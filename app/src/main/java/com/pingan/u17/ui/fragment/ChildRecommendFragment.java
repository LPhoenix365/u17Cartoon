package com.pingan.u17.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.framework.FrescoImageUtil;
import com.example.framework.http.abutil.AbHttpUtil;
import com.example.framework.http.abutil.AbLogUtil;
import com.example.framework.http.request.AbHttpClient;
import com.example.framework.http.request.AbRequestParams;
import com.example.framework.http.response.AbStringHttpResponseListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.pingan.u17.R;
import com.pingan.u17.base.BaseFragment;
import com.pingan.u17.bean.HomePageBean;
import com.pingan.u17.util.Constants;
import com.pingan.u17.util.ToolUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：liupeng on 2017/2/24 09:56
 * Address：liupeng264@pingan.com.cn
 */
public class ChildRecommendFragment extends BaseFragment {
    /*@BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;*/
    @BindView(R.id.ll_container_recommend)
    LinearLayout llContainer;

    private HomePageBean mHomePageBean;
    private static final int THREE = 3;
    private static final int FOUR  = 4;
    private static final int FIVE  = 5;
    private static final int SIX   = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;
    private static final int NINE  = 9;
    private List<HomePageBean.DataBean.ReturnDataBean.GalleryItemsBean> mGalleryItems;//banner 实体
    private List<HomePageBean.DataBean.ReturnDataBean.ComicListsBean>   mComicLists;
    private AbHttpClient                                                mHttpClient;
    private LayoutInflater                                              mInflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_recommend, container, false);
        ButterKnife.bind(this, view);
        mInflater = LayoutInflater.from(mActivity);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        AbHttpUtil abHttpUtil = AbHttpUtil.getInstance(mActivity);
        mHttpClient = new AbHttpClient(mActivity);
        final AbRequestParams requestParams = new AbRequestParams();
        requestParams.put("v", "3220102");
        requestParams.put("t", "1493003790");
        requestParams.put("model", "Redmi+Pro");
        requestParams.put("come_from", "openqq");
        requestParams.put("android_id", "602b734eecb46c60");

        final AbStringHttpResponseListener stringHttpResponseListener = new AbStringHttpResponseListener(mActivity) {
            @Override
            public void onSuccess(int statusCode, String content) {
                Gson gson = new Gson();
                mHomePageBean = gson.fromJson(content, HomePageBean.class);
                updateView();
                List<HomePageBean.DataBean.ReturnDataBean.ComicListsBean> comicLists = mHomePageBean.getData().getReturnData().getComicLists();
                List<HomePageBean.DataBean.ReturnDataBean.GalleryItemsBean> list = mHomePageBean.getData().getReturnData().getGalleryItems();
                AbLogUtil.d("ChildRecommendFragment", "bean=" + mHomePageBean + "comicLists" + comicLists + comicLists + "list" + list);
            }

            @Override
            public void onFailure(int statusCode, String content, Throwable error) {
                super.onFailure(statusCode, content, error);
                AbLogUtil.d("ChildRecommendFragment", "content=" + content + "statusCode=" + statusCode + "error=" + error);
            }
        };
        abHttpUtil.get(Constants.BASE_URL + Constants.HOME_PAGE, requestParams, stringHttpResponseListener);
    }

    /**
     * 根据返回值刷新view界面
     */
    private void updateView() {
        if (mHomePageBean != null) {
            mGalleryItems = mHomePageBean.getData().getReturnData().getGalleryItems();
            addAD();
            mComicLists = mHomePageBean.getData().getReturnData().getComicLists();
            for (HomePageBean.DataBean.ReturnDataBean.ComicListsBean comicList : mComicLists) {
                switch (comicList.getComicType()) {
                    case THREE:
                        List<HomePageBean.DataBean.ReturnDataBean.ComicListsBean.ComicsBean> comics = comicList.getComics();
                        addSixModel(comics);
                        break;
                    case FOUR:

                        break;
                    case FIVE:

                        break;
                    case SIX:

                        break;
                    case SEVEN:

                        break;
                    case EIGHT:
                        break;
                    case NINE:
                        break;
                    default:
                }
            }
        }
    }

    /**
     * 三模块
     */
    private void addThreeModel(List<HomePageBean.DataBean.ReturnDataBean.ComicListsBean.ComicsBean> comics) {
        if (comics != null) {

        }
    }

    /**
     * @param comics
     */
    private void addSixModel(List<HomePageBean.DataBean.ReturnDataBean.ComicListsBean.ComicsBean> comics) {
        if (comics != null && comics.size() > 0) {
            LinearLayout modelLinearLayout = new LinearLayout(mActivity);
            LinearLayout.LayoutParams modelLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            llContainer.addView(modelLinearLayout, modelLayoutParams);
            LinearLayout linearLayout = null;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            for (int i = 0; i < comics.size(); i++) {
                if (i % 3 == 0) {
                    linearLayout = new LinearLayout(mActivity);
                    modelLinearLayout.addView(linearLayout,layoutParams);
                }
                View view = mInflater.inflate(R.layout.item_recomend_three, null, false);
                SixViewHolder viewHolder = new SixViewHolder(view);
                viewHolder.bindView(comics.get(i));
                int screenDpi = ToolUtils.getScreenDpi(mActivity);
                View boder = mInflater.inflate(R.layout.item_common_boder, null, false);
                linearLayout.addView(boder);
                int itemWidth = (screenDpi - boder.getWidth() * 4) / 3;
                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(ToolUtils.dip2px(mActivity,itemWidth), ToolUtils.dip2px(mActivity,300));
                linearLayout.addView(view, layoutParam);
            }

        }
    }

    /**
     * 设置banner
     */
    private void addAD() {

    }


    static class SixViewHolder {
        @BindView(R.id.sv_cover_seven)
        SimpleDraweeView svCoverSeven;
        @BindView(R.id.tv_name_seven)
        TextView         tvNameSeven;
        @BindView(R.id.tv_corner_seven)
        TextView         tvCornerSeven;

        public SixViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void bindView(HomePageBean.DataBean.ReturnDataBean.ComicListsBean.ComicsBean comics) {
            FrescoImageUtil.displayImgFromNetwork(svCoverSeven, comics.getCover());
            tvNameSeven.setText(comics.getAuthor_name());
            tvCornerSeven.setText(comics.getCornerInfo());
        }
    }
}
