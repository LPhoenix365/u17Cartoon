package com.pingan.u17.abum;

import android.os.Bundle;

import com.pingan.u17.R;
import com.pingan.u17.base.BaseActivity;
import com.pingan.u17.bean.ImageItem;

import java.util.ArrayList;
import java.util.List;

public class PhotoSelectActivity extends BaseActivity implements PhotoSelectFragment.Callback{

    private List<ImageItem> resultList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_select2);
        resultList =new ArrayList<ImageItem>();
    }

    @Override
    public void onImageSelected(ImageItem imageItem) {
        resultList.add(imageItem);
    }

    @Override
    public void onImageUnselected(ImageItem imageItem) {
        resultList.remove(imageItem);
    }

    private List<ImageItem> confirm(){
       return resultList;
    }
}
