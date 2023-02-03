import java.util.*;

public class test {
    public static void main(String[] args) {
        test test = new test();
        String sourceString = "A1|B1";
        System.out.println(sourceString);
        System.out.println(test.getRandomRouteValue(sourceString));
    }

    static String getRandomRouteValue(String routeValus)
    {
        String[] routeArys = routeValus.split("[|]");
        if(routeArys==null  ) return null;
        if( routeArys.length == 1) return  routeArys[0];

        Random random = new Random();
        int i = random.nextInt(routeArys.length);

        return routeArys[i];
    }
}
