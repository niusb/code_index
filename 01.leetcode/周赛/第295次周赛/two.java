package 周赛.第295次周赛;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class two {
    // 2288. 价格减免
    public static void main(String[] args) {
        two t = new two();
        String n = t.discountPrices("there are $1 $2 and 5$ candies in the shop", 50);
        System.out.println(n);
    }
//    parseLong是将一个字符串转换成数字的。
    public String discountPrices(String sentence, int discount) {
        return Stream.of(sentence.split(" "))
                .map(t -> !t.matches("\\$\\d+") ? t
                        : String.format("$%.2f", Long.parseLong(t.substring(1)) * (1 - discount / 100.)))
                .collect(Collectors.joining(" "));
    }

}