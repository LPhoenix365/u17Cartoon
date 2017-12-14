package com.pingan.u17.widget;

/**
 * SuperSwipeRefreshLayout 监听
 * @author  liupeng502
 * @data    2017/11/27
 */
public interface SuperRefreshListener {

     interface OnRefreshListener2 {
        void onPullDownToRefresh(SuperSwipeRefreshLayout refreshLayout);

        void onPullUpToRefresh(SuperSwipeRefreshLayout refreshLayout);
    }
     interface OnRefreshListener {
        void onRefresh(SuperSwipeRefreshLayout refreshLayout);
    }

    interface OnRefreshListener3 {
        void onPullDownToRefresh(SuperSwipeRefreshLayout refreshLayout);

        void onPullUpToRefresh(SuperSwipeRefreshLayout refreshLayout);

        void onPullDistance(int distance);

    }

}