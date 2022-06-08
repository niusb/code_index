public class q383 {
    // 383. 赎金信
    public static void main(String[] args) {
        q383 q = new q383();
        String ransomNote = "aad";
        String magazine = "aa";
        System.out.println(q.canConstruct(ransomNote,magazine));
    }


    public boolean canConstruct(String ransomNote, String magazine) {
        //记录杂志字符串出现的次数
        int[] arr = new int[26];
        int temp;
        for (int i = 0; i < magazine.length(); i++) {
            temp = magazine.charAt(i) - 'a';
            arr[temp]++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            temp = ransomNote.charAt(i) - 'a';
            //对于金信中的每一个字符都在数组中查找
            //找到相应位减一，否则找不到返回false
            if (arr[temp] > 0) {
                arr[temp]--;
            } else {
                return false;
            }
        }
        return true;
    }
}
