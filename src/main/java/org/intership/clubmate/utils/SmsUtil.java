package org.intership.clubmate.utils;


import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;

import java.util.HashMap;


public class SmsUtil {
    //连接阿里云
    public static String rigionId="cn-beijing";
    public static String accessKeyId="LTAI5tQuPaddjn1FNYDCJ8VT";
    public static String accessKeySecret="drgcDa1GXNiGFC7XymVeBzZlCWdIUy";
    public static String signName="ClubMate";
    public static String templateCode="SMS_154950909";
    //code为验证码
    public static String toSendMes(String phoneNumber,String code) {

        DefaultProfile profile = DefaultProfile.getProfile(rigionId,accessKeyId ,accessKeySecret );

        IAcsClient client = new DefaultAcsClient(profile);
        //2.构建请求 自定义参数
        SendSmsRequest request = new SendSmsRequest();
        //接收短信的手机号码
        request.setPhoneNumbers(phoneNumber);
        //短信签名名称
        request.setSignName(signName);
        //短信模板CODE
        request.setTemplateCode(templateCode);

        HashMap<String,String> param=new HashMap<>();
        param.put("code",code);
        request.setTemplateParam(JSONObject.toJSONString(param));
        SendSmsResponse response = new SendSmsResponse();
        try {
            //3.发送请求
            response = client.getAcsResponse(request);
//            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
        //短信成功返回json {  "RequestId": "614048FB-0619-4439-A1D5-AA8B218A****",
        // "Message": "OK",  "BizId": "386715418801811068^0",  "Code": "OK"}
        return response.getMessage();
    }
}