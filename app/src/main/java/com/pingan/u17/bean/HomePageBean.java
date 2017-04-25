package com.pingan.u17.bean;

import java.util.List;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/24
 */

public class HomePageBean extends BaseBean {


    private List<GalleryItemsBean> galleryItems;
    private List<?>                textItems;
    private List<ComicListsBean>   comicLists;

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
         * cover : http://image.mylife.u17t.com/2017/04/21/1492770076_o4FE4H94oeNT.jpg
         * id : 438
         * title :
         * content :
         * ext : [{"key":"comicId","val":"130468"}]
         */

        private int           linkType;
        private String        cover;
        private int           id;
        private String        title;
        private String        content;
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

        public List<ExtBean> getExt() {
            return ext;
        }

        public void setExt(List<ExtBean> ext) {
            this.ext = ext;
        }

        public static class ExtBean {
            /**
             * key : comicId
             * val : 130468
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
         * comics : [{"comicId":75050,"name":"无常道","cover":"http://cover2.u17i.com/2014/11/4435808_1415849840_7udguZSSHuUx.sbig.jpg","description":"在阳间时是一体同生的状态（黑主昼白主夜），一旦丢失隐形用的佩玉便只能依靠伪装用人皮道具应付过关，围绕嫡庶地府第五殿阴司长，笑面狐的大蛾子白无常与擅长弄丢刑具的黑无常所展开的故事\u2026\u2026以二者之目注视世间百态","cornerInfo":"95","short_description":"道无常道 法无常法","author_name":"MYstery落木","is_vip":4},{"comicId":143822,"name":"皇帝的独生女","cover":"http://cover2.u17i.com/2017/03/16600503_1490697200_b68do822228q.sbig.jpg","description":"在异世重生后，我成为了奥格利帝国的唯一皇女。然而从出生的那一刻起，我就被充满了同情的视线包围着。理由只有一个\u2014\u2014我爸是个疯子。通过叛乱登上皇位的反王，让整个大陆陷入恐慌的稀世暴君。有一个杀妻杀子都是小菜一碟的爸爸\u2026\u2026我真的能活下来吗？","cornerInfo":"71","short_description":"异世重生成皇女","author_name":"多蕴文化","is_vip":2},{"comicId":138652,"name":"长安异事","cover":"http://cover2.u17i.com/2016/10/2033228_1477363754_IEFDemIjIl4e.sbig.jpg","description":"繁华古韵的大司空城中，年轻的稷卫军卫长孟长安，守卫着皇城的一方和平，但最近妖魔作祟的案件却时常在附近发生，一度消弭于世的妖魔为何重出人间？\r\n稷卫军孟长安，与古玩店老板柳西臣\u2026\u2026相互试探，又互相关联，是在惺惺相惜的信任与友情，还是故意示好的伪装与欺骗？断诡案，降妖魔，一段段异闻奇事，就此展开\u2026\u2026","cornerInfo":"18","short_description":"古风耽美探案集","author_name":"二花＆文子","is_vip":0},{"comicId":75229,"name":"第九世界","cover":"http://cover2.u17i.com/2015/07/1991528_1436935132_MFcNMlP01lJ2.sbig.jpg","description":"精分少年学做人（并不是","cornerInfo":"105","short_description":"异能者的舞动","author_name":"Winslow","is_vip":0},{"comicId":38368,"name":"超能领域","cover":"http://cover2.u17i.com/2015/03/1594_1426841669_83yyd3hZ3h2Z.sbig.jpg","description":"15年前，一起悬崖上的意外车祸，一桩看似奇迹的新闻，却在潜移默化间，改变了49个人的人生轨迹。\r\n15年后，当\u201c死神\u201d再一次将阴湿的腐手触及他们的身边，埋藏多年的秘密终于浮出水面，49个人重重陷入巨大的迷局之中，逃脱不得。\r\n为了拨开眼前的重重迷雾，大隐于世的少年们不得不重新团结在一起，用他们异于常人的力量与恶魔般的暗黑势力展开激烈对抗\u2026\u2026\r\n","cornerInfo":"120","short_description":"超越死亡的超能大乱斗","author_name":"达特尼安","is_vip":4},{"comicId":98191,"name":"地狱（全彩）","cover":"http://cover2.u17i.com/2015/05/3965621_1432901113_y7T7JXky4koY.sbig.jpg","description":"残酷的地狱各种刑罚在等待犯错的人哦~~温馨提示~年龄小的需请家长陪同下观看，不可以模仿故事里的情节哦~","cornerInfo":"94","short_description":"来自地狱的魔鬼","author_name":"龙飞虎lsk","is_vip":0},{"comicId":108569,"name":"怪事界","cover":"http://cover2.u17i.com/2016/12/4854735_1480597276_O454NjJ5sBFh.sbig.jpg","description":"怪事界为现实题材作品，故事内容都有现实基础，内容多为我们身边故事提炼而成，既有生活化的东西，同时又高于生活。故事内容不恐怖，并非恐怖漫画。希望大家能喜欢吧，可以根据目录选择自己喜欢的内容阅读。每周日下午更新","cornerInfo":"101","short_description":"那些怪事的集合","author_name":"G\u2022杰明","is_vip":0},{"comicId":101412,"name":"我的不靠谱王子殿下","cover":"http://cover2.u17i.com/2016/06/14288508_1465970002_zoQV6TtX22wL.sbig.jpg","description":"当一心成为漫画家却屡屡碰壁的她，遇到了除了脸靠谱，剩下什么都不靠谱的他，会不会撞击出新的火花？！\r\n嘿嘿，女汉子遇上嘚瑟二世祖，到底是冤家路窄还是命中注定，等等！天下大同什么的才是本命啊！！！\r\n","cornerInfo":"172","short_description":"女汉子的恋爱革命！","author_name":"南宁金号角","is_vip":4},{"comicId":83541,"name":"非君不可","cover":"http://cover2.u17i.com/2017/03/4506967_1488340117_6nTt998A1AiI.sbig.jpg","description":"据说这是一个集架空、穿越、宫廷、武侠于一身的古风爱情少女漫？！（这是什么鬼？）\r\n在校女大学生安小俞因一次兼职的关系，灵魂意外穿越到一个架空的未知古代世界，成为了当朝礼部尚书的二千金曹诗诗。在尚书府，会有神马惊（奇）险（葩）刺（坑）激（爹）的事情等着她呢\u2026\u2026","cornerInfo":"77","short_description":"此生穿越只为见君","author_name":"朵朵啦","is_vip":4}]
         * comicType : 6
         * titleIconUrl : http://image.mylife.u17t.com/2016/10/20/1476960411_i5CV3HL4bVc5.png
         * newTitleIconUrl : http://image.mylife.u17t.com/2017/01/12/1484202287_RuhGzTuyDejJ.png
         * description : 更多
         * itemTitle : 强力推荐作品
         * argName : topic
         * argValue : 8
         * argType : 3
         */

        private int              comicType;
        private String           titleIconUrl;
        private String           newTitleIconUrl;
        private String           description;
        private String           itemTitle;
        private String           argName;
        private int              argValue;
        private int              argType;
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

        public List<ComicsBean> getComics() {
            return comics;
        }

        public void setComics(List<ComicsBean> comics) {
            this.comics = comics;
        }

        public static class ComicsBean {
            /**
             * comicId : 75050
             * name : 无常道
             * cover : http://cover2.u17i.com/2014/11/4435808_1415849840_7udguZSSHuUx.sbig.jpg
             * description : 在阳间时是一体同生的状态（黑主昼白主夜），一旦丢失隐形用的佩玉便只能依靠伪装用人皮道具应付过关，围绕嫡庶地府第五殿阴司长，笑面狐的大蛾子白无常与擅长弄丢刑具的黑无常所展开的故事……以二者之目注视世间百态
             * cornerInfo : 95
             * short_description : 道无常道 法无常法
             * author_name : MYstery落木
             * is_vip : 4
             */

            private int    comicId;
            private String name;
            private String cover;
            private String description;
            private String cornerInfo;
            private String short_description;
            private String author_name;
            private int    is_vip;

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
