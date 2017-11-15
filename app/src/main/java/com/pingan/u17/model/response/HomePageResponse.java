package com.pingan.u17.model.response;

import java.util.List;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/11/14
 */

public class HomePageResponse extends BaseResponse<HomePageResponse> {


    private List<GalleryItemsBean> galleryItems;
    private List<?> textItems;
    private List<ComicListsBean> comicLists;

    public List<GalleryItemsBean> getGalleryItems() {
        return galleryItems;
    }

    public void setGalleryItems(List<GalleryItemsBean> galleryItems) {
        this.galleryItems = galleryItems;
    }

    public List<?> getTextItems() {
        return textItems;
    }

    public void setTextItems(List<?> textItems) {
        this.textItems = textItems;
    }

    public List<ComicListsBean> getComicLists() {
        return comicLists;
    }

    public void setComicLists(List<ComicListsBean> comicLists) {
        this.comicLists = comicLists;
    }

    public static class GalleryItemsBean {
        /**
         * linkType : 3
         * cover : http://image.mylife.u17t.com/2016/12/14/1481680627_AKP0kvpm03c7.jpg
         * id : 300
         * title :
         * content :
         * ext : [{"key":"comicId","val":"133715"}]
         * isSpecial : 1
         */

        private int linkType;
        private String cover;
        private int id;
        private String title;
        private String content;
        private int isSpecial;
        private List<ExtBean> ext;

        public int getLinkType() {
            return linkType;
        }

        public void setLinkType(int linkType) {
            this.linkType = linkType;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIsSpecial() {
            return isSpecial;
        }

        public void setIsSpecial(int isSpecial) {
            this.isSpecial = isSpecial;
        }

        public List<ExtBean> getExt() {
            return ext;
        }

        public void setExt(List<ExtBean> ext) {
            this.ext = ext;
        }

        public static class ExtBean {
            /**
             * key : comicId
             * val : 133715
             */

            private String key;
            private String val;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getVal() {
                return val;
            }

            public void setVal(String val) {
                this.val = val;
            }
        }
    }

    public static class ComicListsBean {
        /**
         * comics : [{"comicId":155045,"name":"迷局","cover":"http://cover2.u17i.com/2017/09/4165780_1506495643_ys9dsB2tyDoo.sbig.jpg","description":"一份真挚的友谊，一张张罪恶的面孔，在命运的面前，他们会怎么做，会面对现实，还是改变命运，各位读者拭目以待~~~","cornerInfo":"19","short_description":"看不透的人生道路","author_name":"逗鬼の鱼","is_vip":0},{"comicId":190,"name":"拜见女皇陛下","cover":"http://cover2.u17i.com/2016/11/1213_1478322908_NjjWJwz3DTTd.sbig.jpg","description":"小学生于1996年开始的战斗。","cornerInfo":"215","short_description":"小学生萝莉正太成长","author_name":"ZCloud","is_vip":2},{"comicId":64171,"name":"妖闻录","cover":"http://cover2.u17i.com/2015/08/661165_1439557376_Z4COHfihyOZc.sbig.jpg","description":"小道下山开始人间的修行，临行前老道长给了他一个卷轴，卷轴里面记载着众多妖怪的名字和属性，需要时可以召唤，但要满足一定的条件才可以，小道好不容易才摸清楚了规律，又卷入各式各样的妖怪事件里","cornerInfo":"142","short_description":"修道除妖 卷轴召唤","author_name":"小铜钱","is_vip":4},{"comicId":84103,"name":"末日孢子","cover":"http://cover2.u17i.com/2014/11/6433_1415003670_42VlQHjwejdv.sbig.jpg","description":"也许你们不知道，外面究竟发生了什么事情。每个人都有自己想寻找的人，我也一样。每个人都有自己对不住的人，我也一样。我一直都没有勇敢过，直到现在。这是开始的时间，也是结束的时间。在一场末日瘟疫来临时，你如何生存下去？","cornerInfo":"110","short_description":"最后的幸存者们","author_name":"衍其门","is_vip":4},{"comicId":136224,"name":"讨厌你喜欢你","cover":"http://cover2.u17i.com/2017/10/16766133_1508748166_RT04o0TRm8R2.sbig.jpg","description":"【每周一更新】被一个陌生小孩说讨厌你，呃？还一直缠着我成为我的青梅竹马，口不对心的家伙，你到底是讨厌我还是喜欢我？","cornerInfo":"35","short_description":"恋爱中的口不对心","author_name":"東爺。","is_vip":4},{"comicId":71084,"name":"一万零七夜-彩漫周更-","cover":"http://cover2.u17i.com/2016/08/3126292_1470427197_wW6b7wwbd8DO.sbig.jpg","description":"免费！动物拟人全彩厚涂古风魔幻，草食肉食之战！画风一直在进步！如你觉得早期画风难以接受，请先用倒序去看看最后一章节（同人章除外）。 保证画风只会越来越好！本漫绝不收费！微博@玻璃小猫喵\n古风架空大陆，玄幻术法对抗机关机甲，大战一触即发。贫民窟狼少年，暴力病娇的虎妹妹，神秘腹黑的羊先生，谁将称霸？各种动物拟人，古风帅哥美女乱斗，动物迷的天堂，求收藏入书架！求月票！投月票可获福利图！详情请看评论区置顶","cornerInfo":"460","short_description":"狼兄虎妹腹黑羊","author_name":"玻璃小猫喵","is_vip":0},{"comicId":125814,"name":"明汐志","cover":"http://cover2.u17i.com/2017/06/19975_1497853377_p6v6UuGfp6vx.sbig.jpg","description":"时逢乱世，妖魔横行。\n百姓饱受其害，苦不堪言。\n朝廷为安定民心，随召集能人组一衙署，专司抓拿害人之精怪。\n此属衙之人统称为\u2014\u2014缉妖司。\n少年温良年幼时偶遇妖怪袭击，幸得缉妖司及时出手相救。从而对缉妖司心存崇拜，希望有朝一日能成为强大的缉妖司，抓拿破坏村子的妖怪，为父母报仇雪恨。\n长大后的少年，为了达成心愿，偷跑下山，参加一年一度的\u2018缉妖司考试\u2019。途中偶遇金牌缉妖司袁玥，从而展开了一系列的冒险故事。","cornerInfo":"74","short_description":"我要成为缉妖师","author_name":"波菜","is_vip":4},{"comicId":8805,"name":"虎x鹤 妖师录","cover":"http://cover2.u17i.com/2016/02/190368_1454325086_IwWP9cWNcoI9.sbig.jpg","description":"妖魔鬼怪，仙道妖法，奇珍异宝，一个降魔卫道的玄幻世界！","cornerInfo":"285","short_description":"修真世界虎鹤双形","author_name":"黄晓达","is_vip":4},{"comicId":119035,"name":"异种奇谭（全彩）","cover":"http://cover2.u17i.com/2016/03/4939604_1457870337_EKOZ2We27wTe.sbig.png","description":"    诡异的小镇，隐藏着不为人知的秘密，神秘母子的到来，是否能解开曾经埋葬的历史......","cornerInfo":"66","short_description":"诡异的小镇","author_name":"freewindgirl","is_vip":0}]
         * comicType : 6
         * titleIconUrl : http://image.mylife.u17t.com/2017/08/29/1503986106_7TY5gK000yjZ.png
         * newTitleIconUrl : http://image.mylife.u17t.com/2017/07/10/1499657929_N7oo9pPOhaYH.png
         * description : 更多
         * itemTitle : 强力推荐作品
         * argName : topic
         * argValue : 8
         * argType : 3
         * nowTime : 1510652902
         */

        private int comicType;
        private String titleIconUrl;
        private String newTitleIconUrl;
        private String description;
        private String itemTitle;
        private String argName;
        private int argValue;
        private int argType;
        private int nowTime;
        private List<ComicsBean> comics;

        public int getComicType() {
            return comicType;
        }

        public void setComicType(int comicType) {
            this.comicType = comicType;
        }

        public String getTitleIconUrl() {
            return titleIconUrl;
        }

        public void setTitleIconUrl(String titleIconUrl) {
            this.titleIconUrl = titleIconUrl;
        }

        public String getNewTitleIconUrl() {
            return newTitleIconUrl;
        }

        public void setNewTitleIconUrl(String newTitleIconUrl) {
            this.newTitleIconUrl = newTitleIconUrl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getItemTitle() {
            return itemTitle;
        }

        public void setItemTitle(String itemTitle) {
            this.itemTitle = itemTitle;
        }

        public String getArgName() {
            return argName;
        }

        public void setArgName(String argName) {
            this.argName = argName;
        }

        public int getArgValue() {
            return argValue;
        }

        public void setArgValue(int argValue) {
            this.argValue = argValue;
        }

        public int getArgType() {
            return argType;
        }

        public void setArgType(int argType) {
            this.argType = argType;
        }

        public int getNowTime() {
            return nowTime;
        }

        public void setNowTime(int nowTime) {
            this.nowTime = nowTime;
        }

        public List<ComicsBean> getComics() {
            return comics;
        }

        public void setComics(List<ComicsBean> comics) {
            this.comics = comics;
        }

        public static class ComicsBean {
            /**
             * comicId : 155045
             * name : 迷局
             * cover : http://cover2.u17i.com/2017/09/4165780_1506495643_ys9dsB2tyDoo.sbig.jpg
             * description : 一份真挚的友谊，一张张罪恶的面孔，在命运的面前，他们会怎么做，会面对现实，还是改变命运，各位读者拭目以待~~~
             * cornerInfo : 19
             * short_description : 看不透的人生道路
             * author_name : 逗鬼の鱼
             * is_vip : 0
             */

            private int comicId;
            private String name;
            private String cover;
            private String description;
            private String cornerInfo;
            private String short_description;
            private String author_name;
            private int is_vip;

            public int getComicId() {
                return comicId;
            }

            public void setComicId(int comicId) {
                this.comicId = comicId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCornerInfo() {
                return cornerInfo;
            }

            public void setCornerInfo(String cornerInfo) {
                this.cornerInfo = cornerInfo;
            }

            public String getShort_description() {
                return short_description;
            }

            public void setShort_description(String short_description) {
                this.short_description = short_description;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public int getIs_vip() {
                return is_vip;
            }

            public void setIs_vip(int is_vip) {
                this.is_vip = is_vip;
            }
        }
    }


}
