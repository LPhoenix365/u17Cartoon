package com.pingan.u17.abum;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.pingan.u17.R;
import com.pingan.u17.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PhotoSelectActivity extends BaseActivity {

    private static final int CHOOSE_IMAGE = 100;
    @BindView(R.id.photo_select_rv)
    RecyclerView   photoSelectRv;
    @BindView(R.id.activity_album_and)
    RelativeLayout activityAlbumAnd;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_photo_select);
        mUnbinder = ButterKnife.bind(this);
        showPhoto();
        selectImage();
    }

    // 进入选择图片的界面
    private void selectImage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_IMAGE);
    }
    // 在onActivityResult()回调方法中进行数据获取
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // ... 进行一些判断处理
        Uri uri = data.getData();
        // ... 接下来进行图片显示
    }

    private void showPhoto() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
