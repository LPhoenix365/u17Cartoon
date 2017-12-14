package com.pingan.u17.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pingan.u17.R;

/**
 * @Author Zheng Haibo
 * @PersonalWebsite http://www.mobctrl.net
 * @Description 自定义CustomeSwipeRefreshLayout<br>
 * 支持下拉刷新和上拉加载更多<br>
 * 非侵入式，对原来的ListView、RecyclerView没有任何影响,用法和SwipeRefreshLayout类似。<br>
 * 可自定义头部View的样式，调用setHeaderView方法即可 <br>
 * 可自定义页尾View的样式，调用setFooterView方法即可 <br>
 * 支持RecyclerView，ListView，ScrollView，GridView等等。<br>
 * 被包含的View(RecyclerView,ListView etc.)可跟随手指的滑动而滑动<br>
 * 默认是跟随手指的滑动而滑动，也可以设置为不跟随：setTargetScrollWithLayout(false) 回调方法更多<br>
 * 比如：onRefresh() onPullDistance(int distance)和onPullEnable(boolean
 * enable)<br>
 * 开发人员可以根据下拉过程中distance的值做一系列动画。 <br>
 */
@SuppressLint("ClickableViewAccessibility")
public class SwipeRefreshLayout extends FrameLayout {

    private ProgressBar footerProgressBar;
    private TextView textView;
    private ImageView imageView;
    private ProgressBar progressBar;
    private TextView footerTextView;
    private ImageView footerImageView;
    private SuperSwipeRefreshLayout3 refreshLayout;

    public SwipeRefreshLayout(@NonNull Context context) {
        this(context, null);
    }

    public SwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        refreshLayout = new SuperSwipeRefreshLayout3(context);
        refreshLayout.setHeaderView(createHeaderView());// add headerView
        refreshLayout.setFooterView(createFooterView());
        addLisenter();
        addView(refreshLayout,  new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        /*int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            refreshLayout.addView(getChildAt(i));
        }*/
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return refreshLayout.onTouchEvent(event);
    }

    private void addLisenter() {
        refreshLayout
                .setOnPullRefreshListener(new SuperSwipeRefreshLayout3.OnPullRefreshListener() {
                    @Override
                    public void onRefresh() {
                        textView.setText("正在刷新");
                        imageView.setVisibility(View.GONE);
                        progressBar.setVisibility(View.VISIBLE);
                        if (mOnRefreshListener2 != null) {
                            mOnRefreshListener2.onPullDownToRefresh(refreshLayout);
                        }
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                refreshLayout.setRefreshing(false);
                                progressBar.setVisibility(View.GONE);
                            }
                        }, 2000);
                    }

                    @Override
                    public void onPullDistance(int distance) {
                        // pull distance
                    }

                    @Override
                    public void onPullEnable(boolean enable) {
                        textView.setText(enable ? "松开刷新" : "下拉刷新");
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setRotation(enable ? 180 : 0);
                    }
                });

        refreshLayout
                .setOnPushLoadMoreListener(new SuperSwipeRefreshLayout3.OnPushLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        footerTextView.setText("正在加载...");
                        footerImageView.setVisibility(View.GONE);
                        footerProgressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                footerImageView.setVisibility(View.VISIBLE);
                                footerProgressBar.setVisibility(View.GONE);
                                refreshLayout.setLoadMore(false);
                                if (mOnRefreshListener2 != null) {
                                    mOnRefreshListener2.onPullUpToRefresh(refreshLayout);
                                }
                                /*mDatas.addAll(mMChapterDetailList);
                                mChapterListAdapter.notifyDataSetChanged();*/
                            }
                        }, 5000);
                    }

                    @Override
                    public void onPushEnable(boolean enable) {
                        footerTextView.setText(enable ? "松开加载" : "上拉加载");
                        footerImageView.setVisibility(View.VISIBLE);
                        footerImageView.setRotation(enable ? 0 : 180);
                    }

                    @Override
                    public void onPushDistance(int distance) {
                        // TODO Auto-generated method stub
                    }

                });
    }


    private View createFooterView() {
        View footerView = LayoutInflater.from(refreshLayout.getContext())
                .inflate(R.layout.layout_footer, null);
        footerProgressBar = (ProgressBar) footerView
                .findViewById(R.id.footer_pb_view);
        footerImageView = (ImageView) footerView
                .findViewById(R.id.footer_image_view);
        footerTextView = (TextView) footerView
                .findViewById(R.id.footer_text_view);
        footerProgressBar.setVisibility(View.GONE);
        footerImageView.setVisibility(View.VISIBLE);
        //footerImageView.setImageResource(R.drawable.down_arrow);
        footerTextView.setText("上拉加载更多...");
        return footerView;
    }

    private View createHeaderView() {
        View headerView = LayoutInflater.from(refreshLayout.getContext())
                .inflate(R.layout.layout_head, null);
        progressBar = (ProgressBar) headerView.findViewById(R.id.pb_view);
        textView = (TextView) headerView.findViewById(R.id.text_view);
        textView.setText("下拉刷新");
        imageView = (ImageView) headerView.findViewById(R.id.image_view);
        imageView.setVisibility(View.VISIBLE);
        // imageView.setImageResource(R.drawable.down_arrow);
        progressBar.setVisibility(View.GONE);
        return headerView;
    }

    OnRefreshListener2 mOnRefreshListener2;
    public interface OnRefreshListener2 {
        void onPullDownToRefresh(SuperSwipeRefreshLayout3 var1);

        void onPullUpToRefresh(SuperSwipeRefreshLayout3 var1);
    }

    public interface OnRefreshListener {
        void onRefresh(SuperSwipeRefreshLayout3 var1);
    }

    public void setRefreshListener2(OnRefreshListener2 onRefreshListener2 ){
        mOnRefreshListener2=onRefreshListener2;
    }
}
