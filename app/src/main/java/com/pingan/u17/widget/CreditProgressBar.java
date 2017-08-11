package com.pingan.u17.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.pingan.u17.R;

import butterknife.BindColor;

/**
 * Description des
 *
 * @author liupeng502
 * @data 2017/8/11
 */

public class CreditProgressBar extends View {

    private Paint mPaint;
    @BindColor(R.color.color_da5f3c)
    int color_da5f3c;
    private  int startX=100;
    private  int startY=100;
    private  int radius=8;
    public CreditProgressBar(Context context) {
        this(context,null);
    }

    public CreditProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CreditProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color_da5f3c);
        mPaint.setStrokeWidth(5);//设置画笔宽度
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        //path.addCircle(startX,startY,radius, Path.Direction.CCW);
        path.lineTo(startX,startY);
        path.lineTo(startX+60,startY);
        /*path.addCircle(startX+60,startY,radius,Path.Direction.CCW);
        path.lineTo(startX+60,startY);
        path.lineTo(startX+120,startY);
        path.addCircle(startX+120,startY,radius,Path.Direction.CCW);
        path.lineTo(startX+120,startY);
        path.lineTo(startX+180,startY);
        path.addCircle(startX+180,startY,radius,Path.Direction.CCW);*/
        path.close();
        canvas.drawPath(path,mPaint);
    }
}
