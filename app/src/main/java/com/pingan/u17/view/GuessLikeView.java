package com.pingan.u17.view;

import com.pingan.u17.bean.GuessLikeBean;

import java.util.List;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 */

public interface GuessLikeView extends BaseView {
    void getGuessLike(List<GuessLikeBean> response);
}
