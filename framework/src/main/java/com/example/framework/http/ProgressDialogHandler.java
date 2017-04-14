package com.example.framework.http;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.framework.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;


public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;
    public static final int SHOW_LOADING_DIALOG = 3;
    public static final int DISMISS_LOADING_DIALOG = 4;

    private Dialog pd;//状态小人
    private Dialog loadingDialog; //圈圈

    private Context context;
    private boolean cancelable = true;

    public ProgressDialogHandler(Context context,
                                 boolean cancelable) {
        super();
        this.context = context;
        this.cancelable = cancelable;
    }

    /**
     *  loading(状态小人)
     */
    private void initProgressDialog(){
        if (pd == null) {
//            pd = new ProgressDialog(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.loading_dialog,null);
            RelativeLayout rlLoading = (RelativeLayout) v.findViewById(R.id.rl_new_loading);
//            ImageView loading = (ImageView) v.findViewById(R.id.iv_new_loading);
////            Animation animation = AnimationUtils.loadAnimation(context,R.anim.loading);
////            loading.startAnimation(animation);
//            // 2.6.1 使用新loading动画
//            loading.setBackgroundResource(R.drawable.anim_loading);
//            AnimationDrawable animation = (AnimationDrawable) loading.getBackground();
//            animation.start();

            SimpleDraweeView img = (SimpleDraweeView) v.findViewById(R.id.iv_new_loading);

            DraweeController draweeController =
                    Fresco.newDraweeControllerBuilder()
                            .setUri(Uri.parse("res://" + context.getPackageName() + "/" + R.drawable.anim_loading))
                            .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
                            .build();

            img.setController(draweeController);

            pd = new Dialog(context,R.style.MyDialogSty);
            pd.setCancelable(cancelable);
            pd.setContentView(rlLoading, new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.FILL_PARENT,
                    RelativeLayout.LayoutParams.FILL_PARENT));
            pd.setCancelable(cancelable);



            if (pd != null && !pd.isShowing()&& null!=context && !((Activity)context).isFinishing()) {
                pd.show();
            }
        }else if( pd != null && !pd.isShowing()&& null!=context && !((Activity)context).isFinishing()){
            pd.show();
        }
    }

    private void dismissLoadingDialog(){
        if (loadingDialog != null && loadingDialog.isShowing()
                && null!=context ) {
            loadingDialog.dismiss();
//            pd = null;
        }
    }
    /**
     *  loading（圈圈）
     */
    private void initLoadingDialog(){
        if (loadingDialog == null) {
//            pd = new ProgressDialog(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.rst_loading_layout,null);
            RelativeLayout rlLoading = (RelativeLayout) v.findViewById(R.id.rl_new_loading_layout);
//            ImageView loading = (ImageView) v.findViewById(R.id.loading_layout_zz);
//            Animation animation = AnimationUtils.loadAnimation(context,R.anim.loading);
//            loading.startAnimation(animation);

            SimpleDraweeView img = (SimpleDraweeView) v.findViewById(R.id.iv_new_loading);

            DraweeController draweeController =
                    Fresco.newDraweeControllerBuilder()
                            .setUri(Uri.parse("res://" + context.getPackageName() + "/" + R.drawable.anim_loading))
                            .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
                            .build();

            img.setController(draweeController);

            loadingDialog = new Dialog(context,R.style.MyDialogSty);
            loadingDialog.setCancelable(cancelable);
            loadingDialog.setContentView(rlLoading, new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.FILL_PARENT,
                    RelativeLayout.LayoutParams.FILL_PARENT));
            loadingDialog.setCancelable(cancelable);

            if (loadingDialog != null && !loadingDialog.isShowing()&& null!=context && !((Activity)context).isFinishing()) {
                loadingDialog.show();
            }
        }else if( loadingDialog != null && !loadingDialog.isShowing()&& null!=context && !((Activity)context).isFinishing()){
            loadingDialog.show();
        }
    }

    private void dismissProgressDialog(){
        if (pd != null && pd.isShowing()
                && null!=context ) {
            pd.dismiss();
//            pd = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
            case SHOW_LOADING_DIALOG:
                initLoadingDialog();
                break;
            case DISMISS_LOADING_DIALOG:
                dismissLoadingDialog();
                break;
        }
    }

}
