package com.pingan.u17.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/11/15
 */

public class CommentListBean extends BaseBean {
    /**
     * cate : 0
     * color : FF0000
     * is_lock : 0
     * comment_from : u17
     * ip : 180.136.145.*
     * comment_id : 7744605
     * user_id : 17883203
     * content : 《请神误用》每周六更新~&nbsp;&nbsp;对请神有任何兴趣或疑问的请进交流群玩耍吧~~~&nbsp;群号：218396198&nbsp;[f375]&nbsp;
     * create_time : 1504364649
     * floor : 2141
     * is_delete : 0
     * is_up : 1
     * total_reply : 2
     * is_choice : 0
     * comic_author : 1
     * content_filter : 《请神误用》每周六更新~  对请神有任何兴趣或疑问的请进交流群玩耍吧~~~ 群号：218396198 [f375]
     * ticketNum : 0
     * gift_num : 0
     * gift_img :
     * imageList : []
     * vip_exp : 0
     * face_type : 0
     * title : 0
     * nickname : 潜昕
     * group_user : 0
     * group_admin : 0
     * group_author : 0
     * face : http://k.avatar.u17i.com/2017/0522/17883203_e8d78b65c91a130f5874c5bf22a3d38f_1495436091.big.png
     * sex : 女
     * exp : 7685.00
     * online_time : 8022311
     * group_custom :
     * id : 17883203
     * level : {"level":1,"min_exp":0,"max":599,"exp_speed":1.1,"album_size":50,"favorite_num":30,"wage":200,"ticket":13}
     * create_time_str : 2月前
     * likeState : 0
     * likeCount : 28
     */

    public String cate;
    public String color;
    public String is_lock;
    public String comment_from;
    public String ip;
    public String comment_id;
    public int user_id;
    public String content;
    public String create_time;
    public String floor;
    public String is_delete;
    public String is_up;
    public String total_reply;
    public String is_choice;
    public int comic_author;
    public String content_filter;
    public int ticketNum;
    public int gift_num;
    public String gift_img;
    public String vip_exp;
    public String face_type;
    public String title;
    public String nickname;
    public String group_user;
    public String group_admin;
    public String group_author;
    public String face;
    public String sex;
    public String exp;
    public String online_time;
    public String group_custom;
    public String id;
    public LevelBean level;
    public String create_time_str;
    public int likeState;
    public String likeCount;
    public List<?> imageList;

    public static class LevelBean implements Serializable {
        /**
         * level : 1
         * min_exp : 0
         * max : 599
         * exp_speed : 1.1
         * album_size : 50
         * favorite_num : 30
         * wage : 200
         * ticket : 13
         */

        public int level;
        public int min_exp;
        public int max;
        public double exp_speed;
        public int album_size;
        public int favorite_num;
        public int wage;
        public int ticket;
    }
}
