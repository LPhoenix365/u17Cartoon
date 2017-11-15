package com.pingan.u17.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.framework.FrescoImageUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.pingan.u17.R;
import com.pingan.u17.bean.GuessLikeBean;

import java.util.List;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/11/14
 */

public class GuessLikeAdapter extends BaseQuickAdapter<GuessLikeBean,BaseViewHolder> {


    public GuessLikeAdapter(@LayoutRes int layoutResId, @Nullable List<GuessLikeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GuessLikeBean item) {
            helper.setText(R.id.tv_work_name,item.name);
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.sv_work_cover);
        FrescoImageUtil.displayImgFromNetwork(simpleDraweeView,item.cover);
    }
}
