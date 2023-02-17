public class q06 {
    public static void main(String[] args) {
        int[] coins = {4,2,1};
        q06 q06 = new q06();
        System.out.println(q06.minCount(coins));
//        System.out.println(3/2);

    }
    public int minCount(int[] coins) {
        int length = coins.length;
        int minCount = 0;
        for (int i = 0; i < length; i++) {
//            int值乘以1.0,解决取余问题
//            int count = (int) Math.ceil(coins[i]*1.0 / 2);
//            int值+1,解决取余问题
            int count = (coins[i] + 1)  / 2;
            minCount = count + minCount;
        }
        return minCount;
    }
}
