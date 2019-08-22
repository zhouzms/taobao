package zms.util.message;

import zms.util.message.client.AbsRestClient;
import zms.util.message.client.JsonReqClient;

/**
 * 获取验证码
 */
public class GetCode {
    //随机6个数字
    static char [] chars=new char[6];
    private static String sid = "ec055366f68ecdbd2d2e14df7c4824e4";
    private static String token = "6cc33fa37d962250b824b790d3750ed7";
    private static String appid = "06a8df24f99448b4bbe754f8290c7b36";
    private static String templateid = "420900";
    private static String uid = "myCompany_zms";
    /**
     * 获取
     * @return 返回随机码
     */
    private static String getRandom(){
        for(int i=0;i<6;i++){
            chars[i]=(char) (Math.random() * 10 + 48);
        }

        return new String(chars);
    }

    /**
     * 发送短信
     * @param mobile 手机号码
     * @return 是否发送
     */
    public static String param=getRandom();
    public static Boolean isSend(String mobile){
        Boolean aBoolean = testSendSms(sid, token, appid, templateid, param, mobile, uid);
        if(aBoolean){
            return true;
        }
        return false;
    }

    /**
     *调取发送接口
     */
    private static Boolean testSendSms(String sid, String token, String appid, String templateid, String param, String mobile, String uid){
        try {
            String result = InstantiationRestAPI().sendSms(sid, token, appid, templateid, param, mobile, uid);
            System.out.println(result);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 调用需要的类，目前还没有搞懂 new JsonReqClient();
     * @return
     */
    static AbsRestClient InstantiationRestAPI() {
        return new JsonReqClient();
    }

    public static void main(String[] args) {
        Boolean  send = GetCode.isSend("15179556910");
        System.out.println(GetCode.param);
        System.out.println(send);
    }
}
