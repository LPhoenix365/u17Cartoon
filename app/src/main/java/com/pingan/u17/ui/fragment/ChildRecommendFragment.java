package com.pingan.u17.ui.fragment;

import android.content.Context;
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
import com.pingan.u17.base.U17Application;
import com.pingan.u17.bean.HomePageBean;
import com.pingan.u17.util.Constants;
import com.pingan.u17.util.ToolUtils;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.type;

/**
 * Author：liupeng on 2017/2/24 09:56
 * Address：liupeng264@pingan.com.cn
 */
public class ChildRecommendFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.ll_container_recommend)
    LinearLayout llContainer;

    @BindColor(R.color.model_border_bg)
    int model_border_bg;

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
    private Context                                                     mContext;

    private int                       mScreenWidth;
    private LinearLayout.LayoutParams mBoderEdgeParam;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_recommend, container, false);
        ButterKnife.bind(this, view);
        mContext = container.getContext();
        mInflater = LayoutInflater.from(mActivity);
        init();
        return view;
    }

    private void init() {
        mScreenWidth = ToolUtils.getScreenWidth(mActivity);
        mBoderEdgeParam = new LinearLayout.LayoutParams(ToolUtils.dip2px(mActivity, 8), LinearLayout.LayoutParams.MATCH_PARENT);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        AbHttpUtil abHttpUtil = AbHttpUtil.getInstance(mActivity);
        mHttpClient = new AbHttpClient(mActivity);
        final AbRequestParams requestParams = new AbRequestParams();
        requestParams.put("v", "3321");
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

            mComicLists = mHomePageBean.getData().getReturnData().getComicLists();
            for (int i = 0; i < mComicLists.size(); i++) {
                HomePageBean.DataBean.ReturnDataBean.ComicListsBean comicBean = mComicLists.get(i);
                switch (i) {
                    case 0:
                        addThreeModel(comicBean, 2);
                        break;
                    case 1:
                    case 3:
                    case 4:
                    case 7:
                        addThreeModel(comicBean, 1);
                        break;
                    case 2:
                        addTwoModel(comicBean, 80,1,true);
                        break;
                    case 5:
                        addTwoModel(comicBean, 100,2,false);
                        break;
                    case 6:
                        addFourModel();
                        break;
                    case 8:
                        addTwoModel(comicBean, 100,1,false);
                        break;
                    case 9:

                        break;
                    case 10:

                        break;
                    default:
                }
            }
        }
    }

    private void addFourModel(HomePageBean.DataBean.ReturnDataBean.ComicListsBean comicsBean) {
        List<HomePageBean.DataBean.ReturnDataBean.ComicListsBean.ComicsBean> comics = comicsBean.getComics();
        if (comics != null && comics.size() > 0) {
            LinearLayout.LayoutParams modelLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            modelLayoutParams.bottomMargin = ToolUtils.dip2px(mActivity, 10);
            LinearLayout modelLinearLayout = new LinearLayout(mActivity);
            modelLinearLayout.setBackgroundColor(model_border_bg);
            modelLinearLayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams headerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ToolUtils.dip2px(mActivity, 40));
            View headerView = mInflater.inflate(R.layout.item_recomend_header, null, false);
            headerView.findViewById(R.id.item_header).setOnClickListener(this);
            ((SimpleDraweeView) headerView.findViewById(R.id.item_header_icon)).setImageURI(comicsBean.getNewTitleIconUrl());
            ((TextView) headerView.findViewById(R.id.item_header_title)).setText(comicsBean.getItemTitle());
            modelLinearLayout.addView(headerView, headerParams);
            LinearLayout.LayoutParams headerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ToolUtils.dip2px(mActivity, 40));
            View headerView = mInflater.inflate(R.layout.item_recomend_header, null, false);
            headerView.findViewById(R.id.item_header).setOnClickListener(this);
            ((SimpleDraweeView) headerView.findViewById(R.id.item_header_icon)).setImageURI(comicsBean.getNewTitleIconUrl());
            ((TextView) headerView.findViewById(R.id.item_header_title)).setText(comicsBean.getItemTitle());
            modelLinearLayout.addView(headerView, headerParams);

            LinearLayout linearLayout = null;
            int COLUMN = 3;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int mItemWidth = (mScreenWidth - mBoderEdgeParam.width * (COLUMN + 1)) / COLUMN;
            int size = comics.size();
             if (size >= 3) {
                size = 3;
            }
            for (int i = 0; i < size; i++) {
                if (i % COLUMN == 0) {
                    linearLayout = new LinearLayout(mActivity);
                    modelLinearLayout.addView(linearLayout, layoutParams);
                }
                View view = mInflater.inflate(R.layout.item_view_three, null, false);
                ThreeModelViewHolder viewHolder = new ThreeModelViewHolder(view);
                viewHolder.bindView(comics.get(i));
                View boderLine = new View(mActivity);
                linearLayout.addView(boderLine, mBoderEdgeParam);
                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(mItemWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayout.addView(view, layoutParam);
                if ((i + 1) % COLUMN == 0) {
                    View boderLineRight = new View(mActivity);
                    linearLayout.addView(boderLineRight, mBoderEdgeParam);
                }
            }
            llContainer.addView(modelLinearLayout, modelLayoutParams);
        }
    }

    /**
     * 二模块
     * @param comicBean
     * @param height
     * @param type
     */
    private void addTwoModel(HomePageBean.DataBean.ReturnDataBean.ComicListsBean comicBean, int height, int type,boolean onlyImg) {
        List<HomePageBean.DataBean.ReturnDataBean.ComicListsBean.ComicsBean> comics = comicBean.getComics();
        if (comics != null && comics.size() > 0) {
            LinearLayout.LayoutParams modelLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            modelLayoutParams.bottomMargin = ToolUtils.dip2px(mActivity, 10);
            LinearLayout modelLinearLayout = new LinearLayout(mActivity);
            modelLinearLayout.setBackgroundColor(model_border_bg);
            modelLinearLayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams headerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ToolUtils.dip2px(mActivity, 40));
            View headerView = mInflater.inflate(R.layout.item_recomend_header, null, false);
            headerView.findViewById(R.id.item_header).setOnClickListener(this);
            ((SimpleDraweeView) headerView.findViewById(R.id.item_header_icon)).setImageURI(comicBean.getNewTitleIconUrl());
            ((TextView) headerView.findViewById(R.id.item_header_title)).setText(comicBean.getItemTitle());
            modelLinearLayout.addView(headerView, headerParams);

            LinearLayout linearLayout = null;
            int COLUMN = 2;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int mItemWidth = (mScreenWidth - mBoderEdgeParam.width * (COLUMN + 1)) / COLUMN;
            int size = comics.size();
            if (type == 1) {
                size = 2;
            } else if (size >= 4) {
                size = 4;
            }
            for (int i = 0; i < size; i++) {
                if (i % COLUMN == 0) {
                    linearLayout = new LinearLayout(mActivity);
                    modelLinearLayout.addView(linearLayout, layoutParams);
                }
                View view = mInflater.inflate(R.layout.item_view_three, linearLayout, false);
                SimpleDraweeView icon = (SimpleDraweeView) view.findViewById(R.id.sv_cover_seven);
                ViewGroup.LayoutParams iconLayoutParams = icon.getLayoutParams();
                //设置icon的高度
                iconLayoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                iconLayoutParams.height = ToolUtils.dip2px(mActivity, height);

                TwoModelViewHolder viewHolder = new TwoModelViewHolder(view);
                viewHolder.bindView(comics.get(i),onlyImg);
                View boderLine = new View(mActivity);
                linearLayout.addView(boderLine, mBoderEdgeParam);
                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(mItemWidth, LinearLayout.LayoutParams.WRAP_CONTENT);

                linearLayout.addView(view, layoutParam);
                if ((i + 1) % COLUMN == 0) {
                    View boderLineRight = new View(mActivity);
                    linearLayout.addView(boderLineRight, mBoderEdgeParam);
                }
            }
            llContainer.addView(modelLinearLayout, modelLayoutParams);
        }
    }


    /**
     * 有一行的三模块 还有两行的三模块
     *
     * @param comicsBean
     * @param type       = 1为 1行3列的，2为 2行3列的
     */
    private void addThreeModel(HomePageBean.DataBean.ReturnDataBean.ComicListsBean comicsBean, int type) {
        List<HomePageBean.DataBean.ReturnDataBean.ComicListsBean.ComicsBean> comics = comicsBean.getComics();
        if (comics != null && comics.size() > 0) {
            LinearLayout.LayoutParams modelLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            modelLayoutParams.bottomMargin = ToolUtils.dip2px(mActivity, 10);
            LinearLayout modelLinearLayout = new LinearLayout(mActivity);
            modelLinearLayout.setBackgroundColor(model_border_bg);
            modelLinearLayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams headerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ToolUtils.dip2px(mActivity, 40));
            View headerView = mInflater.inflate(R.layout.item_recomend_header, null, false);
            headerView.findViewById(R.id.item_header).setOnClickListener(this);
            ((SimpleDraweeView) headerView.findViewById(R.id.item_header_icon)).setImageURI(comicsBean.getNewTitleIconUrl());
            ((TextView) headerView.findViewById(R.id.item_header_title)).setText(comicsBean.getItemTitle());
            modelLinearLayout.addView(headerView, headerParams);

            LinearLayout linearLayout = null;
            int COLUMN = 3;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int mItemWidth = (mScreenWidth - mBoderEdgeParam.width * (COLUMN + 1)) / COLUMN;
            int size = comics.size();
            if (type == 1) {
                size = 3;
            } else if (size >= 6) {
                size = 6;
            }
            for (int i = 0; i < size; i++) {
                if (i % COLUMN == 0) {
                    linearLayout = new LinearLayout(mActivity);
                    modelLinearLayout.addView(linearLayout, layoutParams);
                }
                View view = mInflater.inflate(R.layout.item_view_three, null, false);
                ThreeModelViewHolder viewHolder = new ThreeModelViewHolder(view);
                viewHolder.bindView(comics.get(i));
                View boderLine = new View(mActivity);
                linearLayout.addView(boderLine, mBoderEdgeParam);
                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(mItemWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayout.addView(view, layoutParam);
                if ((i + 1) % COLUMN == 0) {
                    View boderLineRight = new View(mActivity);
                    linearLayout.addView(boderLineRight, mBoderEdgeParam);
                }
            }
            llContainer.addView(modelLinearLayout, modelLayoutParams);
        }
    }


    /**
     * 设置banner
     */
    private void addAD() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_header:
                //强力推荐作品
                break;

            default:
        }
    }


    static class ThreeModelViewHolder {
        @BindView(R.id.sv_cover_seven)
        SimpleDraweeView svCoverSeven;
        @BindView(R.id.tv_name_seven)
        TextView         tvNameSeven;
        @BindView(R.id.tv_corner_seven)
        TextView         tvCornerSeven;

        public ThreeModelViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void bindView(HomePageBean.DataBean.ReturnDataBean.ComicListsBean.ComicsBean comics) {
            FrescoImageUtil.displayImgFromNetwork(svCoverSeven, comics.getCover());
            tvNameSeven.setText(comics.getName());
            tvCornerSeven.setText(U17Application.getInstance().getResources().getString(R.string.text_update_setion, comics.getCornerInfo()));
        }
    }

    static class TwoModelViewHolder {
        @BindView(R.id.sv_cover_seven)
        SimpleDraweeView svCoverSeven;
        @BindView(R.id.tv_name_seven)
        TextView         tvNameSeven;
        @BindView(R.id.tv_corner_seven)
        TextView         tvCornerSeven;

        public TwoModelViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void bindView(HomePageBean.DataBean.ReturnDataBean.ComicListsBean.ComicsBean comics,boolean onlyImg) {
            FrescoImageUtil.displayImgFromNetwork(svCoverSeven, comics.getCover());
            if (onlyImg) {
                tvNameSeven.setVisibility(View.GONE);
                tvCornerSeven.setVisibility(View.GONE);
            }else{
                tvNameSeven.setText(comics.getName());
                tvCornerSeven.setText(U17Application.getInstance().getResources().getString(R.string.text_update_setion, comics.getCornerInfo()));
            }

        }
    }
}
