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
 * 文 件 名: PullToRefreshAdapter
 * 创 建 人: Allen
 * 创建日期: 16/12/24 19:55
 * 邮   箱: AllenCoder@126.com
 * 修改时间：
 * 修改备注：
 */
public class PullToRefreshAdapter extends BaseQuickAdapter<CommentBean, BaseViewHolder> {
    public PullToRefreshAdapter(@Nullable List<CommentBean> data) {
        super(R.layout.item_comment_list, data);
    }
    /*public PullToRefreshAdapter() {
        super(R.layout.item_comment_list, null);
    }*/

//    @Override
//    protected void convert(BaseViewHolder helper, Status item) {
//        switch (helper.getLayoutPosition()%
//                3){
//            case 0:
//                helper.setImageResource(R.id.img,R.mipmap.animation_img1);
//                break;
//            case 1:
//                helper.setImageResource(R.id.img,R.mipmap.animation_img2);
//                break;
//            case 2:
//                helper.setImageResource(R.id.img,R.mipmap.animation_img3);
//                break;
//        }
//        helper.setText(R.id.tweetName,"Hoteis in Rio de Janeiro");
//        String msg="\"He was one of Australia's most of distinguished artistes, renowned for his portraits\"";
//        ( (TextView)helper.getView(R.id.tweetText)).setText(SpannableStringUtils.getBuilder(msg).append("landscapes and nedes").setClickSpan(clickableSpan).create());
//        ( (TextView)helper.getView(R.id.tweetText)).setMovementMethod(LinkMovementMethod.getInstance());
//    }




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
