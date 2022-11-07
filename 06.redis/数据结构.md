

### 数据类型

#### String字符串

String是redis中最基本的数据类型，一个key对应一个value。
String类型是二进制安全的，意思是redis的string可以包含任何数据。如数字，字符串，jpg图片或者序列化的对象。

#### List列表

Redis中的List其实就是链表（Redis用双端链表实现List)。
使用List结构，我们可以轻松地实现最新消息排队功能（比如新浪微博的TimeLine)。List的另一个应用就是消息队列，可以利用List PUSH操作，将任务存放在List中，然后工作线程再用POP操作将任务取出进行执行。

#### Set集合

Redis 的Set是String类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据。
Redis中集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)。

#### Hash散列

Redis hash是一个string类型的field(字段）和value(值）的映射表，hash特别适合用于存储对象。

#### Zset有序集合

Redis有序集合和集合一样也是string类型元素的集合，且不允许重复的成员。不同的是每个元素都会关联一个
double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。