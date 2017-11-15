package com.pingan.u17.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pingan.u17.R;
import com.pingan.u17.adapter.GuessLikeAdapter;
import com.pingan.u17.base.BaseFragment;
import com.pingan.u17.bean.GuessLikeBean;
import com.pingan.u17.model.CartoonDetailViewModel;
import com.pingan.u17.model.response.CartoonDetailResponse;
import com.pingan.u17.model.response.RealtimeResponse;
import com.pingan.u17.presenter.GuessLikePresenter;
import com.pingan.u17.view.GuessLikeView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/11/9
 */

public class CartoonDetailFragment extends BaseFragment<GuessLikeView, GuessLikePresenter> implements GuessLikeView {

    @BindView(R.id.cartoon_tags)
    TextView cartoonTags;
    @BindView(R.id.tv_mouth_ticket)
    TextView tvMouthTicket;
    @BindView(R.id.tv_mouth_ticket_acount)
    TextView tvMouthTicketAcount;
    @BindView(R.id.tv_other_works)
    TextView tvOtherWorks;
    @BindView(R.id.ll_other_works)
    FrameLayout llOtherWorks;
    @BindView(R.id.recycler_guess_like)
    RecyclerView recyclerGuessLike;

    @BindView(R.id.cartoon_detail)
    TextView cartoonDetail;

    private CartoonDetailViewModel viewModel;

    public static CartoonDetailFragment newInstance(CartoonDetailViewModel viewModel) {
        CartoonDetailFragment fragment = new CartoonDetailFragment();
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
        return R.layout.frag_cartoon_detail;
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
        tvMouthTicket.setText(String.valueOf(realtimeResponse.comic.monthly_ticket));
        tvMouthTicketAcount.setText(String.valueOf(realtimeResponse.comic.total_ticket));

        if (!TextUtils.isEmpty(detailResponse.comic.description)) {
            cartoonDetail.setVisibility(View.VISIBLE);
            cartoonDetail.setText(detailResponse.comic.description);
        }
        tvOtherWorks.setText(detailResponse.otherWorks.size()+"");
        HashMap<String, String> map = new HashMap<>();
        String comic_id = detailResponse.comic.comic_id;
        map.put("comic_id", comic_id);
        mPresenter.getCartoonGuessLike(map);
        recyclerGuessLike.setLayoutManager(new GridLayoutManager(mActivity, 4));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void showErrorMsg(String errormsg) {

    }

    @Override
    public void getGuessLike(List<GuessLikeBean> response) {
        if (response != null) {
            List<GuessLikeBean> guessLikes = response;
            GuessLikeAdapter guessLikeAdapter = new GuessLikeAdapter(R.layout.item_view_guess, guessLikes);
            //guessLikeAdapter.
            recyclerGuessLike.setAdapter(guessLikeAdapter);
        }
    }
}
