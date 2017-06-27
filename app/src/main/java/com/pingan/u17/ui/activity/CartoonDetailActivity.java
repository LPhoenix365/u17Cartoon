package com.pingan.u17.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pingan.u17.R;
import com.pingan.u17.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pingan.u17.R.id.app_bar;

public class CartoonDetailActivity extends BaseActivity {

    @BindView(R.id.iv_cover)
    ImageView               ivCover;
    @BindView(R.id.tv_name)
    TextView                tvName;
    @BindView(R.id.tv_author)
    TextView                tvAuthor;
    @BindView(R.id.tv_visit)
    TextView                tvVisit;
    @BindView(R.id.tv_visit_count)
    TextView                tvVisitCount;
    @BindView(R.id.monthly_ticket)
    TextView                monthlyTicket;
    @BindView(R.id.tv_monthly_ticket)
    TextView                tvMonthlyTicket;
    @BindView(R.id.tv_themes)
    TextView                tvThemes;
    @BindView(R.id.tv_detail)
    TextView                tvDetail;
    @BindView(R.id.view_reward)
    TextView                viewReward;
    @BindView(R.id.tv_download)
    TextView                tvDownload;
    @BindView(R.id.tv_continue_read)
    TextView                tvContinueRead;

    @BindView(R.id.toolbar)
    Toolbar                 toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(app_bar)
    AppBarLayout            appBar;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout       coordinatorLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_cartoon_detail);
        ButterKnife.bind(this);
        /*appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.w("verticalOffset", "verticalOffset=" + verticalOffset);
                toolbar.setAlpha(Math.abs(verticalOffset / (576.f*2)));
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        //collapsingToolbarLayout.setTitle("EXPANDED");//设置title为EXPANDED
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        collapsingToolbarLayout.setTitle("");//设置title不显示
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {

                        }
                       // collapsingToolbarLayout.setTitle("INTERNEDIATE");//设置title为INTERNEDIATE
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });*/

    }


    @OnClick({R.id.tv_download, R.id.tv_continue_read})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_download:
                startActivity(new Intent(CartoonDetailActivity.this,ReadingActivity.class));
                break;
            case R.id.tv_continue_read:
                break;
        }
    }

    private CollapsingToolbarLayoutState state;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }
}
