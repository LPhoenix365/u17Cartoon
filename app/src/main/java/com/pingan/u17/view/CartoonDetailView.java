package com.pingan.u17.view;

import com.pingan.u17.model.response.RealtimeResponse;
import com.pingan.u17.model.response.CartoonDetailResponse;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 */

public interface CartoonDetailView extends BaseView {
    void getCartoonDetail(CartoonDetailResponse response);
    void getCartoonDetailRealtime(RealtimeResponse response);
}
