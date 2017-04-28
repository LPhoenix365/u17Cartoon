package rst.framework;

import rst.framework.utils.SharePreferencesUtil;

/**
 * Created by iceman on 16/3/21 09:36
 * 邮箱：xubin865@pingan.com.cn
 */
public class UrlConfig {
    public final static int STG = 1;

    public final static int PRD = 2;

    public final static int DEV = 3;

    /**
     * 第三方接入:模式选择： PRD生产模式，STG测试模式,
     */
    public static int MODEL = PRD;

    /**
     * 根据保存的信息初始化api环境
     */
    public static void initApiEnvironment() {
        MODEL = SharePreferencesUtil.getInt("url_config_key", PRD);
        refreshApi();
    }

    /**
     * 切换测试环境和生产环境
     *
     * @param model
     */
    public static void changeApiEnvironment(int model) {
        if (MODEL != model) {
            SharePreferencesUtil.saveInt("url_config_key", model);
            MODEL = model;
            refreshApi();
        }
    }

    /**
     * 根据环境设置,初始化各url
     */
    private static void refreshApi() {
        if (MODEL == PRD) {
            URL_ROOT = URL_ROOT_PRD;
            SESSION_URL_ROOT = PINGANONE_PRD;

        } else if (MODEL == STG) {
            URL_ROOT = URL_ROOT_STG;
            SESSION_URL_ROOT = PINGANONE_STG;

        } else {
            URL_ROOT = SharePreferencesUtil.getString("dev_address_key", "");
        }
        RST_ROOT = URL_ROOT + "/mhis-siapp";
        CMS_URL_ROOT = URL_ROOT + "/siapp-sms";
    }

    /*================================人社通接口服务器 start====================================*/
    public static String URL_ROOT_PRD = "https://ehs.pingan.com.cn";
    public static String URL_ROOT_STG = "https://test-mhis-siapp.pingan.com.cn:57443";
    public static String URL_ROOT = URL_ROOT_PRD;
    /*================================人社通接口服务器 end====================================*/

    /*================================平安一账通接口服务器start   用于获取登录方式和获取短信验证码  start====================================*/
    private final static String PINGANONE_STG = "https://test-member.pingan.com.cn/pinganone";
    private final static String PINGANONE_PRD = "https://member.pingan.com.cn/pinganone";
    public static String SESSION_URL_ROOT = PINGANONE_PRD;
    /*================================平安一账通接口服务器end   用于获取登录方式和获取短信验证码  start====================================*/

    /*================================子业务,主要分为功能接口和cms接口 start====================================*/
    public static String RST_ROOT = URL_ROOT + "/mhis-siapp";
    public static String CMS_URL_ROOT = URL_ROOT + "/siapp-sms";
    /*================================子业务,主要分为功能接口和cms接口 end====================================*/


}

