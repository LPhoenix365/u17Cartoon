package com.pingan.u17.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.framework.http.abutil.AbHttpUtil;
import com.example.framework.http.abutil.AbLogUtil;
import com.example.framework.http.request.AbHttpClient;
import com.example.framework.http.request.AbRequestParams;
import com.example.framework.http.response.AbStringHttpResponseListener;
import com.google.gson.Gson;
import com.pingan.u17.R;
import com.pingan.u17.adapter.RecommendAdapter;
import com.pingan.u17.base.BaseFragment;
import com.pingan.u17.bean.HomePageBean;
import com.pingan.u17.util.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：liupeng on 2017/2/24 09:56
 * Address：liupeng264@pingan.com.cn
 */
public class ChildRecommendFragment extends BaseFragment {
    @BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;
    private AbHttpClient mHttpClient;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_recommend, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvRecommend.setLayoutManager(new LinearLayoutManager(mActivity));
        rvRecommend.setAdapter(new RecommendAdapter());

        AbHttpUtil abHttpUtil = AbHttpUtil.getInstance(mActivity);
        mHttpClient = new AbHttpClient(mActivity);
        final AbRequestParams requestParams = new AbRequestParams();
        requestParams.put("v","3220102");
        requestParams.put("t","1493003790");
        requestParams.put("model","Redmi+Pro");
        requestParams.put("come_from","openqq");
        requestParams.put("android_id","602b734eecb46c60");

        final AbStringHttpResponseListener stringHttpResponseListener = new AbStringHttpResponseListener(mActivity) {
            @Override
            public void onSuccess(int statusCode, String content) {
                /*try {
                    JSONObject jsonObject = new JSONObject(content);
                    String code = jsonObject.optString("code");
                    if (1 == Integer.parseInt(code)) {
                        JSONObject data = new JSONObject(jsonObject.optString("data"));
                        String stateCode = data.optString("stateCode");
                        if (1 == Integer.parseInt(stateCode)) {
                            Gson gson = new Gson();
                            String string = data.toString();
                            HomePageBean homePageBean = gson.fromJson(string, HomePageBean.class);
                            List<HomePageBean.ComicListsBean> comicLists = homePageBean.getComicLists();
                            List<HomePageBean.GalleryItemsBean> galleryItems = homePageBean.getGalleryItems();
                            AbLogUtil.d("ChildRecommendFragment","homePageBean="+homePageBean+"comicLists="+comicLists+"galleryItems"+galleryItems);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

                Gson gson = new Gson();
                HomePageBean bean = gson.fromJson(content, HomePageBean.class);
                List<HomePageBean.DataBean.ReturnDataBean.ComicListsBean> comicLists = bean.getData().getReturnData().getComicLists();
                List<HomePageBean.DataBean.ReturnDataBean.GalleryItemsBean> list = bean.getData().getReturnData().getGalleryItems();
                AbLogUtil.d("ChildRecommendFragment","bean="+bean+"comicLists"+comicLists+comicLists+"list"+list);
            }

            @Override
            public void onFailure(int statusCode, String content, Throwable error) {
                super.onFailure(statusCode, content, error);
                AbLogUtil.d("ChildRecommendFragment","content="+content+"statusCode="+statusCode+"error="+error);
            }
        };
        abHttpUtil.get(Constants.BASE_URL + Constants.HOME_PAGE,requestParams,stringHttpResponseListener);
        /*Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mHttpClient.get(Constants.BASE_URL + Constants.HOME_PAGE, requestParams, stringHttpResponseListener);
            }
        };
        new Thread(runnable).start();*/
    }
}
