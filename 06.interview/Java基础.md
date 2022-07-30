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