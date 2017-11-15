package com.pingan.u17.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pingan.u17.R;
import com.pingan.u17.base.BaseFragment;
import com.pingan.u17.model.response.CartoonDetailResponse;
import com.pingan.u17.model.response.CommentListRespone;
import com.pingan.u17.presenter.CommentListPresenter;
import com.pingan.u17.view.CommentListView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/11/9
 */

public class CommentListFragment extends BaseFragment<CommentListView,CommentListPresenter> implements CommentListView {


    @BindView(R.id.rv_comment_list)
    RecyclerView commentList;
    private CartoonDetailResponse.ComicBean model;

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
    }

    private void initData() {
        Bundle bundle = getArguments();
        model = (CartoonDetailResponse.ComicBean) bundle.getSerializable("key");
        String comic_id = model.comic_id;
        String thread_id = model.thread_id;
        commentList.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false));
        Map<String, String> map=new HashMap<>();
        map.put("thread_id",thread_id);
        map.put("object_id",comic_id);
        mPresenter.getCommentList(map);
    }


    @Override
    public void showErrorMsg(String errormsg) {

    }

    @Override
    public void getCommentList(CommentListRespone response) {

    }
}
