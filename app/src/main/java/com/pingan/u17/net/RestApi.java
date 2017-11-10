package com.pingan.u17.net;

import com.pingan.u17.bean.HomePageBean;
import com.pingan.u17.bean.SortPageBean;
import com.pingan.u17.bean.UpdateBean;
import com.pingan.u17.model.response.CartoonDetailResponse;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    Single<UpdateBean> hasNewversion(@QueryMap Map<String,String> map);

    @GET("version/newVersion?")
    Call<UpdateBean> hasNewversion2(@Query("t") String t,
                                    @Query("model") String model,
                                    @Query("android_id") String android_id);
    @GET("comic/boutiqueListNew?")
    Observable<HomePageBean> getHomePageData(@QueryMap Map<String,String> map);


    @GET("sort/mobileCateList?")
    Observable<SortPageBean> getSortPageData(@QueryMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/x3/weather")
    Observable<String> getWeather(@Field("cityId") String cityId, @Field("key") String key);

    @GET("comic/detail_static_new?")
    Single<CartoonDetailResponse> getCartoonDetailData(@QueryMap Map<String,String> map);
}
