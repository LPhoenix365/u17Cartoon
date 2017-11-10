package com.pingan.u17.pull2refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * @author sanshu
 * @data 16/9/12 下午1:07
 * @ToDo ${TODO}
 */

public class PullableLinearlayout extends LinearLayout implements Pullable {
    public PullableLinearlayout(Context context) {
        super(context);
    }

    public PullableLinearlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableLinearlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean canPullDown() {
        return true;
    }

    @Override
    public boolean canPullUp() {
        return true;
    }
}
