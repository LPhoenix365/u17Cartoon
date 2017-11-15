package com.pingan.u17.net;

import com.pingan.u17.bean.GuessLikeBean;
import com.pingan.u17.model.response.BaseResponse;
import com.pingan.u17.model.response.CartoonDetailResponse;
import com.pingan.u17.model.response.CommentListRespone;
import com.pingan.u17.model.response.HomePageResponse;
import com.pingan.u17.model.response.RealtimeResponse;

import java.util.List;
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

    String BASE_URL = "http://app.u17.com/v3/appV3_1/android/phone/comic/";

    @GET("boutiqueListNew?")
    Single<HomePageResponse> getHomePageData(@QueryMap Map<String,String> map);

    @GET("detail_static_new?")
    Single<CartoonDetailResponse> getCartoonDetailData(@QueryMap Map<String, String> map);

    @GET("detail_realtime?")
    Single<RealtimeResponse> getCartoonDetailRealtime(@QueryMap Map<String, String> map);

    @GET("guessLike?")
    Single<BaseResponse<List<GuessLikeBean>>> getCartoonGuessLike(@QueryMap Map<String, String> map);

    @GET("guessLike?")
    Single<CommentListRespone> getCommentList(@QueryMap Map<String, String> map);

}
