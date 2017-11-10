package com.pingan.u17.model.response;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/11/8
 */

public class DataResponse<T> {

        /**
         * stateCode : 1
         * message : 版本更新
         * returnData : {"updateInfo":{"code":3350105,"name":"3.3.5.1","update_time":1506441600,"update_content":"【版本更新】\r\n  1.优化流量包服务\r\n  2.程序猿大大修复了已知bug.... \t","apk_url":"http://download.u17i.com/app/android/phone/u17_phone_3_3_5_1_arm_release.apk","apk_name":"u17_phone_3_3_5_1_arm_release.apk","force_update":false,"size":"10841","cpu":"arm","game_channel_visbal":1}}
         */

        public int stateCode;
        public String message;
        public T returnData;

        public boolean isSucess() {
            return stateCode == 1;
        }

}
