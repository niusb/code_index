import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class StockPrice {
//https://leetcode.cn/problems/stock-price-fluctuation/
    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10);
        stockPrice.update(2,5);
        System.out.println(stockPrice.current());
        System.out.println(stockPrice.maximum());
        stockPrice.update(1,3);
        System.out.println(stockPrice.maximum());
        stockPrice.update(4,2);
        System.out.println(stockPrice.maximum());

    }

    public void StockPrice() {

    }
    int cur;
    Map<Integer, Integer> map = new HashMap<>();
    TreeMap<Integer, Integer> ts = new TreeMap<>();
    public void update(int timestamp, int price) {
        cur = Math.max(cur, timestamp);
        if (map.containsKey(timestamp)) {
            int old = map.get(timestamp);
            int cnt = ts.get(old);
            if (cnt == 1) ts.remove(old);
            else ts.put(old, cnt - 1);
        }
        map.put(timestamp, price);
        ts.put(price, ts.getOrDefault(price, 0) + 1);
    }

    public int current() {
        return map.get(cur);
    }

    public int maximum() {
        return ts.lastKey();
    }

    public int minimum() {
        return ts.firstKey();
    }
}
