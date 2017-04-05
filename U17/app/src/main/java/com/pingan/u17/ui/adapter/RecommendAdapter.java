package com.pingan.u17.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/1
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final  int STYLE_VIEW_HEADER = 0;//广告栏
    public final  int STYLE_VIEW_MODEL_TITLE = 1;//模块标题
    public final  int STYLE_VIEW_ONE_MODE    = 3;//一模块
    public final  int STYLE_VIEW_SMALL_SECOND_MODE   = 2;//二模块 小
    public final  int STYLE_VIEW_SECOND_MODE   = 2;//二模块
    public final  int STYLE_VIEW_THREE_MODE   = 2;//三模块

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


    static  class OneViewHolder{
    }
}
