package com.pingan.u17.net;

import com.pingan.u17.bean.HomePage;
import com.pingan.u17.bean.SortPageBean;
import com.pingan.u17.bean.Update2Bean;
import com.pingan.u17.bean.UpdateBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/27
 */

public interface RestApi {
    @GET("version/newVersion?")
    Call<UpdateBean> hasNewversion(@Query("t") String t,
                                         @Query("model") String model,
                                         @Query("android_id") String android_id);

    @GET("version/newVersion?")
    Call<Update2Bean> hasNewversion2(@Query("t") String t,
                                     @Query("model") String model,
                                     @Query("android_id") String android_id);
    @GET("comic/boutiqueListNew?")
    Call<HomePage> getHomePageData(@Query("model") String model,
                                   @Query("android_id") String android_id);

    @GET("sort/mobileCateList?")
    Call<SortPageBean> getSortPageData(@QueryMap Map<String,String> map);
}
