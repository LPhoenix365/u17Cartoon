package pingan.com.recyclerdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * @author sanshu
 * @data 2017/2/28 23:31
 * @ToDo ${TODO}
 */

public class Liubianxing extends View {

    private Paint mPaint;
    private Path mPath;
    private BitmapShader shader;
    private String TAG="Liubianxing";
    public Liubianxing(Context context) {
        super(context);
        init(context);
    }

    public Liubianxing(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }



    public Liubianxing(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPath = new Path();
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        Log.d(TAG,"width="+width+"height="+height);

        Log.d(TAG,"width=");
        mPath.reset();
        float l = (float) (width / 2);
        float h = (float) (Math.sqrt(3)*l);
        float top = (height - h) / 2  ;
        mPath.moveTo(l/2,top);
        mPath.lineTo(0,h/2+top);
        mPath.lineTo(l/2,top+h);
        mPath.lineTo((float) (l*1.5),top+h);
        mPath.lineTo(width,h/2+top);
        mPath.lineTo((float) (l*1.5),top);
        mPath.close();


//        float l = (float) (width / 2);
//        float h = (float) (Math.sqrt(30));
//        float top = (height - h) / 2  ;
//        mPath.reset();
//        mPath.moveTo(l/2,top);
//        mPath.lineTo(0,h/2+top);
//        mPath.lineTo(l/2,h+top);
//        mPath.lineTo((float) (l*1.5),h+top);
//        mPath.lineTo(2*l,h/2+top);
//        mPath.lineTo((float) (l*1.5),top);
//        mPath.lineTo(l/2,top);
//        mPath.close();
        Log.d(TAG,"mPath"+l+".."+h+".."+top);
        Log.d(TAG,"mPath" + mPath);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getContext().getResources(), R.mipmap.dialog_seven_years_bg);
        shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP) ;
//        // 然后给Paint设置shader
//        //
        mPaint.setShader(shader) ;
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);
    }
}
