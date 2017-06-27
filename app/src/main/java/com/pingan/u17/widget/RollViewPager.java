package com.pingan.u17.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pingan.u17.R;

/**
 * Author：liupeng on 2017/2/8 18:40
 * Address：liupeng264@pingan.com.cn
 *
 */
public class RollViewPager extends ViewGroup {


    public RollViewPager(Context context) {
        this(context,null);
    }

    public RollViewPager(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RollViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_roll, null);
        RollView rollView= (RollView) view.findViewById(R.id.roll_item);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
