package com.pingan.u17.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.framework.FrescoImageUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：liupeng on 2017/2/8 18:40
 * Address：liupeng264@pingan.com.cn
 * 存在一个bug  开始时不能向左滑动
 */
public class RollViewPager extends ViewPager {
    private int mDownX;
    private int mDownY;
    private List<String> mList = new ArrayList();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mCurrentItem++;
            if (mCurrentItem == mTotalSize) {
                //完后再回去
                mCurrentItem = mList.size() * 50+1;
            }
            RollViewPager.this.setCurrentItem(mCurrentItem);
            mHandler.sendEmptyMessageDelayed(0, 2000);
        }
    };
    private int mCurrentItem; //当前条目
    private int mTotalSize;

    public RollViewPager(Context context) {
        this(context, null);

    }

    public RollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //页面滚动后有变化
        this.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //按下手时清除消息，停止轮播
                mHandler.removeMessages(0);
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                mHandler.sendEmptyMessageDelayed(0, 2000);
                break;
            default:
        }
        // return true;//返回true会使viewpager滑动失效
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = (int) ev.getX();
                mDownY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getX();
                int moveY = (int) ev.getY();
                if (Math.abs(moveX - mDownX) > Math.abs(moveY - mDownY)) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    return super.dispatchTouchEvent(ev);
                }
                break;
            default:
        }
        return super.dispatchTouchEvent(ev);
    }

    //设置数据
    public void setData(List<String> datas) {
        mList = datas;
        mTotalSize = mList.size() * 100;
        //设置为
        mCurrentItem=mList.size()*50;
        RollAdapter rollAdapter = new RollAdapter();
        this.setAdapter(rollAdapter);
        this.setCurrentItem(mCurrentItem);
        //初始化后发消息
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }


    //viewpager的适配器
    class RollAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mTotalSize;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(container.getContext());
            FrescoImageUtil.displayImgFromNetwork(simpleDraweeView, mList.get(position % mList.size()));
            container.addView(simpleDraweeView);
            return simpleDraweeView;
        }
    }

}
