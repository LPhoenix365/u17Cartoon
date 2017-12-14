package com.pingan.u17.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pingan.u17.R;

import java.util.List;

/**
 * 章节列表
 *
 * @author liupeng502
 * @data 2017/11/14
 */

public class ReaddingAdapter extends BaseQuickAdapter<String, BaseViewHolder> {




    public ReaddingAdapter(@Nullable List<String> data) {
        super(R.layout.item_chapter_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
