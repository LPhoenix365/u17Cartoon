package com.pingan.u17.abum;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.pingan.u17.R;
import com.pingan.u17.adapter.PhotoSelectAdapter;
import com.pingan.u17.base.BaseActivity;
import com.pingan.u17.bean.ImageItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PhotoSelectActivity extends BaseActivity{


    @BindView(R.id.photo_select_rv)
    RecyclerView   photoSelectRv;
    @BindView(R.id.activity_album_and)
    RelativeLayout activityAlbumAnd;
    private Unbinder mUnbinder;
    private ImageDataLoader mImageDataLoader;

    private ArrayList<ImageItem> mArrayList;
    private PhotoSelectAdapter mPhotoSelectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_photo_select);
        mUnbinder= ButterKnife.bind(this);
        photoSelectRv.setLayoutManager(new GridLayoutManager(this,3));

        mPhotoSelectAdapter = new PhotoSelectAdapter(this, mArrayList);

        photoSelectRv.setAdapter(mPhotoSelectAdapter);
        mImageDataLoader = new ImageDataLoader(this);
        mImageDataLoader.initLoader(null);
        mImageDataLoader.setLoaderImgListener(new ImageDataLoader.LoaderImgListener() {
            @Override
            public void loaderImg(ArrayList<ImageItem> imageFolders) {

                mPhotoSelectAdapter.refreshData(imageFolders);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
