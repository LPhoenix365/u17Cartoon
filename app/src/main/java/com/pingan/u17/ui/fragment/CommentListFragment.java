package com.pingan.u17.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pingan.u17.R;
import com.pingan.u17.adapter.PullToRefreshAdapter;
import com.pingan.u17.adapter.decoration.DividerGridItemDecoration;
import com.pingan.u17.base.BaseFragment;
import com.pingan.u17.bean.CommentBean;
import com.pingan.u17.model.response.CartoonDetailResponse;
import com.pingan.u17.model.response.CommentListRespone;
import com.pingan.u17.presenter.CommentListPresenter;
import com.pingan.u17.view.CommentListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/11/9
 */

public class CommentListFragment extends BaseFragment<CommentListView, CommentListPresenter> implements CommentListView {


    @BindView(R.id.rv_comment_list)
    RecyclerView rvCommentList;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindColor(R.color.border_bg)
    int border_bg;
    @BindDrawable(R.drawable.item_grid_divider)
    Drawable grid_divider;
    private CartoonDetailResponse.ComicBean model;
    private PullToRefreshAdapter mAdapter;
    private int currentPage;
    private Map<String, String> map;
    private List<CommentBean> commentList;
    private int pageCount;
    private static final int PAGE_SIZE = 20;

    public static CommentListFragment newInstance(CartoonDetailResponse.ComicBean viewModel) {
        CommentListFragment fragment = new CommentListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("key", viewModel);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected CommentListPresenter createPresenter() {
        return new CommentListPresenter();
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.frag_comment_list;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initRefreshLayout();
    }

    private void initData() {
        Bundle bundle = getArguments();
        model = (CartoonDetailResponse.ComicBean) bundle.getSerializable("key");
        String comic_id = model.comic_id;
        String thread_id = model.thread_id;
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 2);
        rvCommentList.setLayoutManager(gridLayoutManager);
        rvCommentList.addItemDecoration(new DividerGridItemDecoration(mActivity,grid_divider));
        map = new HashMap<>();
        map.put("thread_id", thread_id);
        map.put("object_id", comic_id);
        currentPage = 1;
        map.put("page", String.valueOf(currentPage));
        mPresenter.getCommentList(map);

    }


    private void initRefreshLayout() {
        commentList = new ArrayList<>();
        mAdapter = new PullToRefreshAdapter(commentList);
        rvCommentList.setAdapter(mAdapter);
        mSwipeRefreshLayout.setEnabled(true);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
    }

    @Override
    public void showErrorMsg(String errormsg) {
        mAdapter.loadMoreComplete();
    }


    private void loadMore() {
        map.put("page", String.valueOf(currentPage++));
        if (currentPage <= pageCount) {
            mPresenter.getCommentList(map);
        }

    }

    private void setData(boolean isRefresh, List<CommentBean> data) {
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {//是下拉刷新
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
        } else {
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void getCommentList(CommentListRespone response) {

        if (response != null) {
            setData(false, response.commentList);
            pageCount = response.pageCount;
        }
    }
}
