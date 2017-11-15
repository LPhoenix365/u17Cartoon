package com.pingan.u17.net;

import com.pingan.u17.model.response.CommentListRespone;

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

public interface CommentServiceImp {

    String BASE_URL = "http://app.u17.com/v3/appV3_1/android/phone/";


    @GET("comment/list?")
    Single<CommentListRespone> getCommentList(@QueryMap Map<String, String> map);

}
