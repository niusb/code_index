#### 创建对象的几种方式

1. new关键字

2. Class.newInstance

3. Constructor.newInstance

4. Clone

5. 反序列化

#### 对象创建的过程

1. 判断对象对应的类是否被加载，链接，初始化
2. 为对象分配内存空间（如果内存堆规整----指针碰撞方法，如果内存堆不完整-空闲列表法），内存堆是否完整是根据垃圾回收算法和垃圾收集器决定的
3. 处理并非安全问题（堆是线程共享的，线程安全问题）解决方案： 1、CAS失败重试保证原子性 2、TLAB 每一个线程预先分配一块区域
4. 初始化分配到的空间
5. 设置对象头信息
6. 执行init方法初始化

#### list、set区别

1. list可以插入多个null元素，而set只允许插入一个null元素；
2. list容器是有序的，而set容器是无序的；
3. list方法可以允许重复的对象，而set方法不允许重复对象等等。



#### hashset、treeset区别

1. 不同点1：底层使用的储存数据结构不同：
   - Hashset底层使用的是HashMap[哈希](https://so.csdn.net/so/search?q=哈希&spm=1001.2101.3001.7020)表结构储存
   - 而Treeset底层用的是[TreeMap](https://so.csdn.net/so/search?q=TreeMap&spm=1001.2101.3001.7020)树结构储存。
2. 不同点2：储存的数据保存唯一方式不用。
   - Hashset是通过复写hashCode()方法和[equals](https://so.csdn.net/so/search?q=equals&spm=1001.2101.3001.7020)()方法来保证的。
   - 而Treeset是通过Compareable接口的compareto方法来保证的。
3. 不同点3：
   - hashset无序
   - Treeset有序