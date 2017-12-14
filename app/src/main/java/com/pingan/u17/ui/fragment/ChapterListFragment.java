package com.pingan.u17.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pingan.u17.R;
import com.pingan.u17.adapter.ChapterListAdapter;
import com.pingan.u17.base.BaseFragment;
import com.pingan.u17.bean.ChapterBean;
import com.pingan.u17.bean.ChapterDetailBean;
import com.pingan.u17.bean.ChapterRealTimeBean;
import com.pingan.u17.model.CartoonDetailViewModel;
import com.pingan.u17.model.response.CartoonDetailResponse;
import com.pingan.u17.model.response.RealtimeResponse;
import com.pingan.u17.presenter.GuessLikePresenter;
import com.pingan.u17.widget.SuperSwipeRefreshLayout3;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/11/9
 */

public class ChapterListFragment extends BaseFragment {


    @BindView(R.id.rv_chapter_list)
    RecyclerView chapterList;
    @BindView(R.id.refresh_layout)
    SuperSwipeRefreshLayout3 swipeRefreshLayout;
    private CartoonDetailViewModel viewModel;
    private ProgressBar footerProgressBar;
    private TextView textView;
    private ImageView imageView;
    private ProgressBar progressBar;
    private TextView footerTextView;
    private ImageView footerImageView;
    private List<ChapterDetailBean> mDatas;
    private ChapterDetailBean mChapterDetailBean;
    private List<ChapterDetailBean> mMChapterDetailList=new ArrayList<>();
    private ChapterListAdapter mChapterListAdapter;


    public static ChapterListFragment newInstance(CartoonDetailViewModel viewModel) {
        ChapterListFragment fragment = new ChapterListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("key", viewModel);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    protected GuessLikePresenter createPresenter() {
        return new GuessLikePresenter();
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.frag_chapter_list;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        Bundle bundle = getArguments();
        viewModel = (CartoonDetailViewModel) bundle.getSerializable("key");
        CartoonDetailResponse detailResponse = viewModel.cartoonDetailResponse;
        RealtimeResponse realtimeResponse = viewModel.realtimeResponse;
        chapterList.setLayoutManager(new GridLayoutManager(mActivity, 2));
       swipeRefreshLayout.setHeaderView(createHeaderView());// add headerView
        swipeRefreshLayout.setFooterView(createFooterView());
        swipeRefreshLayout.setLoadMore(false);
        swipeRefreshLayout.setRefreshing(false);
        if (detailResponse != null && realtimeResponse != null && detailResponse.chapter_list != null && detailResponse.chapter_list != null) {
            List<ChapterBean> chapter_list = detailResponse.chapter_list;
            List<ChapterRealTimeBean> timeDetailBeanList = realtimeResponse.chapter_list;

            int minSize = Math.min(chapter_list.size(), timeDetailBeanList.size());
            mDatas = new ArrayList<>();

            for (int i = 0; i < minSize; i++) {
                mMChapterDetailList.add(new ChapterDetailBean(chapter_list.get(i), timeDetailBeanList.get(i)));
            }
            mDatas.addAll(mMChapterDetailList);
            mChapterListAdapter = new ChapterListAdapter(mDatas);
            View header = LayoutInflater.from(mActivity).inflate(R.layout.activity_clock, null);
            mChapterListAdapter.addHeaderView(header);
            mChapterListAdapter.openLoadAnimation();
            chapterList.setAdapter(mChapterListAdapter);
        }


        swipeRefreshLayout
                .setOnPullRefreshListener(new SuperSwipeRefreshLayout3.OnPullRefreshListener() {
                    @Override
                    public void onRefresh() {
                        textView.setText("正在刷新");
                        imageView.setVisibility(View.GONE);
                        progressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);
                                progressBar.setVisibility(View.GONE);
                                mDatas.clear();
                                mDatas.addAll(mMChapterDetailList.subList(0,mMChapterDetailList.size()));
                                mChapterListAdapter.setNewData(mDatas);
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

        swipeRefreshLayout
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
                                swipeRefreshLayout.setLoadMore(false);
                                mDatas.addAll(mMChapterDetailList);
                                mChapterListAdapter.notifyDataSetChanged();
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
        View footerView = LayoutInflater.from(swipeRefreshLayout.getContext())
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
        View headerView = LayoutInflater.from(swipeRefreshLayout.getContext())
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


}
