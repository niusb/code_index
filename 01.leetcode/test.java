import java.util.*;

public class test {
    public static void main(String[] args) {
        test test = new test();
//        String sourceString = "A1|B1";
//        System.out.println(sourceString);
//        System.out.println(test.ThreeSum(sourceString));

//        Random random = new Random();
//        int ends = random.nextInt(9);
//        String.format("%02d",ends);
//        String loginInfo_accept = String.valueOf(System.currentTimeMillis()) + ends;
//        System.out.println(loginInfo_accept);

//        jz25 jz25 = new jz25();
        ListNode node3 = new ListNode(4);
        ListNode node2 = new ListNode(2,node3);
        ListNode node1 = new ListNode(1,node2);
        ListNode node6 = new ListNode(4);
        ListNode node5 = new ListNode(3,node6);
        ListNode node4 = new ListNode(1,node5);
        ListNode.print(test.mergeTwoLists(node1,node4));

    }

    public class ThreeSum {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            if (nums == null || nums.length < 3) {
                return ans;
            }
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum == 0) {
                        ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (sum < 0) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            return ans;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dum = new ListNode(0), cur = dum;
        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            }
            else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dum.next;
    }


}
