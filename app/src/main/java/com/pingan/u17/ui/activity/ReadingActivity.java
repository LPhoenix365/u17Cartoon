package com.pingan.u17.ui.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pingan.u17.R;
import com.pingan.u17.adapter.ReaddingAdapter;
import com.pingan.u17.base.BaseActivity;
import com.pingan.u17.widget.SuperRefreshListener;
import com.pingan.u17.widget.SuperSwipeRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/9
 */

public class ReadingActivity extends BaseActivity implements SuperRefreshListener.OnRefreshListener3 {

    @BindView(R.id.iv_cover)
    SimpleDraweeView ivCover;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.tv_visit)
    TextView tvVisit;
    @BindView(R.id.tv_visit_count)
    TextView tvVisitCount;
    @BindView(R.id.monthly_ticket)
    TextView monthlyTicket;
    @BindView(R.id.tv_monthly_ticket)
    TextView tvMonthlyTicket;
    @BindView(R.id.tv_themes)
    TextView tvThemes;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.view_reward)
    TextView viewReward;
    @BindView(R.id.tv_download)
    TextView tvDownload;
    @BindView(R.id.tv_continue_read)
    TextView tvContinueRead;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_name_toolbar)
    TextView tvNameToolbar;
    @BindView(R.id.iv_share_toolbar)
    ImageView ivShareToolbar;
    @BindView(R.id.iv_star_toolbar)
    ImageView ivStarToolbar;
    @BindView(R.id.toolbar_rl)
    RelativeLayout toolbarRl;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.refresh_layout)
    SuperSwipeRefreshLayout refresh_layout;
    @BindView(R.id.ll_read_header)
    LinearLayout ll_read_header;
    private ArrayList<String> mList;
    private int preDis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_reading);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        rvList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mList.add("我是第"+i+"个");
        }
        rvList.setAdapter(new ReaddingAdapter(mList));
        Log.d("tag","ll_read_header.getPaddingTop()"+ll_read_header.getPaddingTop());
        refresh_layout.setSuperRefreshListener3(this);
    }



    @Override
    public void onPullDownToRefresh(SuperSwipeRefreshLayout refreshLayout) {
        SystemClock.sleep(2000);
        refresh_layout.setRefreshing(false);
    }

    @Override
    public void onPullUpToRefresh(SuperSwipeRefreshLayout refreshLayout) {
        SystemClock.sleep(2000);
        refresh_layout.setLoadMore(false);
    }

    @Override
    public void onPullDistance(int distance) {
        if (distance >0) {
            Log.d("tag","distance="+distance);
            //coordinatorLayout.offsetTopAndBottom(distance);
            /*int dy = (int) (distance * 0.4f);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) ll_read_header.getLayoutParams();
            Log.d("tag","ll_read_header.getPaddingTop()"+ll_read_header.getPaddingTop()+"dy="+dy);
            ll_read_header.setPadding(0,ll_read_header.getPaddingTop()+dy,0,0);
            //layoutParams.setMargins(0,layoutParams.topMargin+dy,0,0);
            appBar.getLayoutParams().height= appBar.getLayoutParams().height+dy;
            appBar.requestLayout();*/
            /*appBar.bringToFront();
            appBar.offsetTopAndBottom(distance);*/

           // appBar.setScaleY(distance);
           //ll_read_header.setTranslationY(distance);

            //refresh_layout.offsetTopAndBottom(distance);
        }
    }
}
