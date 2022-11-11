import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        Map<String, Object> repMap = new HashMap<>();
        repMap.put("resultCode","0");
        Map<String, Object> login1 = new HashMap<>();
        login1.put("orgId","1");
        login1.put("channelName","营业厅");
        login1.put("opOrgId","1");
        login1.put("mobilePhone","13879182231");
        login1.put("channelId","0");
        login1.put("staffName","思特奇系统");
        login1.put("systemUserCode","SITECH");
        login1.put("orgName","江西省公司");
        Map<String, Object> login2 = new HashMap<>();
        login2.put("orgId","501218");
        login2.put("channelName","营业厅");
        login2.put("opOrgId","501218,501219");
        login2.put("mobilePhone","13879182231");
        login2.put("channelId","0");
        login2.put("staffName","思特奇测试工号");
        login2.put("systemUserCode","ZZ0001");
        login2.put("orgName","Z测试区域");
        List loginInfoList = new ArrayList();
        loginInfoList.add(login1);
        loginInfoList.add(login2);
        repMap.put("resultData",loginInfoList);
        repMap.put("resultMessage","操作成功");
        System.out.println(repMap);
    }
}
