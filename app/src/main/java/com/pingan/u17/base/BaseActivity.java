package com.pingan.u17.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.pingan.u17.R;

public class BaseActivity extends AppCompatActivity {


    FrameLayout baseFramelayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        baseFramelayout = (FrameLayout) findViewById(R.id.base_framelayout);
    }
    /***
     * 设置内容区域
     */
    public void setContentLayout(int resId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(resId, null);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(layoutParams);
        if (null != baseFramelayout) {
            baseFramelayout.addView(contentView);
        }
    }
    /***
     * 设置内容区域
     */
    public void setContentLayout(View view) {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        if (null != baseFramelayout) {
            baseFramelayout.addView(view);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
