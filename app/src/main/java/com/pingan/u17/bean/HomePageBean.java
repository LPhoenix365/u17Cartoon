package com.pingan.u17.bean;

import java.util.List;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/26
 */

public class HomePageBean extends BaseBean<HomePageBean> {




        private int stateCode;
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
                 * cover : http://image.mylife.u17t.com/2017/06/30/1498817823_202beQLMF1q3.jpg
                 * id : 539
                 * title :
                 * content :
                 * ext : [{"key":"comicId","val":"113920"}]
                 */

                private int linkType;
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
                     * val : 113920
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
                 * comics : [{"comicId":57524,"name":"尚善","cover":"http://cover2.u17i.com/2017/01/3674833_1484402043_u3c6Hj2z9y0h.sbig.jpg","description":"大概就是一个怪蜀黎收了一个怪萝莉为徒，然后遇到各种怪人怪物。。。故事是治愈向的（大概。。。），没有神仙也没有打大妖怪，没有帝王也没有盟主，都是些平民百姓的小故事。（独家签约/逢10更新）","cornerInfo":"123","short_description":"神异鬼怪物语","author_name":"蒋家梅子","is_vip":4},{"comicId":71064,"name":"斗罗大陆","cover":"http://cover2.u17i.com/2017/02/4276398_1487647067_Dd8E8C6ExsXj.sbig.jpg","description":"偷学绝顶武功的唐三跳崖明志，转世后却依然是令人艳羡的正太一枚 ！他带着天生双武魂先天满魄力的天赋走进了诺丁学院，遇到了野蛮可爱的萝莉小舞，血雨腥风加儿女情长。《斗罗大陆》漫画唯一杂志和单行本连载、出版方为《风炫漫画》。好看的漫画必须收入书架哦！","cornerInfo":"347","short_description":"武魂觉醒修炼升级吧","author_name":"上海风炫动画","is_vip":2},{"comicId":114373,"name":"当不良老大的男人","cover":"http://cover2.u17i.com/2017/04/775668_1493046244_VPSdoOP5PPUE.sbig.jpg","description":"【周六更新】这是一个有点傻白甜的耽美恋爱故事。\n高校三好学生徐青松因为打架斗殴事件结识了不良少年朱晨，对他一见钟情。\n因为种种原因，徐青松成为了帮派老大，攻略现二把手朱晨，并带领着海花帮横行霸\u2026啊不对，整顿泰方镇。\n","cornerInfo":"26","short_description":"不良老大傻白甜日常","author_name":"lamatter","is_vip":4},{"comicId":64171,"name":"妖闻录","cover":"http://cover2.u17i.com/2015/08/661165_1439557376_Z4COHfihyOZc.sbig.jpg","description":"小道下山开始人间的修行，临行前老道长给了他一个卷轴，卷轴里面记载着众多妖怪的名字和属性，需要时可以召唤，但要满足一定的条件才可以，小道好不容易才摸清楚了规律，又卷入各式各样的妖怪事件里","cornerInfo":"129","short_description":"修道除妖 卷轴召唤","author_name":"小铜钱","is_vip":4},{"comicId":71084,"name":"一万零七夜-彩漫周更-","cover":"http://cover2.u17i.com/2016/08/3126292_1470427197_wW6b7wwbd8DO.sbig.jpg","description":"动物拟人的全彩厚涂古风魔幻故事，草食与肉食之战！画风一直在进步，假如你觉得早期画风难以接受，请先用倒序去看看最后一章节（同人章除外）。 保证画风只会越来越好。微博@玻璃小猫喵 \n古风架空大陆上，玄幻术法对抗着机关机甲，大战一触即发。贫民窟的狼少年，暴力病娇的虎妹妹，神秘腹黑的羊先生，谁将称霸？各种动物拟人，古风帅哥美女乱斗，动物迷的天堂，求收藏入书架！投喂月票可获福利图哦！详情请看评论区置顶...","cornerInfo":"382","short_description":"狼兄虎妹腹黑羊","author_name":"玻璃小猫喵","is_vip":0},{"comicId":84047,"name":"教主哪里跑","cover":"http://cover2.u17i.com/2017/05/4118405_1493957661_k9zosFY4jXAX.sbig.jpg","description":"【第一部 武林盟篇】教主哪里跑！初出茅庐的武林盟主遇上狡猾的魔教教主，一边愉快地家暴一边联手抗敌！HE，大长篇，正常情況下周更8页，繁忙时期4页，绝对不坑~","cornerInfo":"138","short_description":"武林盟主追妻记","author_name":"意逸SesamE","is_vip":0},{"comicId":73083,"name":"开元秘史","cover":"http://cover2.u17i.com/2017/04/4347148_1492135079_ZHMp7C0ph1PR.sbig.jpg","description":"盛唐浮华背后的欲、爱、权、谋。原名《开元异闻录》，非耽美正剧向【只是主角刚好喜欢搞基(咦?)】第十九章起每周六全彩更新。","cornerInfo":"92","short_description":"波澜壮阔的盛唐秘辛","author_name":"废柴阿布","is_vip":4},{"comicId":119035,"name":"异种奇谭（全彩）","cover":"http://cover2.u17i.com/2016/03/4939604_1457870337_EKOZ2We27wTe.sbig.png","description":"    诡异的小镇，隐藏着不为人知的秘密，神秘母子的到来，是否能解开曾经埋葬的历史......","cornerInfo":"47","short_description":"诡异的小镇","author_name":"freewindgirl","is_vip":0}]
                 * comicType : 6
                 * titleIconUrl : http://image.mylife.u17t.com/2016/10/20/1476960411_i5CV3HL4bVc5.png
                 * newTitleIconUrl : http://image.mylife.u17t.com/2017/01/12/1484202287_RuhGzTuyDejJ.png
                 * description : 更多
                 * itemTitle : 强力推荐作品
                 * argName : topic
                 * argValue : 8
                 * argType : 3
                 */

                private int comicType;
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
                     * comicId : 57524
                     * name : 尚善
                     * cover : http://cover2.u17i.com/2017/01/3674833_1484402043_u3c6Hj2z9y0h.sbig.jpg
                     * description : 大概就是一个怪蜀黎收了一个怪萝莉为徒，然后遇到各种怪人怪物。。。故事是治愈向的（大概。。。），没有神仙也没有打大妖怪，没有帝王也没有盟主，都是些平民百姓的小故事。（独家签约/逢10更新）
                     * cornerInfo : 123
                     * short_description : 神异鬼怪物语
                     * author_name : 蒋家梅子
                     * is_vip : 4
                     */

                    private int comicId;
                    private String name;
                    private String cover;
                    private String description;
                    private String cornerInfo;
                    private String short_description;
                    private String author_name;
                    private int    is_vip;
                    private String[] tags;

                    public String[] getTags() {
                        return tags;
                    }

                    public void setTags(String[] tags) {
                        this.tags = tags;
                    }
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
