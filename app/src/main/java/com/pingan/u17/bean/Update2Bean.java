package com.pingan.u17.bean;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/24
 */

public class Update2Bean extends DataBean<Update2Bean.ReturnDataBean> {

    public static class ReturnDataBean {
        /**
         * updateInfo : {"code":3320100,"name":"3.3.2.1","update_time":1495468800,"update_content":"升级来啦~ \r\n【新增】\u201c换一换\u201d，强力漫画看个够 \r\n【新增】\u201c书单\u201d，自由创建个性合集\r\n【优化】\u201c漫画详情页\u201d，操作便捷人性化\r\n【优化】\u201c历史记录列表\u201d，继续阅读更方便\r\n 快来体验吧！","apk_url":"http://download.u17i.com/app/android/phone/u17_phone_3_3_2_1_arm_release.apk","apk_name":"u17_phone_3_3_2_1_arm_release.apk","force_update":false,"size":"12088","cpu":"arm","game_channel_visbal":1}
         */

        private UpdateInfoBean updateInfo;

        public UpdateInfoBean getUpdateInfo() {
            return updateInfo;
        }

        public void setUpdateInfo(UpdateInfoBean updateInfo) {
            this.updateInfo = updateInfo;
        }

        public static class UpdateInfoBean {
            /**
             * code : 3320100
             * name : 3.3.2.1
             * update_time : 1495468800
             * update_content : 升级来啦~
             * 【新增】“换一换”，强力漫画看个够
             * 【新增】“书单”，自由创建个性合集
             * 【优化】“漫画详情页”，操作便捷人性化
             * 【优化】“历史记录列表”，继续阅读更方便
             * 快来体验吧！
             * apk_url : http://download.u17i.com/app/android/phone/u17_phone_3_3_2_1_arm_release.apk
             * apk_name : u17_phone_3_3_2_1_arm_release.apk
             * force_update : false
             * size : 12088
             * cpu : arm
             * game_channel_visbal : 1
             */

            private int     code;
            private String  name;
            private int     update_time;
            private String  update_content;
            private String  apk_url;
            private String  apk_name;
            private boolean force_update;
            private String  size;
            private String  cpu;
            private int     game_channel_visbal;

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }

            public String getUpdate_content() {
                return update_content;
            }

            public void setUpdate_content(String update_content) {
                this.update_content = update_content;
            }

            public String getApk_url() {
                return apk_url;
            }

            public void setApk_url(String apk_url) {
                this.apk_url = apk_url;
            }

            public String getApk_name() {
                return apk_name;
            }

            public void setApk_name(String apk_name) {
                this.apk_name = apk_name;
            }

            public boolean isForce_update() {
                return force_update;
            }

            public void setForce_update(boolean force_update) {
                this.force_update = force_update;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getCpu() {
                return cpu;
            }

            public void setCpu(String cpu) {
                this.cpu = cpu;
            }

            public int getGame_channel_visbal() {
                return game_channel_visbal;
            }

            public void setGame_channel_visbal(int game_channel_visbal) {
                this.game_channel_visbal = game_channel_visbal;
            }
        }
    }

}
