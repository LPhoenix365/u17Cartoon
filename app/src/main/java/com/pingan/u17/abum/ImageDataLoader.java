package com.pingan.u17.abum;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.pingan.u17.bean.ImageFolder;
import com.pingan.u17.bean.ImageItem;

import java.io.File;
import java.util.ArrayList;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/18
 */

public class ImageDataLoader implements LoaderManager.LoaderCallbacks<Cursor> {


    private FragmentActivity mActivity;
    ArrayList<ImageFolder> imageFolders = new ArrayList<>();

    private final String[] IMAGE_PROJECTION = {     //查询图片需要的数据列
            MediaStore.Images.Media.DISPLAY_NAME,   //图片的显示名称  aaa.jpg
            MediaStore.Images.Media.DATA,           //图片的真实路径  /storage/emulated/0/pp/downloader/wallpaper/aaa.jpg
            MediaStore.Images.Media.SIZE,           //图片的大小，long型  132492
            MediaStore.Images.Media.WIDTH,          //图片的宽度，int型  1920
            MediaStore.Images.Media.HEIGHT,         //图片的高度，int型  1080
            MediaStore.Images.Media.MIME_TYPE,      //图片的类型     image/jpeg
            MediaStore.Images.Media.DATE_ADDED,     //图片被添加的时间，long型  1450518608
    };


    private final int REQUEST_ALL  = 1;//获取全部图片
    private final int REQUEST_PATH = 2;//获取指定路径的图片
    private LoaderImgListener mLoaderImgListener;

    public ImageDataLoader(FragmentActivity activity) {
        mActivity = activity;
    }


    public void initLoader(String path) {
        LoaderManager loaderManager = mActivity.getSupportLoaderManager();
        loaderManager.initLoader(REQUEST_ALL, null, this);
    }


    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(mActivity, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                IMAGE_PROJECTION, null, null, IMAGE_PROJECTION[6] + " DESC");
        return cursorLoader;
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null) {
            ArrayList<ImageItem> imageItemsAll = new ArrayList<>();
            imageFolders.clear();
            while (data.moveToNext()) {
                String name = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                String path = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
                long size = data.getLong(data.getColumnIndexOrThrow(IMAGE_PROJECTION[2]));
                int width = data.getInt(data.getColumnIndexOrThrow(IMAGE_PROJECTION[3]));
                int height = data.getInt(data.getColumnIndexOrThrow(IMAGE_PROJECTION[4]));
                String mime_type = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[5]));
                long add_time = data.getLong(data.getColumnIndexOrThrow(IMAGE_PROJECTION[6]));
                ImageItem imageItem = new ImageItem(name, path, size, width, height, mime_type, add_time);
                //所有图片的合集
                imageItemsAll.add(imageItem);

                File imgFile = new File(path);
                File imgParentFile = imgFile.getParentFile();
                ImageFolder imageFolder = new ImageFolder();
                imageFolder.name = imgParentFile.getName();
                imageFolder.path = imgParentFile.getAbsolutePath();

                if (imageFolders.contains(imageFolder)) {
                    //父文件夹 已包含在
                    imageFolders.get(imageFolders.indexOf(imageFolder)).images.add(imageItem);
                } else {
                    //尚未包含
                    ArrayList<ImageItem> images = new ArrayList<>();
                    images.add(imageItem);
                    imageFolder.cover = imageItem;
                    imageFolder.images = images;
                    imageFolders.add(imageFolder);
                }
            }
            //获取完毕 通知外界
            if (mLoaderImgListener != null) {
                mLoaderImgListener.loaderImg(imageItemsAll);
            }
        }
    }


    @Override
    public void onLoaderReset(Loader loader) {
        return;
    }

    public interface LoaderImgListener {
        void loaderImg(ArrayList<ImageItem> imageFolders);
    }

    public void setLoaderImgListener(LoaderImgListener loaderImgListener) {
        mLoaderImgListener = loaderImgListener;
    }

}
