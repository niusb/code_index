public class jz63 {
//    剑指 Offer 63. 股票的最大利润
    public static void main(String[] args) {
        jz63 jz63 = new jz63();
        int prices[] = {7,1,5,3,6,4};
        System.out.println(jz63.maxProfit(prices));
    }
    public int maxProfit(int[] prices) {
        int cost = Integer.MAX_VALUE, profit = 0;
        for(int price : prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }

}
