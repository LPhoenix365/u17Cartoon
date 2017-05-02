package com.pingan.u17.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pingan.u17.R;
import com.pingan.u17.abum.PhotoSelectFragment;
import com.pingan.u17.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author：liupeng on 2017/2/24 09:56
 * Address：liupeng264@pingan.com.cn
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.mine_btn)
    Button mineBtn;
    private Unbinder mBind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        mBind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mineBtn.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.mine_btn:
                Intent intent = new Intent(mActivity, PhotoSelectFragment.class);
                startActivity(intent);
                break;
            default:
        }

    }
}
