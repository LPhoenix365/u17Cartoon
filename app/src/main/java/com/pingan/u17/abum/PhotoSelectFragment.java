package com.pingan.u17.abum;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.pingan.u17.R;
import com.pingan.u17.adapter.PhotoSelectAdapter;
import com.pingan.u17.bean.ImageItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PhotoSelectFragment extends Fragment{


    @BindView(R.id.photo_select_rv)
    RecyclerView   photoSelectRv;
    @BindView(R.id.activity_album_and)
    RelativeLayout activityAlbumAnd;
    private Unbinder        mUnbinder;
    private ImageDataLoader mImageDataLoader;
    private Callback mCallback;
    private ArrayList<ImageItem> mArrayList;
    private PhotoSelectAdapter   mPhotoSelectAdapter;
    private List<ImageItem> mSelectImages = new ArrayList<>();
    private Handler         handler       = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                ImageItem imageItem = (ImageItem) msg.obj;
                selectImageFromGrid(imageItem);
            }
        }
    };
    private int mDesireImageCount=5;
    private FragmentActivity mActivity;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (Callback) activity;
            mActivity = getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException("The Activity must implement MultiImageSelectorFragment.Callback interface...");
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_photo_select, container, false);
        mUnbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        photoSelectRv.setLayoutManager(new GridLayoutManager(mActivity, 3));

        mPhotoSelectAdapter = new PhotoSelectAdapter(mActivity, mArrayList, handler);

        photoSelectRv.setAdapter(mPhotoSelectAdapter);
        mImageDataLoader = new ImageDataLoader(mActivity);
        mImageDataLoader.initLoader(null);
        mImageDataLoader.setLoaderImgListener(new ImageDataLoader.LoaderImgListener() {
            @Override
            public void loaderImg(ArrayList<ImageItem> imageFolders) {

                mPhotoSelectAdapter.refreshData(imageFolders);
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }


    /**
     * 选择图片操作
     */
    //selectImageFromGrid(Image image, int mode)
    private void selectImageFromGrid(ImageItem imageItem) {

        if(imageItem != null) {
            if (mSelectImages.contains(imageItem)) {
                mSelectImages.remove(imageItem);
                mCallback.onImageUnselected(imageItem);
            } else {
                // 判断选择数量问题
                if(mDesireImageCount <= mSelectImages.size()){
                    Log.d("TAG", "最多只能选择"+mDesireImageCount+"张照片");
                    return;
                }
                mSelectImages.add(imageItem);
                if (mCallback != null) {
                    mCallback.onImageSelected(imageItem);
                }
            }
        }

    }


    /**
     * 回调接口
     */
    public interface Callback{
        void onImageSelected(ImageItem imageItem);
        void onImageUnselected(ImageItem imageItem);
    }
}
