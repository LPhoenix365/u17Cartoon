package com.pingan.u17.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.framework.FrescoImageUtil;
import com.example.framework.http.abutil.AbLogUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.pingan.u17.R;
import com.pingan.u17.base.BaseFragment;
import com.pingan.u17.base.U17Application;
import com.pingan.u17.bean.HomePageBean;
import com.pingan.u17.bean.UpdateBean;
import com.pingan.u17.presenter.ChildRecommendPresenter;
import com.pingan.u17.ui.activity.ScrollingActivity;
import com.pingan.u17.util.ActivityIntentTools;
import com.pingan.u17.util.ToolUtils;
import com.pingan.u17.view.ChildRecommendView;
import com.pingan.u17.widget.RollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Description  首页 推荐
 *
 * @author liupeng502
 * @data 2017/6/9
 */
public class ChildRecommendFragment extends BaseFragment<ChildRecommendView,ChildRecommendPresenter> implements View.OnClickListener,ChildRecommendView {

    @BindView(R.id.ll_container_recommend)
    LinearLayout llContainer;

    @BindColor(R.color.white)
    int model_border_bg;
    @BindColor(R.color.item_rank_bg1)
    int item_rank_bg1;
    @BindColor(R.color.item_rank_bg2)
    int item_rank_bg2;
    @BindColor(R.color.item_rank_bg3)
    int item_rank_bg3;
    @BindColor(R.color.item_rank_bg4)
    int item_rank_bg4;
    @BindColor(R.color.item_rank_bg4)
    int item_rank_bg5;
    private HomePageBean mHomePageBean;

    public static final String TAG = ChildRecommendFragment.class.getSimpleName();

    private List<HomePageBean.ReturnDataBean.GalleryItemsBean>         mGalleryItems;//banner 实体
    private List<HomePageBean.ReturnDataBean.ComicListsBean> mComicLists;

    private int                       mScreenWidth;
    private LinearLayout.LayoutParams mBoderEdgeParam;
    private int[]                     mRank_bgs;
    List<HomePageBean.ReturnDataBean.ComicListsBean.ComicsBean> twoModelEntities;


    @Override
    protected int createViewLayoutId() {
        return R.layout.frag_recommend;
    }

    private void init() {
        mRank_bgs = new int[]{item_rank_bg1, item_rank_bg2, item_rank_bg3, item_rank_bg4, item_rank_bg5};
        mScreenWidth = ToolUtils.getScreenWidth(mActivity);
        mBoderEdgeParam = new LinearLayout.LayoutParams(ToolUtils.dip2px(mActivity, 8), LinearLayout.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        /* AbHttpUtil abHttpUtil = AbHttpUtil.getInstance(mActivity);
        AbRequestParams requestParams = new AbRequestParams();
        requestParams.put("v", "3321");
        requestParams.put("t", "1493003790");
        requestParams.put("model", "Redmi+Pro");
        requestParams.put("come_from", "openqq");
        requestParams.put("android_id", "602b734eecb46c60");

        final AbStringHttpResponseListener stringHttpResponseListener = new AbStringHttpResponseListener(mActivity) {
            @Override
            public void onSuccess(int statusCode, String content) {
                Gson gson = new Gson();
                mHomePageBean = gson.fromJson(content, HomePage2Bean.class);
                updateView();
                List<HomePageBean.ReturnDataBean.ComicListsBean> comicLists = mHomePageBean.getData().getReturnData().getComicLists();
                List<HomePageBean.ReturnDataBean.GalleryItemsBean> list = mHomePageBean.getData().getReturnData().getGalleryItems();
                AbLogUtil.d(TAG, "bean=" + mHomePageBean + "comicLists" + comicLists + comicLists + "list" + list);
            }

            @Override
            public void onFailure(int statusCode, String content, Throwable error) {
                super.onFailure(statusCode, content, error);
                AbLogUtil.d(TAG, "content=" + content + "statusCode=" + statusCode + "error=" + error);
            }
        };
        //abHttpUtil.get(Constants.BASE_URL + Constants.HOME_PAGE, requestParams, stringHttpResponseListener);*/


        String t = "1493003790";
        String model = "Redmi+Pro";
        String android_id = "602b734eecb46c60";


        Map<String,String> map = new HashMap<>();
        map.put("v", "3321");
        map.put("t", "1493003790");
        map.put("model", "Redmi+Pro");
        map.put("come_from", "openqq");
        map.put("android_id", "602b734eecb46c60");

        mPresenter.getHomePageData(map);

                /*.subscribe(new Consumer<HomePageBean.ReturnDataBean.ComicListsBean>() {
                    @Override
                    public void accept(HomePageBean.ReturnDataBean.ComicListsBean comicListsBean) throws Exception {
                        int comicType = comicListsBean.getComicType();
                        switch (comicType) {
                            case 6:  //强力推荐
                                addThreeModel(comicListsBean, 2);
                                break;
                            //今日推荐 今日更新 订阅漫画  vip会员漫画
                            case 7:
                                addThreeModel(comicListsBean, 1);
                                break;
                            case 5:  // 不知道什么鬼
                                addTwoModel(comicListsBean, 80, 1, true);
                                break;
                            case 3: //条慢每日更新
                                addTwoModel(comicListsBean, 100, 2, false);
                                break;
                            case 8:
                                //暂时无数据 热门新品
                                //addFourModel(comicListsBean);
                                break;
                            case 9: //最新动画
                                addTwoModel(comicListsBean, 100, 1, false);
                                break;
                    *//*case 9: //暂时没有吧
                        addTwoModel(comicBean, 100, 2, false);
                        break;*//*
                            case 4: //排行
                                addRankModel(comicListsBean);
                                break;
                            default:
                        }
                    }
                });*/

        //mPresenter.hasNewversion(t,model,android_id);



        api.hasNewversion2(t, model, android_id).enqueue(new Callback<UpdateBean>() {
            @Override
            public void onResponse(Call<UpdateBean> call, Response<UpdateBean> response) {
                UpdateBean updateInfo = response.body();
                AbLogUtil.d("ChildRecommendFragment", "call=" + call + "response=" + updateInfo);

            }

            @Override
            public void onFailure(Call<UpdateBean> call, Throwable t) {
                AbLogUtil.d("ChildRecommendFragment", "call=" + call + "Throwable=" + t);
            }
        });
    }

    @Override
    public void getHomePageData(HomePageBean homePageBean) {
        mHomePageBean=homePageBean;
        updateView();
    }

    @Override
    public void hasNewVersion(UpdateBean updateBean) {
        String content = updateBean.getReturnData().getUpdateInfo().getUpdate_content();
        AbLogUtil.d(TAG,"content="+content);
    }


    /**
     * 根据返回值刷新view界面
     */
    private void updateView() {
        if (mHomePageBean != null) {
            mGalleryItems = mHomePageBean.getReturnData().getGalleryItems();
            addAD();
            mComicLists = mHomePageBean.getReturnData().getComicLists();
            int size = mComicLists.size();

            for (int i = 0; i < mComicLists.size(); i++) {
                HomePageBean.ReturnDataBean.ComicListsBean comicBean = mComicLists.get(i);
                int comicType = comicBean.getComicType();
                switch (comicType) {
                    case 6:  //强力推荐
                        addThreeModel(comicBean, 2);
                        break;
                    //今日推荐 今日更新 订阅漫画  vip会员漫画
                    case 7:
                        addThreeModel(comicBean, 1);
                        break;
                    case 5:  // 不知道什么鬼
                        addTwoModel(comicBean, 80, 1, true);
                        break;
                    case 3: //条慢每日更新
                        addTwoModel(comicBean, 100, 2, false);
                        break;
                    case 8:
                        //暂时无数据 热门新品
                        addFourModel(comicBean);
                        break;
                    case 9: //最新动画
                        addTwoModel(comicBean, 100, 1, false);
                        break;
                    /*case 9: //暂时没有吧
                        addTwoModel(comicBean, 100, 2, false);
                        break;*/
                    case 4: //排行
                        addRankModel(comicBean);
                        break;
                    default:
                }
            }
        }
    }

    @Override
    protected ChildRecommendPresenter createPresenter() {
        return new ChildRecommendPresenter(this);
    }

    /**
     * 二模块
     *
     * @param comicBean 实体类
     * @param height    自己根据界面设置高度
     * @param type      type=1 一行 type=2 两行
     * @param onlyImg   只显示图片的种类
     */
    private void addTwoModel(HomePageBean.ReturnDataBean.ComicListsBean comicBean, int height, int type, boolean onlyImg) {
        twoModelEntities = comicBean.getComics();

        if (twoModelEntities != null && twoModelEntities.size() > 0) {
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
            //头部点击事件
            headerView.setTag(comicBean.getItemTitle());
            headerView.setOnClickListener(headerClickListener);
            LinearLayout linearLayout = null;
            int COLUMN = 2;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int mItemWidth = (mScreenWidth - mBoderEdgeParam.width * (COLUMN + 1)) / COLUMN;
            int size = twoModelEntities.size();
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
                HomePageBean.ReturnDataBean.ComicListsBean.ComicsBean comics = twoModelEntities.get(i);
                viewHolder.bindView(comics, onlyImg);
                View boderLine = new View(mActivity);
                linearLayout.addView(boderLine, mBoderEdgeParam);
                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(mItemWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayout.addView(view, layoutParam);
                //设置点击事件
                String tag = comicBean.getItemTitle() + "_" + i;
                view.setTag(tag);
                view.setOnClickListener(twoModelClickListener);
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
    private void addThreeModel(HomePageBean.ReturnDataBean.ComicListsBean comicsBean, int type) {
        List<HomePageBean.ReturnDataBean.ComicListsBean.ComicsBean> comics = comicsBean.getComics();
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
            //头部点击事件
            headerView.setTag(comicsBean.getItemTitle());
            headerView.setOnClickListener(headerClickListener);
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
                //点击事件
                String tag = comicsBean.getItemTitle() + "_" + i;
                view.setTag(tag);
                view.setOnClickListener(threeModelClickListener);
                if ((i + 1) % COLUMN == 0) {
                    View boderLineRight = new View(mActivity);
                    linearLayout.addView(boderLineRight, mBoderEdgeParam);
                }
            }
            llContainer.addView(modelLinearLayout, modelLayoutParams);
        }
    }

    /**
     * @param comicsBean 四模块
     */
    private void addFourModel(HomePageBean.ReturnDataBean.ComicListsBean comicsBean) {
        List<HomePageBean.ReturnDataBean.ComicListsBean.ComicsBean> comics = comicsBean.getComics();
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
            //点击事件
            headerView.setTag(comicsBean.getItemTitle());
            headerView.setOnClickListener(headerClickListener);

            LinearLayout.LayoutParams fourLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ToolUtils.dip2px(mActivity, 100));
            View fourLayout = mInflater.inflate(R.layout.item_view_four, null, false);
            fourLayout.findViewById(R.id.item_header).setOnClickListener(this);
            SimpleDraweeView draweeView = (SimpleDraweeView) fourLayout.findViewById(R.id.sv_cover_four);
            FrescoImageUtil.displayImgFromNetwork(draweeView, comicsBean.getComics().get(0).getCover());
            ((TextView) fourLayout.findViewById(R.id.tv_name_four)).setText(comicsBean.getComics().get(0).getName());
            ((TextView) fourLayout.findViewById(R.id.tv_autho_name)).setText(comicsBean.getComics().get(0).getAuthor_name());
            ((TextView) fourLayout.findViewById(R.id.tv_detail)).setText(comicsBean.getComics().get(0).getDescription());
            modelLinearLayout.addView(fourLayout, fourLayoutParams);

            LinearLayout linearLayout = null;
            int COLUMN = 3;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int mItemWidth = (mScreenWidth - mBoderEdgeParam.width * (COLUMN + 1)) / COLUMN;
            int size = comics.size();
            if (size >= 4) {
                size = 4;
            }
            for (int i = 1; i < size; i++) {
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
                layoutParam.bottomMargin = ToolUtils.dip2px(mActivity, 10);
                linearLayout.addView(view, layoutParam);
                //点击事件
                String tag = comicsBean.getItemTitle() + "_" + i;
                view.setTag(tag);
                view.setOnClickListener(fourModelClickListener);
                if ((i + 1) % COLUMN == 0) {
                    View boderLineRight = new View(mActivity);
                    linearLayout.addView(boderLineRight, mBoderEdgeParam);
                }
            }
            llContainer.addView(modelLinearLayout, modelLayoutParams);
        }
    }

    /**
     * 排名模块的
     *
     * @param comicsBean
     */
    private void addRankModel(HomePageBean.ReturnDataBean.ComicListsBean comicsBean) {
        List<HomePageBean.ReturnDataBean.ComicListsBean.ComicsBean> comics = comicsBean.getComics();
        if (comics != null && comics.size() > 0) {
            LinearLayout.LayoutParams modelLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            modelLayoutParams.bottomMargin = ToolUtils.dip2px(mActivity, 10);
            LinearLayout modelLinearLayout = new LinearLayout(mActivity);
            modelLinearLayout.setBackgroundColor(model_border_bg);
            modelLinearLayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams headerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ToolUtils.dip2px(mActivity, 40));
            View headerView = mInflater.inflate(R.layout.item_recomend_header, null, false);
            headerView.findViewById(R.id.item_header).setOnClickListener(this);
            SimpleDraweeView icon = (SimpleDraweeView) headerView.findViewById(R.id.item_header_icon);
            FrescoImageUtil.displayImgFromNetwork(icon, comicsBean.getNewTitleIconUrl());
            headerView.findViewById(R.id.item_header_more).setVisibility(View.INVISIBLE);
            ((TextView) headerView.findViewById(R.id.item_header_title)).setText(comicsBean.getItemTitle());
            modelLinearLayout.addView(headerView, headerParams);
            //点击事件
            headerView.setTag(comicsBean.getItemTitle());
            headerView.setOnClickListener(headerClickListener);
            LinearLayout linearLayout = null;
            int COLUMN = 1;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int mItemWidth = (mScreenWidth - mBoderEdgeParam.width * (COLUMN + 1)) / COLUMN;
            int size = comics.size();
            if (size >= 5) {
                size = 5;
            }
            for (int i = 0; i < size; i++) {
                if (i % COLUMN == 0) {
                    linearLayout = new LinearLayout(mActivity);
                    modelLinearLayout.addView(linearLayout, layoutParams);
                }
                View view = mInflater.inflate(R.layout.item_view_rank, null, false);
                RankModelViewHolder viewHolder = new RankModelViewHolder(view);
                viewHolder.bindView(comics.get(i), i);
                View boderLine = new View(mActivity);
                linearLayout.addView(boderLine, mBoderEdgeParam);
                LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(mItemWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParam.bottomMargin = ToolUtils.dip2px(mActivity, 10);
                linearLayout.addView(view, layoutParam);
                //点击事件
                String tag = comicsBean.getItemTitle() + "_" + i;
                view.setTag(tag);
                view.setOnClickListener(rankModelClickListener);
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
        LinearLayout.LayoutParams modelLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout modelLinearLayout = new LinearLayout(mActivity);
        modelLinearLayout.setBackgroundColor(model_border_bg);
        LinearLayout.LayoutParams adLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ToolUtils.dip2px(mActivity, 140));
        RollView rollViewPager = new RollView(mActivity);
        List<String> arrayList = new ArrayList<>();
        for (HomePageBean.ReturnDataBean.GalleryItemsBean galleryItem : mGalleryItems) {
            arrayList.add(galleryItem.getCover());
        }
        rollViewPager.setData(arrayList);
        modelLinearLayout.addView(rollViewPager, adLayoutParams);
        llContainer.addView(modelLinearLayout, modelLayoutParams);
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


    private String[] splitTag(String tag) {
        String regex = "_";
        String[] array = new String[2];
        String[] strings = tag.split(regex);
        if (strings != null) {
            array[0] = strings[0];
            array[1] = strings[1];
        }
        return array;
    }

    private View.OnClickListener twoModelClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String[] params = splitTag((String) v.getTag());
            dispatchEventClick(params);
        }
    };


    private View.OnClickListener threeModelClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String[] params = splitTag((String) v.getTag());
            dispatchEventClick(params);
        }
    };
    private View.OnClickListener fourModelClickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String[] params = splitTag((String) v.getTag());
            dispatchEventClick(params);
        }
    };
    private View.OnClickListener rankModelClickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String[] params = splitTag((String) v.getTag());
            dispatchEventClick(params);
        }
    };

    private View.OnClickListener adModelClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String[] params = splitTag((String) v.getTag());
            dispatchEventClick(params);
        }
    };

    private View.OnClickListener headerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private void dispatchEventClick(String[] params) {
        String itemTitle = params[0];
        int position = Integer.parseInt(params[1]);
        ActivityIntentTools.gotoActivityNotFinish(mActivity, ScrollingActivity.class);
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

        public void bindView(HomePageBean.ReturnDataBean.ComicListsBean.ComicsBean comics) {
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

        public void bindView(HomePageBean.ReturnDataBean.ComicListsBean.ComicsBean comics, boolean onlyImg) {
            FrescoImageUtil.displayImgFromNetwork(svCoverSeven, comics.getCover());
            if (onlyImg) {
                tvNameSeven.setVisibility(View.GONE);
                tvCornerSeven.setVisibility(View.GONE);
            } else {
                tvNameSeven.setText(comics.getName());
                tvCornerSeven.setText(U17Application.getInstance().getResources().getString(R.string.text_update_setion, comics.getCornerInfo()));
            }

        }
    }

    class RankModelViewHolder {
        @BindView(R.id.sv_cover_rank)
        SimpleDraweeView svCoverRank;
        @BindView(R.id.tv_name_rank)
        TextView         tvNameRank;
        @BindView(R.id.tv_theme_rank)
        TextView         tvThemeRank;
        @BindView(R.id.tv_anthor_rank)
        TextView         tvAnthorRank;
        @BindView(R.id.ll_right)
        LinearLayout     llRight;

        public RankModelViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void bindView(HomePageBean.ReturnDataBean.ComicListsBean.ComicsBean comics, int position) {
            FrescoImageUtil.displayImgFromNetwork(svCoverRank, comics.getCover());
            tvNameRank.setText(comics.getName());
            tvAnthorRank.setText(comics.getAuthor_name());
            llRight.setBackgroundColor(mRank_bgs[position]);
            String[] comicsTags = comics.getTags();
            StringBuilder sb = new StringBuilder();
            if (comicsTags != null && comicsTags.length > 0) {
                for (int i = 0; i < comicsTags.length; i++) {
                    sb.append(comicsTags[i] + " ");
                }
            }
            tvThemeRank.setText(sb.toString());
        }
    }
}
