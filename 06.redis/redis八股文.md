#### redis支持的数据结构

- String
- hash，哈希是field和value之间的映射
- List
- Set
- Zset

#### SDS

SDS,即Simple Dynamic String,简单动态字符串。
Redis没有直接使用C语言传统的字符串表示（以空字符结尾的字符数组）,而是自己构建了一种名为简单动态字符串（simple dynamic
string,SDS)的抽象类型，并将SDS用作Redis的默认字符串表示。
在Redis里面，C字符串只会作为字符串字面量（string literal)用在一些无须对字符串值进行修改的地方，比如打印日志。
当Redis需要的不仅仅是一个字符串字面量，而是一个可以被修改的字符串值时，Redis就会使用SDS来表示字符串值，比如在Redis的
数据库里面，包含字符串值的键值对在底层都是由SDS实现的。

**优点**

SDS可以在0(1)的时间复杂度中获取字符串长度，
而C语言字符串需要逐一遍历；
SDS拥有自动扩容机制；
SDS拥有惰性空间释放机制，减少了内存重分配次数；
SDS不同于C字符串，它是二进制安全的CSDN我就坐这看看

#### 说一下你在项目中的redis的应用场景？

1. 5大value类型：
2. 基本上就是缓存~!
3. 为的是服务无状态，延申思考，看你的项目有哪些数据结构或对象，在单机里需要单机锁，在
   多机需要分布式锁，抽出来放入redis中；
4. 无锁化

#### redis是单线程还是多线程？

1. 无论什么版本，工作线程就是一个

2. 6.×高版本出现了IO多线程

3. 使用上没有变化

4. 【IO】1.系统内核->程序 2.程序处理

5. IO多线程提高输入/输出的效率，计算效率未影响

   好处：

   1. 执行时间缩短
   2. 更好压榨系统的IO性能

![redis.drawio](https://raw.githubusercontent.com/niusb/picGo/main/img/redis.drawio.png)



#### redis存在线程安 全的问题吗？为什么？

redis线程模型
redis可以保障内部串行（工作线程串行）
外界使用的时候要保障，业务上要自行保障顺序~!

#### 缓存穿透

#### 缓存击透

#### 缓存雪崩

核心避免DB接触大量无效/重复的请求

![image-20220825172231201](https://raw.githubusercontent.com/niusb/picGo/main/img/image-20220825172231201.png)



#### 数据库与缓存不一致如何解决