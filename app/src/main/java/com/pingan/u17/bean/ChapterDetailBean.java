package com.pingan.u17.bean;

/**
 * 给adapter使用
 *
 * @author liupeng502
 * @data 2017/11/15
 */

public class ChapterDetailBean extends BaseBean {
    public ChapterRealTimeBean chapterRealTimeDetailBean;
    public ChapterBean chapterListBean;

    public ChapterDetailBean() {

    }

    public ChapterDetailBean(ChapterBean chapterListBean, ChapterRealTimeBean chapterRealTimeDetailBean) {
        this.chapterRealTimeDetailBean = chapterRealTimeDetailBean;
        this.chapterListBean = chapterListBean;
    }

}
