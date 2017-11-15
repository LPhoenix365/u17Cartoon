package com.pingan.u17.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pingan.u17.R;
import com.pingan.u17.adapter.ChapterListAdapter;
import com.pingan.u17.base.BaseFragment;
import com.pingan.u17.bean.ChapterDetailBean;
import com.pingan.u17.bean.ChapterBean;
import com.pingan.u17.bean.ChapterRealTimeBean;
import com.pingan.u17.model.CartoonDetailViewModel;
import com.pingan.u17.model.response.CartoonDetailResponse;
import com.pingan.u17.model.response.RealtimeResponse;
import com.pingan.u17.presenter.GuessLikePresenter;

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
    private CartoonDetailViewModel viewModel;

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

        if (detailResponse != null && realtimeResponse != null && detailResponse.chapter_list != null && detailResponse.chapter_list != null) {
            List<ChapterBean> chapter_list = detailResponse.chapter_list;
            List<ChapterRealTimeBean> timeDetailBeanList = realtimeResponse.chapter_list;

            int minSize = Math.min(chapter_list.size(), timeDetailBeanList.size());
            List<ChapterDetailBean> datas = new ArrayList<>();

            for (int i = 0; i < minSize; i++) {
                ChapterDetailBean chapterDetailBean = new ChapterDetailBean(chapter_list.get(i), timeDetailBeanList.get(i));
                datas.add(chapterDetailBean);
            }
            ChapterListAdapter adapter = new ChapterListAdapter(datas);
            chapterList.setAdapter(adapter);
        }

    }


}
