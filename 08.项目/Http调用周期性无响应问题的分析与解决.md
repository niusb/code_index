#### **一. 问题背景**

项目中， 需要把第三方的服务 通过微服务网关进行发布。 在测试过程中，发现了每过一段时间，通过网关调用下游服务时，会返回 HttpCode 500的异常， 再次调用该服务就返回正常了。 


![img](http://rgwngkfs9.hn-bkt.clouddn.com/f46e30a4-26a8-42ca-b23e-c0700768d614.png)



#### **二. 问题定位**

\1. 网关日志分析，通过测试人员描述，初步判断是调用下游三方服务超时了，在日志中抓到异常日志如下：

![img](http://rgwngkfs9.hn-bkt.clouddn.com/cd473ab5-5510-4f02-9032-28a2bea274e4.png)


通过日志可以看到，网关在调用第三方服务时， 出现了 Socket ReadTimedout 的异常，判断可能是 网关调用下游服务的超时时间 10s 太短了， 下游服务在第一次被调用时，可能要加载一些数据，超过了10s，所以第一次调用报错， 而后续的调用是正常的。

\2. 调整服务调用超时时间，从10s 调整到 60s，但是在30分钟以后，第一次调用依然会出现 Socket ReadTimedout 的异常。 在调整到 180s， 第一次调用依然会出现 Socket ReadTimedout 异常。因此怀疑并不是简单的超时时间问题。 重新调整了怀疑方向。

\3. 部署我们自己的模拟应用进行测试，多次测试并不会出现 Socket ReadTimedout 异常。 因此怀疑可能是 网关到 第三方的网络存在问题。

\4. 协调网运同事进行排查， 网络没有问题。 同时排查了主机的 ulimit(openfile) 与 ipv4(tcp_timestamps、tcp_tw_recycle) 配置。 调整主机 ulimit openfile 参数后，第一次超时仍未解决。

\5. 因为现象是第一次调用服务失败，后续调用服务正常， 因此通过Postman的 Code功能 对比2次调用的 HttpHeader 等信息， 第一次调用和后续多次调用都是完全一致的。 

![img](http://rgwngkfs9.hn-bkt.clouddn.com/fc98ead5-19de-4e10-a87b-5e10974a64d7.png)




\6. 常规排查问题的手段基本都使用了，还是无法判断问题原因。 因此只能通过网络抓包来分析TCP的交互流程，在更底层的4层网络来分析排查问题。

- Linux 抓包工具使用 Tcpdump.
- 网络包分析工具使用 Wireshark。

##### **(1) 正常服务网络包1 :**

​    *网关ip地址： 192.168.147.6
​    三方服务ip地址: 172.22.136.123*


![img](http://rgwngkfs9.hn-bkt.clouddn.com/0ea2683d-73d9-4212-b395-ce8d5965948e.png)

##### **(2) 正常服务网络包2 :**


还有一种正常调用的情况是： 没有TCP的3次握手过程，网关直接发送 TCP 封包到下游服务，这样的流程是由于 Http 设置了 keep-alive 参数， 因此客户端和服务端会维持一个 TCP的长链接，这样就不用每次都建立 TCP连接。 关于 keep-alive 参数说明参考: https://blog.csdn.net/xiaoduanayu/article/details/78386508

![img](http://rgwngkfs9.hn-bkt.clouddn.com/72a8f298-dff2-44c5-a2d0-dfef732e9dd5.png)

![img](http://rgwngkfs9.hn-bkt.clouddn.com/08488882-e73f-4454-9c12-06bac27a0432.png)

##### **(3) 第一次调用异常的网络包 :**


*从异常的TCP抓包中可以看到， 没有TCP的3次握手过程， 直接是 Client 给 Server 发送数据包， 但是因为 Server 没有ACK响应， 所以Client 重试了 10次(Linux配置) ， 然后一直等待到超时。*

**因此大概率问题就是： Http因为使用了 keep-alive设置， clinet 和server 建立了长连接， 但是 server（nginx/openrestry） 由于配置, 在 response 的 keep-alive Header中， 并没有返回 timeout的参数，而 server 又设置了在若干时间后(30mins)， 断开 TCP连接； 但此时 Client 由于keep-alive设置，并不知道 TCP已经断开， 所以在30mins后的第一次调用时， 不会做TCP 握手动作， 导致超时问题。** 

![img](http://rgwngkfs9.hn-bkt.clouddn.com/70fd65b7-3c45-4aa4-a813-e1a53677b8c5.png)

 

![img](http://rgwngkfs9.hn-bkt.clouddn.com/78ae1f23-7c8a-45d1-be76-7512da2aebca.png)



#### **三. 问题解决**

从上面的问题分析可以看到， 解决方式有 2种：
 (1) Server 端在返回的 Keep-Alive Header种增加 timeout参数， 例如 HttpHeader -> Connection ： keep-alive; timeout=60000
(2) Client 自己为 Keep-Alive 设置一个 timeout 时间。

由于 下游服务是 第三方服务， 很难推动修改， 因此我们采用 Client端设置 Keep-Alive timeout的方式修改。网关内部使用 Apache HttpClient4.5 作为调用下游服务的客户端，因此修改 Apache HttpClient中 Keep-Alive 参数的设置即可， 参考 ： https://hc.apache.org/httpcomponents-client-4.5.x/current/tutorial/html/connmgmt.html#d5e425。


![img](http://rgwngkfs9.hn-bkt.clouddn.com/ad1e6da1-c67a-408f-a0a6-a7c789bee370.png)