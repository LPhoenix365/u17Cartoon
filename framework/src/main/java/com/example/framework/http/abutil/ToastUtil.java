package com.example.framework.http.abutil;

import android.content.Context;
import android.widget.Toast;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/14
 */

public class ToastUtil {



    public static void showToast(String msg) {
    }

    public static void showToast(Context context, String s) {
        Toast.makeText(context,s,Toast.LENGTH_LONG);
    }
}
