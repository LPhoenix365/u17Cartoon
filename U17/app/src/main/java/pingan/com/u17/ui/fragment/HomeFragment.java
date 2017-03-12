package pingan.com.u17.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import pingan.com.u17.R;
import pingan.com.u17.adapter.HomeRecyclerAdapter;
import pingan.com.u17.base.BaseFragment;

/**
 * Author：liupeng on 2017/2/24 09:54
 * Address：liupeng264@pingan.com.cn
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.home_childconcont_ll)
    LinearLayout homeChildconcontLl;
    @BindView(R.id.home_recyclerview)
    RecyclerView homeRecyclerview;
    @BindView(R.id.home_concont_ll)
    LinearLayout homeConcontLl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        homeRecyclerview.setLayoutManager(new LinearLayoutManager(mActivity));
        homeRecyclerview.setAdapter(new HomeRecyclerAdapter());
    }


}
