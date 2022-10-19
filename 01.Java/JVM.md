### JVM

#### 垃圾

没有引用的对象就是垃圾

满足**根可达性**的不是垃圾

#### 垃圾清除的算法

- 标记清除（mark-sweep)

![image-20220824094322109](https://raw.githubusercontent.com/niusb/picGo/main/img/image-20220824094322109.png)

- 拷贝(copying)

![image-20220824094237165](https://raw.githubusercontent.com/niusb/picGo/main/img/image-20220824094237165.png)

- 标记压缩（mark-compact）

  ![image-20220824094107714](https://raw.githubusercontent.com/niusb/picGo/main/img/image-20220824094107714.png)

#### 垃圾回收器

##### 堆内存逻辑分区

可以手动调整

![image-20220824135041642](https://raw.githubusercontent.com/niusb/picGo/main/img/image-20220824135041642.png)

##### 垃圾回收期类型

![image-20220824150955190](https://raw.githubusercontent.com/niusb/picGo/main/img/image-20220824150955190.png)

##### 三色标记算法

- 黑色：已经扫描结束
- 灰色：没有扫描结束
- 白色：未扫描

##### 【CMS垃圾回收器】Incremental Update的非常隐蔽的问题

review

![image-20220824162558554](https://raw.githubusercontent.com/niusb/picGo/main/img/image-20220824162558554.png)

##### 【G1垃圾回收期】SFATB Snapshot At the Beginning

![image-20220825135751233](https://raw.githubusercontent.com/niusb/picGo/main/img/image-20220825135751233.png)

#### [JVM怎么给对象分配内存](https://blog.csdn.net/qq_37735779/article/details/124620252)

为对象分配空间的任务等同于把一块确定大小的内存从Java堆中划分出来。与[垃圾回收](https://so.csdn.net/so/search?q=垃圾回收&spm=1001.2101.3001.7020)机制有关：

1. 对于MS的垃圾回收器，是使用空闲列表进行分配；
2. 对于MC的垃圾回收器，是使用指针碰撞进行分配。

（1）指针碰撞(Serial、ParNew等带Compact过程的收集器)

假设Java堆中内存是绝对规整的，所有用过的内存都放在一边，空闲的内存放在另一边，中间放着一个指针作为分界点的指示器，那所分配内存就仅仅是把那个指针向空闲空间那边挪动一段与对象大小相等的距离，这种分配方式称为“指针碰撞”（Bump the Pointer）。

（2）空闲列表(CMS这种基于Mark-Sweep算法的收集器)
如果Java堆中的内存并不是规整的，已使用的内存和空闲的内存相互交错，那就没有办法简单地进行指针碰撞了，虚拟机就必须维护一个列表，记录上哪些内存块是可用的，在分配的时候从列表中找到一块足够大的空间划分给对象实例，并更新列表上的记录，这种分配方式称为“空闲列表”（Free List）。

#### [垃圾回收怎么触发](https://blog.csdn.net/weixin_41643257/article/details/122870691)

**对象被回收前的挣扎**

第一次标记：如果对象在进行可达性分析之后发现没有与GC Root相连接的引用链，那么将会被第一次标记。

第二次标记：第一次标记之后会进行一次筛选，筛选的条件就是此对象是否有必要执行finalize()方法。在finalize()方法中没有重新与引用链建立关联关系的，将被进行第二次标记。第二次标记成功的对象将真的会被回收，如果对象在finalize()方法中重新与引用链建立了关联关系，那么将会逃离本次回收，继续存活。

**方法区如何判断是否需要回收**

首先需要确定方法区主要回收的内容：废弃常量和无用的类。对于废弃常量可以使用引用可达性分析算法来判断，而无用的类需要满足以下三个条件：

该类所有的实例都已经被回收，JAVA堆中不存在该类的任何实例。
加载该类的classloader已经被回收。
该类对应的java.lang.class对象没有任何地方被引用，无法在任务地方通过反射的方式访问该类的方法。

#### GC频繁如何调优

//todo



### JVM调优

#### 什么是调优

//todo

#### 常用命令

1. JVM自带命令

   - jps 列出Java进程

   - jinfo（进程号） Java进程的相关信息

   - jstat  内存占用大小输出

     ```
     jstat -gc (进程号)
     ```

   - jstack

     
     
   - jamp 不能在生产环境使用，会使JVM卡死在某个状态输出文件

     ```
     jmap -histo (进程号)
     jmap -histo (进程号) | head -20
     ```

2. linux系统命令

   - top（进程）
   - top -Hp （线程）

3. 排查步骤

   1. top排查高进程
   2. top -Hp排查高线程
   3. jstack将线程对应到程序，两种情况（频繁GC，或者业务线程BUG）
      1. 频繁GC，大量流量冲击，或者垃圾回收失败（内存泄漏）



