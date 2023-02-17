#### 重量级、轻量级区分

重量级：需要操作系统帮忙协调调度

轻量级：不需要操作系统协调，自己可以完成调度



#### 线程的三个特性

可见性、有序性、原子性

#### AQS

##### 是什么


AQS抽象的队列同步器，是用来构建锁或者其他同步器组件的重量级基础框架及整个JUC体系的基石，通过内置的FIFO队列来完成资源获取线程的排队工作，并通过一个int类型的变量表示持有锁的状态；当有线程获取不到锁时，就将线程加入该队列中，通过CAS自旋和LockSupport.part()的方式，维护state变量，达到并发同步的效果。
**参考链接：https://blog.csdn.net/qq_37419449/article/details/120040856**

AQS 为构建锁和同步器提供了一些通用功能的是实现，因此，使用 AQS 能简单且高效地构造出应用广泛的大量的同步器，比如我们提到的 ReentrantLock，Semaphore，其他的诸如 ReentrantReadWriteLock，SynchronousQueue等等皆是基于 AQS 的。# AQS 原理



##### AQS原理

AQS 核心思想是，如果被请求的共享资源空闲，则将当前请求资源的线程设置为有效的工作线程，并且将共享资源设置为锁定状态。如果被请求的共享资源被占用，那么就需要一套线程阻塞等待以及被唤醒时锁分配的机制，这个机制 AQS 是用 **CLH 队列锁** 实现的，即将暂时获取不到锁的线程加入到队列中。

CLH(Craig,Landin,and Hagersten) 队列是一个虚拟的双向队列（虚拟的双向队列即不存在队列实例，仅存在结点之间的关联关系）。AQS 是将每条请求共享资源的线程封装成一个 CLH 锁队列的一个结点（Node）来实现锁的分配。在 CLH 同步队列中，一个节点表示一个线程，它保存着线程的引用（thread）、 当前节点在队列中的状态（waitStatus）、前驱节点（prev）、后继节点（next）。

CLH 队列结构如下图所示：

![img](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/40cb932a64694262993907ebda6a0bfe~tplv-k3u1fbpfcp-zoom-1.image)

AQS(`AbstractQueuedSynchronizer`)的核心原理图（图源[Java 并发之 AQS 详解open in new window](https://www.cnblogs.com/waterystone/p/4920797.html)）如下：

![img](https://my-blog-to-use.oss-cn-beijing.aliyuncs.com/Java 程序员必备：并发知识系统总结/CLH.png)

AQS 使用 **int 成员变量 `state` 表示同步状态**，通过内置的 **线程等待队列** 来完成获取资源线程的排队工作。

`state` 变量由 `volatile` 修饰，用于展示当前临界资源的获锁情况。

```java
// 共享变量，使用volatile修饰保证线程可见性
private volatile int state;
```

另外，状态信息 `state` 可以通过 `protected` 类型的`getState()`、`setState()`和`compareAndSetState()` 进行操作。并且，这几个方法都是 `final` 修饰的，在子类中无法被重写。

```java
//返回同步状态的当前值
protected final int getState() {
     return state;
}
 // 设置同步状态的值
protected final void setState(int newState) {
     state = newState;
}
//原子地（CAS操作）将同步状态值设置为给定值update如果当前同步状态的值等于expect（期望值）
protected final boolean compareAndSetState(int expect, int update) {
      return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
}
```

以 `ReentrantLock` 为例，`state` 初始值为 0，表示未锁定状态。A 线程 `lock()` 时，会调用 `tryAcquire()` 独占该锁并将 `state+1` 。此后，其他线程再 `tryAcquire()` 时就会失败，直到 A 线程 `unlock()` 到 `state=`0（即释放锁）为止，其它线程才有机会获取该锁。当然，释放锁之前，A 线程自己是可以重复获取此锁的（`state` 会累加），这就是可重入的概念。但要注意，获取多少次就要释放多少次，这样才能保证 state 是能回到零态的。

再以 `CountDownLatch` 以例，任务分为 N 个子线程去执行，`state` 也初始化为 N（注意 N 要与线程个数一致）。这 N 个子线程是并行执行的，每个子线程执行完后`countDown()` 一次，state 会 CAS(Compare and Swap) 减 1。等到所有子线程都执行完后(即 `state=0` )，会 `unpark()` 主调用线程，然后主调用线程就会从 `await()` 函数返回，继续后余动作。

原文链接：https://javaguide.cn/java/concurrent/aqs.html



#### synchronized

**本质**：在对象头做改动相当于锁定

![image-20220828232158742](https://raw.githubusercontent.com/niusb/picGo/main/img/image-20220828232158742.png)

#### 重量级锁、轻量级锁

轻量级锁:自旋锁、乐观锁、无锁

#### 为什么会发生原子性的问题？

因为CPU 有时间片的概念，会根据不同的调度算法进行线程调度。当一个线程获得时间片之后开始执行，在时间片耗尽之后，就会失去 CPU 使用权。所以在多线程场景下，由于时间片在线程间轮换，就会发生原子性问题。

#### 如何解决原子性的问题呢？加锁，为什么加锁可以解决原子性问题？

Synchronized是 Java 在语言层面提供的互斥原语，Java中还有其他类型的锁。但是作为互斥锁，原理都是一样的，首先要有一个锁，然后是要锁住什么资源以及在哪里加锁

#### 为什么会发生有序性的问题?重排，sync有加内存屏障吗？

**指令重排序**

编译器或者解释器为了优化程序的执行性能，有时会改变程序的执行顺序。但是，编译器或者解释器对程序的执行顺序进行修改，

导致并发编程产生各种诡异问题的根源有三个：缓存导致的可见性问题、线程切换导致的原子性问题和编译优化带来的有序性问题。