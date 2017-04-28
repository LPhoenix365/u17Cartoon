package rst.framework.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 相册浏览图片使用的viewpager.防止出现多指触摸抬起时index越界的问题
 */
public class MultiTouchViewPager extends ViewPager {

    public MultiTouchViewPager(Context context) {
        super(context);
    }

    public MultiTouchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private boolean mIsDisallowIntercept = false;

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        mIsDisallowIntercept = disallowIntercept;
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_POINTER_DOWN:
                if (ev.getPointerCount() > 1 && mIsDisallowIntercept) {
                    requestDisallowInterceptTouchEvent(false);
                    boolean handled = super.dispatchTouchEvent(ev);
                    requestDisallowInterceptTouchEvent(true);
                    return handled;
                }
        }
        try {
            return super.dispatchTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}