public class q242 {
    // 242.有效的字母异位词
    public static void main(String[] args) {
        q242 q = new q242();
        System.out.println(q.isAnagram("car","ra"));
    }

    public boolean isAnagram(String s, String t) {

        int[] record = new int[26];
        for (char c:
             s.toCharArray()) {
            record[c - 'a'] += 1;
        }
        for (char c:
             t.toCharArray()) {
            record[c - 'a'] -= 1;
        }
        for (int i :
             record) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
