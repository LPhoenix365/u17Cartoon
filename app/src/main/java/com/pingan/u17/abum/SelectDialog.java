package com.pingan.u17.abum;


import android.app.Dialog;
import android.content.Context;

/**
 * @author sanshu
 * @data 2017/4/16 22:35
 * @ToDo ${TODO}
 */

public class SelectDialog extends Dialog {
    public SelectDialog(Context context) {
        super(context);
    }

    public SelectDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SelectDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }



}
