package com.pingan.u17.net;

import com.pingan.u17.model.response.CartoonDetailRealtimeResponse;
import com.pingan.u17.model.response.CartoonDetailResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/11/8
 */

public interface CommonServiceImp {

    String BASE_URL = "http://app.u17.com/v3/appV3_1/android/phone/";

    @GET("comic/detail_static_new?")
    Single<CartoonDetailResponse> getCartoonDetailData(@QueryMap Map<String, String> map);

    @GET("comic/detail_realtime?")
    Single<CartoonDetailRealtimeResponse> getCartoonDetailRealtime(@QueryMap Map<String, String> map);

}
