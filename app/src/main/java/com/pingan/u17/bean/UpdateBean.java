package com.pingan.u17.bean;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/24
 */

public class UpdateBean extends BaseBean {

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
