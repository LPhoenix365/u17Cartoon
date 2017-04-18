package com.pingan.u17.abum;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/18
 */

public class FrescoLoader  {

    public void display(Context context, SimpleDraweeView simpleDraweeView) {
        Fresco.initialize(context);
    }
}
