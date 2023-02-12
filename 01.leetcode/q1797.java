import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class q1797 {
    public static void main(String[] args) {
        q1797 q1797 = new q1797();
        Date date = new Date();
        AuthenticationManager authenticationManager = new AuthenticationManager(30);
        authenticationManager.renew("aaa", 1);
        authenticationManager.generate("aaa", 2);
        authenticationManager.countUnexpiredTokens(6);
        authenticationManager.generate("bbb", 7);
    }

    static class AuthenticationManager {

        private int timeToLive;
        // 存储id的最后一次更新的时间
        private final HashMap<String, Integer> map = new HashMap<>();
        // 使用两个列表分别储存所有记录的id和time
        List<String> idList = new ArrayList<>();
        List<Integer> timeList = new ArrayList<>();
        // 表示上一次查询未过时验证码时，第一个未过时的验证码在列表中的索引
        int start = 0;

        public AuthenticationManager(int timeToLive) {
            this.timeToLive = timeToLive;
        }

        public void generate(String tokenId, int currentTime) {
            map.put(tokenId, currentTime);
            idList.add(tokenId);
            timeList.add(currentTime);
        }

        public void renew(String tokenId, int currentTime) {
            if (map.containsKey(tokenId)) {
                // 若该id存在且未过时，则更新map并给列表添加记录
                if (map.get(tokenId) > currentTime - timeToLive) {
                    map.put(tokenId, currentTime);
                    idList.add(tokenId);
                    timeList.add(currentTime);
                }
            }
        }

        public int countUnexpiredTokens(int currentTime) {
            int n = idList.size();
            int i = start;
            for (; i < n; i++) {
                String id = idList.get(i);
                Integer time = timeList.get(i);
                // 遇到没超时的记录就停止遍历
                if (time > currentTime - timeToLive) break;
                // 如果这一id的最后一次更新时间和该次记录的时间相同，则说明这一id已经过时
                if (map.get(id).equals(time)) {
                    map.remove(id);
                }
            }
            // 因为下一次时间肯定不早于这次的时间，所以下一次查询从start开始即可
            start = i;
            return map.size();
        }
    }

}
