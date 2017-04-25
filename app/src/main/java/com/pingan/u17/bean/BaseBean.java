package com.pingan.u17.bean;

import java.io.Serializable;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/18
 */

public class BaseBean implements Serializable {


    private int            code;
    private String         msg;
    private BaseCommonBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BaseCommonBean getData() {
        return data;
    }

    public void setData(BaseCommonBean data) {
        this.data = data;
    }

    public static class BaseCommonBean {
        /**
         * stateCode : 1
         * message : 版本更新
         * returnData : {"updateInfo":{"code":3310105,"name":"3.3.1","update_time":1491753600,"update_content":"更新提示：\r\n【新增】新增\u201c妖果商城\u201d，妖果可以兑换礼物啦！\r\n【新增】新增\u201c继续观看\u201d提醒，观看体验UP↑\r\n【优化】点击漫画作者名，可看作者的相关作品。\r\n【优化】优化阅读器界面UI，可直接打赏作者哟~","apk_url":"http://download.u17i.com/app/android/phone/u17_phone_3_3_1_arm_release.apk","apk_name":"u17_phone_3_3_1_arm_release.apk","force_update":false,"size":"10072","cpu":"arm","game_channel_visbal":1}}
         */

        private int            stateCode;
        private String         message;
        private ReturnDataBean returnData;

        public int getStateCode() {
            return stateCode;
        }

        public void setStateCode(int stateCode) {
            this.stateCode = stateCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public ReturnDataBean getReturnData() {
            return returnData;
        }

        public void setReturnData(ReturnDataBean returnData) {
            this.returnData = returnData;
        }

        public static class ReturnDataBean<T> {
            /**
             * updateInfo : {"code":3310105,"name":"3.3.1","update_time":1491753600,"update_content":"更新提示：\r\n【新增】新增\u201c妖果商城\u201d，妖果可以兑换礼物啦！\r\n【新增】新增\u201c继续观看\u201d提醒，观看体验UP↑\r\n【优化】点击漫画作者名，可看作者的相关作品。\r\n【优化】优化阅读器界面UI，可直接打赏作者哟~","apk_url":"http://download.u17i.com/app/android/phone/u17_phone_3_3_1_arm_release.apk","apk_name":"u17_phone_3_3_1_arm_release.apk","force_update":false,"size":"10072","cpu":"arm","game_channel_visbal":1}
             */

            private T dateInfo;

            public T getUpdateInfo() {
                return dateInfo;
            }

            public void setUpdateInfo(T dateInfo) {
                this.dateInfo = dateInfo;
            }
        }
    }

}
