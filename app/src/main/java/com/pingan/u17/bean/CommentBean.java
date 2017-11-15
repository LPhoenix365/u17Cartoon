package com.pingan.u17.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/11/15
 */

public class CommentBean extends BaseBean{
    /**
     * cate : 0
     * color : null
     * is_lock : 0
     * comment_from : u17
     * ip : 219.138.133.*
     * comment_id : 4005254
     * user_id : 360545
     * content : 看了下端脑的动漫~&nbsp;&nbsp;&nbsp;那个真的不是喜剧吗？&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开头一点看了就不想看了~&nbsp;&nbsp;&nbsp;&nbsp;端脑开头是那种给人慢慢深入下去的感觉&nbsp;&nbsp;动漫呢&nbsp;&nbsp;&nbsp;直接跳过开头&nbsp;那还看什么？&nbsp;&nbsp;最开始&nbsp;看的时候就是想看下那剧情到底是怎么回事&nbsp;为啥要杀死陆季&nbsp;&nbsp;端脑到底是什么？&nbsp;&nbsp;&nbsp;&nbsp;然后再到后面晴知消失后想看&nbsp;怎么找到他&nbsp;&nbsp;&nbsp;在看看动漫&nbsp;&nbsp;&nbsp;一开头&nbsp;&nbsp;晴知&nbsp;就出场了一下&nbsp;就没了&nbsp;&nbsp;然后就是猪脚去找人&nbsp;&nbsp;给人一点紧张感都没有&nbsp;&nbsp;&nbsp;看了之后感觉就这样吧~&nbsp;&nbsp;&nbsp;这样PV&nbsp;看了有什么用？　　我们看过端脑的人还能理解下　是什么剧情什么的　　　不理解的感觉就是　绑架案似的　　　如果真的要弄ＰＶ的话还不如吧陆季的案子弄出来　然后　在接一个晴知收到信　　然后消失　　这样还有个悬念&nbsp;
     * create_time : 1415895319
     * floor : 33364
     * is_delete : 0
     * is_up : 0
     * total_reply : 1
     * is_choice : 1
     * comic_author : 0
     * content_filter : 看了下端脑的动漫~   那个真的不是喜剧吗？     开头一点看了就不想看了~    端脑开头是那种给人慢慢深入下去的感觉  动漫呢   直接跳过开头 那还看什么？  最开始 看的时候就是想看下那剧情到底是怎么回事 为啥要杀死陆季  端脑到底是什么？    然后再到后面晴知消失后想看 怎么找到他   在看看动漫   一开头  晴知 就出场了一下 就没了  然后就是猪脚去找人  给人一点紧张感都没有   看了之后感觉就这样吧~   这样PV 看了有什么用？　　我们看过端脑的人还能理解下　是什么剧情什么的　　　不理解的感觉就是　绑架案似的　　　如果真的要弄ＰＶ的话还不如吧陆季的案子弄出来　然后　在接一个晴知收到信　　然后消失　　这样还有个悬念
     * ticketNum : 0
     * gift_num : 0
     * gift_img :
     * imageList : []
     * vip_exp : 0
     * face_type : 0
     * title : 34
     * nickname : 月光的话
     * group_user : 0
     * group_admin : 0
     * group_author : 0
     * face : http://static.u17i.com/common/images/face/default.big.gif
     * sex : 保密
     * exp : 4665.00
     * online_time : 908083
     * group_custom : null
     * id : 360545
     * level : {"level":1,"min_exp":0,"max":599,"exp_speed":1.1,"album_size":50,"favorite_num":30,"wage":200,"ticket":13}
     * create_time_str : 无更新
     * likeState : 0
     * likeCount : 0
     * active_time : 1475234717
     * is_hidden : 0
     */

    public String cate;
    public Object color;
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
    public Object group_custom;
    public String id;
    public LevelBean level;
    public String create_time_str;
    public int likeState;
    public int likeCount;
    public String active_time;
    public String is_hidden;
    public List<?> imageList;

    public static class LevelBean implements Serializable{
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
