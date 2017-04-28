package com.example.framework;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by liupeng502 on 2017/3/10.
 */


public class LoadingLayout extends FrameLayout {

    private Context context;
    private int     loading_layout;
    private int     error_layout_net_fail;
    private int     error_layout_no_data_fail;
    private View    loadingLayout;
    private View    errorLayout, nodataLayout;
    private OnClickListener refreshLayoutLisenter;

    public LoadingLayout(Context context) {
        this(context, null);

    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.loadingLayout);
            if (typedArray != null) {
                loading_layout = typedArray.getResourceId(R.styleable.loadingLayout_loading_layout, R.layout.loading_layout);
                error_layout_net_fail = typedArray.getResourceId(R.styleable.loadingLayout_error_layout_net_fail, R.layout.error_layout_net_fail);
                error_layout_no_data_fail = typedArray.getResourceId(R.styleable.loadingLayout_error_layout_no_data_fail, R.layout.error_layout_net_fail);
                typedArray.recycle();
            }
        }
    }
    /**
     * 缺失setUpPartProcessLayout 局部更新功能
     */

    /**
     * 显示加载中转圈
     */
    public void showProcess() {
        if (loadingLayout == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            loadingLayout = inflater.inflate(loading_layout, null); //inflater.inflate(loading_layout,this);直接add不能设置布局参数
            addView(loadingLayout, new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.CENTER));
        }
        /*  不如放下面好
        loadingLayout.setVisibility(View.VISIBLE);
        loadingLayout.bringToFront();*/
        if (errorLayout != null) {
            errorLayout.setVisibility(View.GONE);
        }
        if (nodataLayout != null) {
            nodataLayout.setVisibility(View.GONE);
        }
        loadingLayout.setVisibility(View.VISIBLE);
        loadingLayout.bringToFront();
    }


    /**
     * 显示加载失败时 网络问题
     */
    public void showErrorNet() {
        if (errorLayout == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            errorLayout = inflater.inflate(error_layout_net_fail, null);
            if (refreshLayoutLisenter != null) {
                errorLayout.findViewById(R.id.error_btn).setOnClickListener(refreshLayoutLisenter);
            }
        }
        addView(errorLayout, new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.CENTER));
        if (loadingLayout != null) {
            loadingLayout.setVisibility(View.GONE);
        }
        if (nodataLayout != null) {
            nodataLayout.setVisibility(View.GONE);
        }
        errorLayout.setVisibility(VISIBLE);
        errorLayout.bringToFront();
    }


    /**
     * 显示加载失败时 网络问题
     */
    public void showErrorNoData() {
        if (nodataLayout == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            nodataLayout = inflater.inflate(error_layout_no_data_fail, null);
            if (refreshLayoutLisenter != null) {
                nodataLayout.findViewById(R.id.error_btn).setOnClickListener(refreshLayoutLisenter);
            }
        }
        addView(nodataLayout, new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.CENTER));
        if (loadingLayout != null) {
            loadingLayout.setVisibility(View.GONE);
        }
        if (errorLayout != null) {
            errorLayout.setVisibility(View.GONE);
        }
        nodataLayout.setVisibility(VISIBLE);
        nodataLayout.bringToFront();
    }

    /**
     * 监听重试的接口
     使用自带的 不定义自己的了
    interface RefreshLayoutLisenter {
        void onRefreshLayout();
    }*/

    public void setRefreshLayoutLisenter(OnClickListener lisenter) {
        this.refreshLayoutLisenter = lisenter;
    }

}
