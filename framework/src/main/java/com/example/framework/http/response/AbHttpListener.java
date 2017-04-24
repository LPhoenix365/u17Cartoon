package com.example.framework.http.response;

import java.util.List;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/14
 */

public abstract class AbHttpListener  {
    /**
     * 构造.
     */
    public AbHttpListener() {
        super();
    }

    /**
     * 请求成功.
     *
     * @param content the content
     */
    public void onSuccess(String content){};

    /**
     * 请求成功.
     *
     * @param list the list
     */
    public void onSuccess(List<?> list){};

    /**
     * 请求失败.
     * @param content the content
     */
    public abstract void onFailure(String content);


    /**
     * 描述：获取数据开始.
     */
    public void onStart(){};


    /**
     * 完成后调用，失败，成功，调用.
     */
    public void onFinish(){};
}
