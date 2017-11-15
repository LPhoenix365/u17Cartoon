package com.pingan.u17.presenter;

import com.pingan.u17.model.response.CommentListRespone;
import com.pingan.u17.model.response.HttpSingleSubscriber;
import com.pingan.u17.util.RxUtils;
import com.pingan.u17.view.CommentListView;

import java.util.Map;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/11/6
 */

public class CommentListPresenter extends BasePresenter<CommentListView> {

    private CommentListView commentListView;

    public void getCommentList(Map<String, String> map) {
        commentListView = getView();
        commentServiceImp.getCommentList(map)
                .compose(RxUtils.<CommentListRespone>defaultSchedulers_single())
                .subscribe(new HttpSingleSubscriber<CommentListRespone>() {
                    @Override
                    public void success(CommentListRespone commentListRespone) {
                        if (commentListView != null) {
                            commentListView.getCommentList(commentListRespone);
                        }
                    }

                    @Override
                    public void error(Throwable e) {
                        if (commentListView != null) {
                            commentListView.showErrorMsg(e.getMessage());
                        }
                    }
                });
    }
}
