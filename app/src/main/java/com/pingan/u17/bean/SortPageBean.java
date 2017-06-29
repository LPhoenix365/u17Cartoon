package com.pingan.u17.bean;

import java.util.List;

/**
 * Description 分类
 *
 * @author liupeng502
 * @data 2017/6/29
 */
public class SortPageBean extends BaseBean<DataBean>{

    public static class DataBean {
        /**
         * stateCode : 1
         * message : 成功
         * returnData : {"rankingList":[{"sortId":131,"sortName":"少年","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471858828_59HmtKlz999X.jpg","canEdit":false,"argName":"cate","argValue":1},{"sortId":124,"sortName":"少女","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856885_EMUYMYcd1zcc.jpg","canEdit":false,"argName":"cate","argValue":2},{"sortId":125,"sortName":"生活","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856830_02bblPzwP5P5.jpg","canEdit":false,"argName":"theme","argValue":3},{"sortId":129,"sortName":"惊悚","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471857028_F78hO8o9dbuW.jpg","canEdit":true,"argName":"theme","argValue":11},{"sortId":130,"sortName":"耽美","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471858681_PikE4P2b4R29.jpg","canEdit":true,"argName":"theme","argValue":10},{"sortId":128,"sortName":"小说改编","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856898_l2y4rVvpr225.jpg","canEdit":true,"argName":"special","argValue":128,"argCon":0},{"sortId":132,"sortName":"恋爱","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471860696_4D98T4m08984.jpg","canEdit":true,"argName":"theme","argValue":4},{"sortId":116,"sortName":"魔幻","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856737_F60y496YfAS4.jpg","canEdit":true,"argName":"theme","argValue":2},{"sortId":120,"sortName":"搞笑","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471858264_ttzW85KDmZgw.jpg","canEdit":true,"argName":"theme","argValue":1},{"sortId":118,"sortName":"动作","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856769_Hcqqj695V911.jpg","canEdit":true,"argName":"theme","argValue":5},{"sortId":122,"sortName":"科幻","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856818_7Gg6526tYG62.jpg","canEdit":true,"argName":"theme","argValue":6},{"sortId":126,"sortName":"同人","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856871_o5VKzWWNI4Pm.jpg","canEdit":true,"argName":"theme","argValue":12},{"sortId":123,"sortName":"四格","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856914_aDtATdb2DT2A.jpg","canEdit":true,"argName":"cate","argValue":4},{"sortId":121,"sortName":"古风","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856799_g34OsbI4PSeb.jpg","canEdit":true,"argName":"special","argValue":121,"argCon":0},{"sortId":117,"sortName":"穿越","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856751_ss0I63WCzdkN.jpg","canEdit":true,"argName":"special","argValue":117,"argCon":0}],"topList":[{"sortId":"119","sortName":"独家签约","cover":"http://static.u17i.com/app/sort/dujia.png","extra":{"title":"独家签约","tabList":[{"argName":"topic","argValue":15,"argCon":4,"tabTitle":"今日最热"},{"argName":"topic","argValue":15,"argCon":1,"tabTitle":"人气总榜"}]},"uiWeight":2},{"sortId":"133","sortName":"彩漫作品","cover":"http://static.u17i.com/app/sort/caiman.png","extra":{"title":"彩漫作品","tabList":[{"argName":"topic","argValue":7,"argCon":4,"tabTitle":"今日最热"},{"argName":"topic","argValue":7,"argCon":1,"tabTitle":"人气总榜"}]},"uiWeight":2},{"sortId":"127","sortName":"完结作品","cover":"http://static.u17i.com/app/sort/wanjie.png","extra":{"title":"完结作品","tabList":[{"argName":"serial","argValue":1,"argCon":4,"tabTitle":"今日最热"},{"argName":"serial","argValue":1,"argCon":1,"tabTitle":"人气总榜"}]},"uiWeight":2}],"recommendSearch":"镇魂街"}
         */

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
            /**
             * rankingList : [{"sortId":131,"sortName":"少年","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471858828_59HmtKlz999X.jpg","canEdit":false,"argName":"cate","argValue":1},{"sortId":124,"sortName":"少女","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856885_EMUYMYcd1zcc.jpg","canEdit":false,"argName":"cate","argValue":2},{"sortId":125,"sortName":"生活","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856830_02bblPzwP5P5.jpg","canEdit":false,"argName":"theme","argValue":3},{"sortId":129,"sortName":"惊悚","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471857028_F78hO8o9dbuW.jpg","canEdit":true,"argName":"theme","argValue":11},{"sortId":130,"sortName":"耽美","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471858681_PikE4P2b4R29.jpg","canEdit":true,"argName":"theme","argValue":10},{"sortId":128,"sortName":"小说改编","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856898_l2y4rVvpr225.jpg","canEdit":true,"argName":"special","argValue":128,"argCon":0},{"sortId":132,"sortName":"恋爱","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471860696_4D98T4m08984.jpg","canEdit":true,"argName":"theme","argValue":4},{"sortId":116,"sortName":"魔幻","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856737_F60y496YfAS4.jpg","canEdit":true,"argName":"theme","argValue":2},{"sortId":120,"sortName":"搞笑","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471858264_ttzW85KDmZgw.jpg","canEdit":true,"argName":"theme","argValue":1},{"sortId":118,"sortName":"动作","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856769_Hcqqj695V911.jpg","canEdit":true,"argName":"theme","argValue":5},{"sortId":122,"sortName":"科幻","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856818_7Gg6526tYG62.jpg","canEdit":true,"argName":"theme","argValue":6},{"sortId":126,"sortName":"同人","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856871_o5VKzWWNI4Pm.jpg","canEdit":true,"argName":"theme","argValue":12},{"sortId":123,"sortName":"四格","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856914_aDtATdb2DT2A.jpg","canEdit":true,"argName":"cate","argValue":4},{"sortId":121,"sortName":"古风","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856799_g34OsbI4PSeb.jpg","canEdit":true,"argName":"special","argValue":121,"argCon":0},{"sortId":117,"sortName":"穿越","isLike":true,"cover":"http://image.mylife.u17t.com/2016/08/22/1471856751_ss0I63WCzdkN.jpg","canEdit":true,"argName":"special","argValue":117,"argCon":0}]
             * topList : [{"sortId":"119","sortName":"独家签约","cover":"http://static.u17i.com/app/sort/dujia.png","extra":{"title":"独家签约","tabList":[{"argName":"topic","argValue":15,"argCon":4,"tabTitle":"今日最热"},{"argName":"topic","argValue":15,"argCon":1,"tabTitle":"人气总榜"}]},"uiWeight":2},{"sortId":"133","sortName":"彩漫作品","cover":"http://static.u17i.com/app/sort/caiman.png","extra":{"title":"彩漫作品","tabList":[{"argName":"topic","argValue":7,"argCon":4,"tabTitle":"今日最热"},{"argName":"topic","argValue":7,"argCon":1,"tabTitle":"人气总榜"}]},"uiWeight":2},{"sortId":"127","sortName":"完结作品","cover":"http://static.u17i.com/app/sort/wanjie.png","extra":{"title":"完结作品","tabList":[{"argName":"serial","argValue":1,"argCon":4,"tabTitle":"今日最热"},{"argName":"serial","argValue":1,"argCon":1,"tabTitle":"人气总榜"}]},"uiWeight":2}]
             * recommendSearch : 镇魂街
             */

            private String recommendSearch;
            private List<RankingListBean> rankingList;
            private List<TopListBean>     topList;

            public String getRecommendSearch() {
                return recommendSearch;
            }

            public void setRecommendSearch(String recommendSearch) {
                this.recommendSearch = recommendSearch;
            }

            public List<RankingListBean> getRankingList() {
                return rankingList;
            }

            public void setRankingList(List<RankingListBean> rankingList) {
                this.rankingList = rankingList;
            }

            public List<TopListBean> getTopList() {
                return topList;
            }

            public void setTopList(List<TopListBean> topList) {
                this.topList = topList;
            }

            public static class RankingListBean {
                /**
                 * sortId : 131
                 * sortName : 少年
                 * isLike : true
                 * cover : http://image.mylife.u17t.com/2016/08/22/1471858828_59HmtKlz999X.jpg
                 * canEdit : false
                 * argName : cate
                 * argValue : 1
                 * argCon : 0
                 */

                private int sortId;
                private String  sortName;
                private boolean isLike;
                private String  cover;
                private boolean canEdit;
                private String  argName;
                private int     argValue;
                private int     argCon;

                public int getSortId() {
                    return sortId;
                }

                public void setSortId(int sortId) {
                    this.sortId = sortId;
                }

                public String getSortName() {
                    return sortName;
                }

                public void setSortName(String sortName) {
                    this.sortName = sortName;
                }

                public boolean isIsLike() {
                    return isLike;
                }

                public void setIsLike(boolean isLike) {
                    this.isLike = isLike;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public boolean isCanEdit() {
                    return canEdit;
                }

                public void setCanEdit(boolean canEdit) {
                    this.canEdit = canEdit;
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

                public int getArgCon() {
                    return argCon;
                }

                public void setArgCon(int argCon) {
                    this.argCon = argCon;
                }
            }

            public static class TopListBean {
                /**
                 * sortId : 119
                 * sortName : 独家签约
                 * cover : http://static.u17i.com/app/sort/dujia.png
                 * extra : {"title":"独家签约","tabList":[{"argName":"topic","argValue":15,"argCon":4,"tabTitle":"今日最热"},{"argName":"topic","argValue":15,"argCon":1,"tabTitle":"人气总榜"}]}
                 * uiWeight : 2
                 */

                private String sortId;
                private String    sortName;
                private String    cover;
                private ExtraBean extra;
                private int       uiWeight;

                public String getSortId() {
                    return sortId;
                }

                public void setSortId(String sortId) {
                    this.sortId = sortId;
                }

                public String getSortName() {
                    return sortName;
                }

                public void setSortName(String sortName) {
                    this.sortName = sortName;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public ExtraBean getExtra() {
                    return extra;
                }

                public void setExtra(ExtraBean extra) {
                    this.extra = extra;
                }

                public int getUiWeight() {
                    return uiWeight;
                }

                public void setUiWeight(int uiWeight) {
                    this.uiWeight = uiWeight;
                }

                public static class ExtraBean {
                    /**
                     * title : 独家签约
                     * tabList : [{"argName":"topic","argValue":15,"argCon":4,"tabTitle":"今日最热"},{"argName":"topic","argValue":15,"argCon":1,"tabTitle":"人气总榜"}]
                     */

                    private String title;
                    private List<TabListBean> tabList;

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public List<TabListBean> getTabList() {
                        return tabList;
                    }

                    public void setTabList(List<TabListBean> tabList) {
                        this.tabList = tabList;
                    }

                    public static class TabListBean {
                        /**
                         * argName : topic
                         * argValue : 15
                         * argCon : 4
                         * tabTitle : 今日最热
                         */

                        private String argName;
                        private int    argValue;
                        private int    argCon;
                        private String tabTitle;

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

                        public int getArgCon() {
                            return argCon;
                        }

                        public void setArgCon(int argCon) {
                            this.argCon = argCon;
                        }

                        public String getTabTitle() {
                            return tabTitle;
                        }

                        public void setTabTitle(String tabTitle) {
                            this.tabTitle = tabTitle;
                        }
                    }
                }
            }
        }
    }
}
