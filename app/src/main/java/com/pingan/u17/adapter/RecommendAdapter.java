package com.pingan.u17.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/1
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final int STYLE_VIEW_HEADER            = 1;//广告栏
    public final int STYLE_VIEW_MODEL_TITLE       = 2;//模块标题
    public final int STYLE_VIEW_ONE_MODE          = 3;//一模块
    public final int STYLE_VIEW_SMALL_SECOND_MODE = 4;//二模块 小
    public final int STYLE_VIEW_SECOND_MODE       = 5;//二模块
    public final int STYLE_VIEW_THREE_MODE        = 6;//三模块

    public RecommendAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class OneViewHolder extends RecyclerView.ViewHolder {

        public OneViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    class TwoViewHolder extends RecyclerView.ViewHolder {

        public TwoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class TwoSmallViewHolder extends RecyclerView.ViewHolder {

        public TwoSmallViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ThreeViewHolder extends RecyclerView.ViewHolder {

        public ThreeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
