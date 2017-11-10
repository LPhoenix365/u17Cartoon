package com.pingan.u17.ui.fragment;

import android.os.Bundle;

import com.pingan.u17.R;
import com.pingan.u17.base.BaseFragment;
import com.pingan.u17.model.CartoonDetailViewModel;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/11/9
 */

public class CartoonDetailFragment extends BaseFragment {

    public static  CartoonDetailFragment netInstance(CartoonDetailViewModel viewModel){
        CartoonDetailFragment fragment = new CartoonDetailFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("key","");
        return fragment;
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.frag_cartoon_detail;
    }
}
