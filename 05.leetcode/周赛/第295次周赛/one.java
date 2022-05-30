package 周赛.第295次周赛;

//第295场周赛-第1题
public class one {
    public static void main(String[] args) {
        one t = new one();
        int n = t.rearrangeCharacters("abcbc", "bc");
        System.out.println(n);
    }
    public int rearrangeCharacters(String s, String target) {
//int是32位的，英文字母有26个。
//所以可以使用int[] count = new int[26];存储字符串中出现字母
        int count[] = new int[26], count2[] = new int[26], min = Integer.MAX_VALUE;
        for (char c: s.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c: target.toCharArray()) {
            count2[c - 'a']++;
        }
        for (char c: target.toCharArray()) {
            min = Math.min(min, count[c - 'a'] / count2[c - 'a']);
        }
        return min;
    }

}
