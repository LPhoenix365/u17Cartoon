package com.example.framework.http;

import android.content.Context;

import static com.example.framework.http.AbThreadFactory.mExecutorService;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/1
 */

public class AbHttpClient  {

    private  Context mContext;

    public AbHttpClient(Context context) {
        mContext = context;
        mExecutorService = AbThreadFactory.getExecutorService();

    }
}
