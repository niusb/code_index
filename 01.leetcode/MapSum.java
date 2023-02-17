import java.util.HashMap;
import java.util.Map;

public class MapSum {
//    https://leetcode.cn/problems/z1R5dt/
//    字典树
    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple",2);
        System.out.println(mapSum.sum("app"));
        mapSum.insert("app",3);
        System.out.println(mapSum.sum("app"));

    }

    class Tire{
        Tire[] child;   // 小写字母，构造26个节点的数组
        int cnt;        // 记录当前节点对应的数值

        Tire(int num) {
            this.child = new Tire[26];
            this.cnt = num;
        }
    }

    Map<String, Integer> map;
    Tire root;

    /** Initialize your data structure here. */
    public MapSum() {
        map = new HashMap<>();
        root = new Tire(0);
    }

    public void insert(String key, int val) {
        // 判断是否已经存在，存在则覆盖并且将当前val - 之前val得到最新val 更新字典树
        if (map.containsKey(key)){
            int pre = map.get(key);
            map.put(key, val);
            val -= pre;
        }else{
            map.put(key, val);
        }

        // 更新字典树
        Tire cur = root;
        for (int i = 0; i < key.length(); i++){
            char ck = key.charAt(i);
            if (cur.child[ck - 'a'] == null){
                cur.child[ck - 'a'] = new Tire(val);
            }else{
                cur.child[ck - 'a'].cnt += val;
            }
            cur = cur.child[ck - 'a'];
        }
    }

    public int sum(String prefix) {
        // 遍历字典树，得到最后结果
        Tire cur = root;
        int ans = 0;
        for (int i = 0; i < prefix.length(); i++){
            char cp = prefix.charAt(i);
            if (cur.child[cp - 'a'] == null) {
                return 0;
            }
            cur = cur.child[cp - 'a'];
            ans = cur.cnt;
        }
        return ans;
    }



}
