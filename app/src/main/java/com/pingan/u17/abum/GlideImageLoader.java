package com.pingan.u17.abum;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pingan.u17.R;

import java.io.File;
/**
 * Description
 * @author  liupeng502
 * @data    2017/6/30
 */
public class GlideImageLoader  {
    public static  void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)                             //配置上下文
                .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(R.drawable.default_image)           //设置错误图片
                .placeholder(R.drawable.default_image)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }

    public void clearMemoryCache() {
    }
}
