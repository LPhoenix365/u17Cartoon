package com.pingan.u17.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.framework.FrescoImageUtil;
import com.example.framework.http.abutil.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.pingan.u17.R;
import com.pingan.u17.adapter.PAFragmentPagerAdapter;
import com.pingan.u17.base.BaseActivity;
import com.pingan.u17.model.CartoonDetailViewModel;
import com.pingan.u17.model.response.CartoonDetailResponse;
import com.pingan.u17.model.response.RealtimeResponse;
import com.pingan.u17.presenter.CartoonDetailPresenter;
import com.pingan.u17.ui.fragment.CartoonDetailFragment;
import com.pingan.u17.ui.fragment.ChapterListFragment;
import com.pingan.u17.view.CartoonDetailView;

import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pingan.u17.R.id.tv_author;
import static com.pingan.u17.R.id.tv_visit_count;


public class CartoonDetailActivity extends BaseActivity<CartoonDetailView, CartoonDetailPresenter> implements CartoonDetailView {

    @BindView(R.id.iv_cover)
    SimpleDraweeView ivCover;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(tv_author)
    TextView tvAuthor;
    @BindView(R.id.tv_visit)
    TextView tvVisit;
    @BindView(tv_visit_count)
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
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_name_toolbar)
    TextView tvNameToolbar;
    @BindView(R.id.iv_share_toolbar)
    ImageView ivShareToolbar;
    @BindView(R.id.iv_star_toolbar)
    ImageView ivStarToolbar;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.toolbar_rl)
    RelativeLayout rlLayout;
    private CartoonDetailViewModel cartoonDetailModel;
    private String[] mTitles=new String[]{
            "详情", "目录", "评论"
    };
    private int mCurrentPage=0;
    private boolean getCartoonDetailSucess;
    private boolean getDetailRealtimeSucess;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_cartoon_detail);
        ButterKnife.bind(this);
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int height = appBar.getHeight();
                //设置
                float alpha = Math.abs(verticalOffset) * 1.f / height;
                tvNameToolbar.setAlpha(alpha);
                rlLayout.setAlpha(alpha);
            }
        });
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("comicid", String.valueOf(13707));
        mPresenter.getCartoonDetailData(map);
        mPresenter.getCartoonDetailRealtime(map);
        cartoonDetailModel = new CartoonDetailViewModel();
    }

    @Override
    public CartoonDetailPresenter createPresenter() {
        return new CartoonDetailPresenter();
    }


    @Override
    public void showErrorMsg(String errormsg) {
        ToastUtil.showToast(this, errormsg);
    }

    @Override
    public void getCartoonDetail(CartoonDetailResponse response) {
        if (response != null) {
            cartoonDetailModel.cartoonDetailResponse=response;
            getCartoonDetailSucess=true;
            initVp();
            CartoonDetailResponse.ComicBean comicBean = response.comic;
            tvAuthor.setText(comicBean.author.name);
            List<String> stringList = comicBean.theme_ids;
            StringBuffer sb = new StringBuffer();
            if (stringList != null && stringList.size() > 0) {
                for (String s : stringList) {
                    sb.append(s + " ");
                }
                tvThemes.setText(sb.toString());
            }
            tvDetail.setText(comicBean.description);
            FrescoImageUtil.displayImgFromNetwork(ivCover, comicBean.cover);

        }
    }

    @Override
    public void getCartoonDetailRealtime(RealtimeResponse response) {
        if (response != null) {
            cartoonDetailModel.realtimeResponse =response;
            getDetailRealtimeSucess=true;
            initVp();
            RealtimeResponse.ComicBean comic = response.comic;
            if (comic != null) {
                tvVisitCount.setText(String.valueOf(comic.total_click));
                tvMonthlyTicket.setText(String.valueOf(comic.monthly_ticket));
            }
        }
    }


    private void initVp() {
        if (getDetailRealtimeSucess && getCartoonDetailSucess) {
            tablayout.addTab(tablayout.newTab().setText(mTitles[0]).setTag(mTitles[0]));
            tablayout.addTab(tablayout.newTab().setText(mTitles[1]).setTag(mTitles[1]));
            tablayout.addTab(tablayout.newTab().setText(mTitles[2]).setTag(mTitles[2]));
            PAFragmentPagerAdapter mAdapter = new PAFragmentPagerAdapter(getSupportFragmentManager(), mTitles);
            mAdapter.addFragment(CartoonDetailFragment.newInstance(cartoonDetailModel));
            mAdapter.addFragment(ChapterListFragment.newInstance(cartoonDetailModel));
            mAdapter.addFragment(CartoonDetailFragment.newInstance(cartoonDetailModel));
            viewpage.setAdapter(mAdapter);
            viewpage.setCurrentItem(mCurrentPage);
            //viewpage.setOffscreenPageLimit(mTitles.length - 1);//不再让Fragment自动销毁并重建
            //给viewpager设置 adapter
            tablayout.setupWithViewPager(viewpage);
        }
    }
    @OnClick({R.id.iv_back, R.id.iv_share_toolbar, R.id.iv_star_toolbar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.iv_share_toolbar:
                break;
            case R.id.iv_star_toolbar:
                break;
        }
    }


}
