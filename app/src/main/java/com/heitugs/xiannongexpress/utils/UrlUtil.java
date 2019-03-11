package com.heitugs.xiannongexpress.utils;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class UrlUtil {
    public static final String url = "http://app.heitugs.com/";


    /**
     * http://app.heitugs.com/app_server/app_user_info.aspx?type=a&username=13796666878&usertype=0&RealName=李明&sex=0&IDcardFiles=2304211234&password=123456
     */
    // 用户注册
    public static final String registerSuccessUrl = url + "app_server/app_user_info.aspx?type=a";

    // 验证验证码
    public static final String registerUrl = url + "sms/get_sms_code.aspx?type=set";
    // http://app.heitugs.com/code=1234&sendphone=13796666878
    // 获取验证码
    public static final String getCodeUrl = "http://app.heitugs.com/sms/get_sms_code.aspx?type=get&sendphone=";

    // 登录
    public static final String loginUrl = url + "user/l_no.aspx?"; // username=test&password=111111

    //查询用户信息
    public static final String findUrl = url + "app_server/app_user_info.aspx?type=l&";
    /**
     * http://app.heitugs.com/app_server/app_user_wz.aspx?usernum=99892491029&
     */
    // 订单发布
    public static final String orderUrl = url + "app_server/app_user_wz.aspx?usernum=";

    // 查询自己的订单
    public static final String ownOrderUrl = url + "app_server/app_user_wz.aspx?usernum=";

    // 手机验证码

    public static final String phoneCodeUrl = url + "sms/get_sms_code.aspx?type=getpass&sendphone=";
    // 获取手机验证码
    public static final String booleanCodeUrl = url + "sms/get_sms_code.aspx?type=set&code=1234&sendphone=13796666878";
    // 地址删除
    public static final String addressDeleteUrl = url + "app_server/app_user_address.aspx?type=d&usernum=99892491029&id=123456";

    // 修改用户信息
    public static final String changeUrl = url + "app_server/app_user_info.aspx?type=u&";

    public static final String addressUrl = url + "app_server/";

    /**
     * app_user_wz.aspx?usernum=99892491029&size=1&type=a&sendTime=2016-05-5&receiveTime=2016-05-19%204:00&receiveLocation=45.771959AND126.690794
     */


    public static final String aa = "http://app.heitugs.com/app_server/app_user_info.aspx?type=l&usernum=99892491029";
    public static final String bb = "http://app.heitugs.com/user/l_no.aspx?username=test&password=111111";
}
