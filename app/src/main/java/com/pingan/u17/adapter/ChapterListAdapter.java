package com.pingan.u17.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pingan.u17.R;
import com.pingan.u17.bean.ChapterBean;
import com.pingan.u17.bean.ChapterDetailBean;
import com.pingan.u17.bean.ChapterRealTimeBean;

import java.util.List;

/**
 * 章节列表
 *
 * @author liupeng502
 * @data 2017/11/14
 */

public class ChapterListAdapter extends BaseQuickAdapter<ChapterDetailBean, BaseViewHolder> {




    public ChapterListAdapter(@Nullable List<ChapterDetailBean> data) {
        super(R.layout.item_chapter_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChapterDetailBean item) {
        ChapterBean chapterBean = item.chapterListBean;
        if (chapterBean != null) {
            helper.setText(R.id.chapter_number, chapterBean.index);
            helper.setText(R.id.chapter_name, chapterBean.name);
            if (chapterBean.is_new != 0) {
                helper.getView(R.id.iv_chapter_new).setVisibility(View.VISIBLE);
            }
        }
        ChapterRealTimeBean timeDetailBean = item.chapterRealTimeDetailBean;
        if (timeDetailBean != null) {
            if (!TextUtils.equals("0", timeDetailBean.buyed)) {
                helper.getView(R.id.iv_chapter_pay).setVisibility(View.VISIBLE);
            }
            if (TextUtils.equals("0", timeDetailBean.vip_images)) {
                helper.getView(R.id.iv_chapter_vip).setVisibility(View.VISIBLE);
            }
        }

    }
}
