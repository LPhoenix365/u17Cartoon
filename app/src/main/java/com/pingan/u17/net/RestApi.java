package com.pingan.u17.net;

import com.pingan.u17.bean.UpdateBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

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
    Call<Object> hasNewversion2(@Query("t") String t,
                                   @Query("model") String model,
                                   @Query("android_id") String android_id);
}
