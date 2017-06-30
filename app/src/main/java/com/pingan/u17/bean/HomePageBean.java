package com.pingan.u17.bean;

import java.util.List;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/26
 */

public class HomePageBean extends BaseBean<HomePageBean> {

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

    public static class ReturnDataBean {
        private List<GalleryItemsBean> galleryItems;
        private List<Object>           textItems;
        private List<ComicListsBean>   comicLists;

        public List<GalleryItemsBean> getGalleryItems() {
            return galleryItems;
        }

        public void setGalleryItems(List<GalleryItemsBean> galleryItems) {
            this.galleryItems = galleryItems;
        }

        public List<Object> getTextItems() {
            return textItems;
        }

        public void setTextItems(List<Object> textItems) {
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
             * cover : http://image.mylife.u17t.com/2017/04/26/1493173754_Zy0oKjVMuk0f.jpg
             * id : 442
             * title :
             * content :
             * ext : [{"key":"comicId","val":"101412"}]
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
                 * val : 101412
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
             * comics : [{"comicId":84103,"name":"末日孢子","cover":"http://cover2.u17i.com/2014/11/6433_1415003670_42VlQHjwejdv.sbig.jpg","description":"也许你们不知道，外面究竟发生了什么事情。每个人都有自己想寻找的人，我也一样。每个人都有自己对不住的人，我也一样。我一直都没有勇敢过，直到现在。这是开始的时间，也是结束的时间。在一场末日瘟疫来临时，你如何生存下去？","cornerInfo":"90","short_description":"最后的幸存者们","author_name":"衍其门","is_vip":4},{"comicId":140212,"name":"豪门小老婆","cover":"http://cover2.u17i.com/2016/11/16549869_1479781393_BKHuuhzsGvLo.sbig.jpg","description":"【豪门小老婆粉丝群：458986220】一纸契约，一千万赎金，她沦为恶魔总裁的贴身近侍！曾经的千金小姐，如今的提线玩偶，他想尽办法折磨她，只为让她生下一个男孩，亲手报复她们家？！为逃狼窝，她学媚术，献香汗，誓要查清到底是谁在操纵她的人生\u2026\u2026","cornerInfo":"46","short_description":"一纸契约千万赎金","author_name":"左岸卡漫","is_vip":1},{"comicId":137742,"name":"幻师","cover":"http://cover2.u17i.com/2017/04/1303113_1492730167_NYNyjZ2n9n99.sbig.jpg","description":"世事纷扰，终成梦幻。竹月用自己的幻术解救了无数的人和灵，但却始终无法解脱自己的心。童年的阴影迫使他参与到别人的苦难里来逃避自己的过去。在一次次的拯救和自我提升的过程中，也在寻找着自己的答案。。。<br />\n幻师群 42808659  欢迎加入","cornerInfo":"21","short_description":"少年幻师平息冤魂","author_name":"简鹏飞","is_vip":0},{"comicId":119612,"name":"十字徒-CROSS","cover":"http://cover2.u17i.com/2016/05/98280_1462278145_13ZTc8nFXNx7.sbig.jpg","description":"公元2044年，一种神秘药物\u201cArachne（艾拉克因）\u201d经黑市流入我国，2年后位于上海的17岁高一学生林戒，于夜晚的\u201cTINX酒吧\u201d中遇到了一件不可思议的事件，从未有过的恐惧极速袭来......","cornerInfo":"53","short_description":"少年遭遇神秘之血","author_name":"周睿","is_vip":4},{"comicId":126263,"name":"食用兔子的正确方式","cover":"http://cover2.u17i.com/2016/11/14695313_1480135142_j184Z4SLzV8x.sbig.jpg","description":"喜欢上室友了怎么办，在线等，急！青泽亲自示范如何正确的撩到室友。大学日常向小条漫~讲述一只猫看上一只兔子然后追到手吃抹干净的故事。","cornerInfo":"44","short_description":"猫和兔子的室友关系","author_name":"酱香小龙虾","is_vip":0},{"comicId":121335,"name":"我的等级需要重新修炼","cover":"http://cover2.u17i.com/2016/11/6147427_1478086811_RjB525h11ujz.sbig.jpg","description":"原科沃大陆最强魔人，在失去魔力后与一位热血天真、勇于挑战世界的勇者相遇，携手完成各自理想，展开的青春、热血、冒险故事。黑白旬更作品（不是彩漫，只看彩漫的勿入坑），一般每月5、15、25左右更新\u2026\u2026","cornerInfo":"45","short_description":"魔力最强与热血勇者","author_name":"梓田AND梁茶","is_vip":4},{"comicId":135697,"name":"本草仙云之梦白蛇","cover":"http://cover2.u17i.com/2016/11/16670911_1478506667_k3KKWKWiM5z5.sbig.jpg","description":"许仙只身一人来到杭州，误入保安堂。？？白娘子还是那个白娘子，但这些分分钟变成美萌美萌妹子的中药是什么鬼哇！？？面对这么多的美女，许仙也不懂这到底还是不是个严肃认真的古装剧了！难道一不小心穿越到了后宫剧组里？鼻血都要出来了~~~~~","cornerInfo":"82","short_description":"许仙再续梦白蛇","author_name":"北京奇光影业","is_vip":0},{"comicId":77888,"name":"断罪小学","cover":"http://cover2.u17i.com/2017/01/2916913_1485265767_AL7jBZJ98bjw.sbig.jpg","description":"李杀神、王诛魔和刘斩仙三位少年做梦也没有想到会在狼牙区飞虎路私立断罪小学再度聚首，面对校园里号称屠遍三界的\u201c魔神\u201d，四年级的赵日天，他们能否力挽狂澜，再度改写被上天诅咒的命运呢？！","cornerInfo":"76","short_description":"我赵日天第一个不服","author_name":"糖小云","is_vip":4},{"comicId":54466,"name":"全世界都在谈恋爱","cover":"http://cover2.u17i.com/2016/08/571406_1471346032_sZfkG9eRkwt0.sbig.jpg","description":"不管身处哪一个时空，我喜欢的\u2026\u2026都是你。","cornerInfo":"70","short_description":"很久之前的青涩回忆","author_name":"二囬","is_vip":0}]
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
                 * comicId : 84103
                 * name : 末日孢子
                 * cover : http://cover2.u17i.com/2014/11/6433_1415003670_42VlQHjwejdv.sbig.jpg
                 * description : 也许你们不知道，外面究竟发生了什么事情。每个人都有自己想寻找的人，我也一样。每个人都有自己对不住的人，我也一样。我一直都没有勇敢过，直到现在。这是开始的时间，也是结束的时间。在一场末日瘟疫来临时，你如何生存下去？
                 * cornerInfo : 90
                 * short_description : 最后的幸存者们
                 * author_name : 衍其门
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

}
