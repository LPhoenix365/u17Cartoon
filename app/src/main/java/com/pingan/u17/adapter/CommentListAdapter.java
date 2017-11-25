package com.pingan.u17.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.framework.FrescoImageUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.pingan.u17.R;
import com.pingan.u17.bean.CommentBean;
import com.pingan.u17.util.TimeUtil;

import java.util.List;

/**
 * 评论详情
 *
 * @author liupeng502
 * @data 2017/11/14
 */

public class CommentListAdapter extends BaseQuickAdapter<CommentBean, BaseViewHolder> {


    public CommentListAdapter(@Nullable List<CommentBean> data) {
        super(R.layout.item_comment_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentBean item) {
        SimpleDraweeView face = helper.getView(R.id.iv_face);
        FrescoImageUtil.displayImgFromNetwork(face, item.face);
        TextView textView = helper.getView(R.id.tv_comment);
        Spanned sp = Html.fromHtml(item.content,null,null);
        textView.setText(sp);
        helper.setText(R.id.tv_nickname, item.nickname);
        helper.setText(R.id.tv_comment_time, TimeUtil.getPrintTimeFormat4(Long.valueOf(item.create_time)*1000));
        helper.setText(R.id.tv_favorite, String.valueOf(item.likeCount));
        helper.setText(R.id.tv_write_comment, String.valueOf(item.total_reply));
    }
}
