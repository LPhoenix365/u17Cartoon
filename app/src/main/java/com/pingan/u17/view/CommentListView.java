package com.pingan.u17.view;

import com.pingan.u17.model.response.CommentListRespone;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/6/30
 */

public interface CommentListView extends BaseView {
    void getCommentList(CommentListRespone response);
}
