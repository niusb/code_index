#### 对Spring的理解

- 组成
  - IOC：控制反转（原来要自己new对象，现在交给容器）
  - AOP：面向切面编程（日志、事务）
- 看作容器，承载bean对象
- 生态牛逼

总：容器、生态

分：IOC、AOP、IOC如何实现、循环依赖、生命周期

#### 生命周期

实例化操作

- 以前xml
- 近来注解

![image-20220821203746121](http://rgwngkfs9.hn-bkt.clouddn.com/image-20220821203746121.png)



![image-20220821203716292](http://rgwngkfs9.hn-bkt.clouddn.com/image-20220821203716292.png)

#### 接口和抽象类的区别

接口：自上向下

抽象类：自下向上

#### BeanFactory和FactoryBean的区别

![image-20220824102614688](http://rgwngkfs9.hn-bkt.clouddn.com/image-20220824102614688.png)

#### 循环依赖（三级缓存）

可以理解做三个map结构