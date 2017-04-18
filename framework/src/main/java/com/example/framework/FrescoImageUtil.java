package com.example.framework;

import android.net.Uri;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/1
 */

public class FrescoImageUtil  {

    public static Uri getUriFromNet(String url) {
        if (TextUtils.isEmpty(url)) {
            return Uri.parse("");
        } else {
            return Uri.parse(url);
        }
    }

    public static Uri getUriFromAsset(String path){
        if (TextUtils.isEmpty(path)) {
            return Uri.parse("");
        } else {
            return Uri.parse("asset:///" + path);
        }
    }

    public static Uri getUriFromFile(String path) {
        if (TextUtils.isEmpty(path)) {
            return Uri.parse("");
        } else {
            return Uri.parse("file://" + path);
        }
    }


    public static Uri getUriFromRes(int resid) {
        if (resid == -1) {
            return Uri.parse("");
        } else {
            return Uri.parse("res:///" + resid);
        }
    }

    /**
     * 使用渐进式加载网络图片.
     * 只能用于网络图片加载
     *
     * @param simpleDraweeView 图片控件
     * @param url
     */
    public static void displayImgWithProgressive(SimpleDraweeView simpleDraweeView, String url) {
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(FrescoImageUtil.getUriFromNet(url))
                .setProgressiveRenderingEnabled(true)
                .build();
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);
    }

    /**
     * 从网络加载图片
     *
     * @param simpleDraweeView
     * @param url
     */
    public static void displayImgFromNetwork(SimpleDraweeView simpleDraweeView, String url) {
        simpleDraweeView.setImageURI(getUriFromNet(url));
    }

    /**
     * 显示网络图片或者本地图片.
     * 对于本地图片,还会根据拍照时手机附加的角度(比如三星手机)进行自动旋转.
     *
     * @param simpleDraweeView
     * @param url              url地址或者图片文件绝对路径
     */
    public static void displayImgWithRetate(SimpleDraweeView simpleDraweeView, String url) {
        if (url.startsWith("http")) {
            displayImgFromNetwork(simpleDraweeView, url);
        } else {
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(getUriFromFile(url))
                    .setAutoRotateEnabled(true)
                    .build();
            PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setOldController(simpleDraweeView.getController())
                    .build();
            simpleDraweeView.setController(controller);
        }
    }


    /**
     * @param simpleDraweeView
     * @param url
     * @param weight
     * @param height
     */
        public static void displayImgWithRetate(SimpleDraweeView simpleDraweeView, String url,int weight,int height) {
            if (url.startsWith("http")) {
                displayImgFromNetwork(simpleDraweeView, url);
            } else {

                ResizeOptions options=new ResizeOptions(weight,height);
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(getUriFromFile(url))
                        .setAutoRotateEnabled(true)
                        .setResizeOptions(options)
                        .build();
                PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(simpleDraweeView.getController())
                        .build();
                simpleDraweeView.setController(controller);
            }
    }
}
