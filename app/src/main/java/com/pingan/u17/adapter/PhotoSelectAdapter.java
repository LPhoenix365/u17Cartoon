package com.pingan.u17.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pingan.u17.R;
import com.pingan.u17.abum.PicassoImageLoader;
import com.pingan.u17.bean.ImageItem;
import com.pingan.u17.util.ToolUtils;
import com.pingan.u17.widget.SuperCheckBox;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/4/18
 */
public class PhotoSelectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int ITEM_CARAME = 1;
    private static final int ITEM_ALBUM  = 2;


    private       LayoutInflater       mInflater;
    private       Activity             mActivity;
    private       ArrayList<ImageItem> images;
    private  int                  mImageItemWidth;

    /**
     * 构造方法
     */
    public PhotoSelectAdapter(Activity activity, ArrayList<ImageItem> images) {
        this.mActivity = activity;
        if (images == null || images.size() == 0) this.images = new ArrayList<>();
        else this.images = images;
        mImageItemWidth = ToolUtils.getImageItemWidth(activity);
        mInflater = LayoutInflater.from(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_CARAME) {
            View view = mInflater.inflate(R.layout.adapter_camera_item, parent, false);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mImageItemWidth);
            view.setLayoutParams(params);
            CarameViewHolder carameViewHolder = new CarameViewHolder(view);
            return carameViewHolder;
        } else {
            View view = mInflater.inflate(R.layout.adapter_image_list_item, parent, false);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mImageItemWidth);
            view.setLayoutParams(params);
            AlbumViewHolder albumViewHolder = new AlbumViewHolder(view);
            return albumViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CarameViewHolder) {
            ((CarameViewHolder) holder).bindCarame(position);
        } else {
            ((AlbumViewHolder) holder).bindAlbum(position);
        }
    }

    @Override
    public int getItemCount() {
        return images.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ITEM_CARAME : ITEM_ALBUM;
    }

    //此方法模仿listview
    public ImageItem getItem(int position) {
        return position == 0 ? null : images.get(position - 1);
    }

    /**
     * @param images 更新数据
     */
    public void refreshData(ArrayList<ImageItem> images) {
        if (images == null || images.size() == 0) this.images = new ArrayList<>();
        else this.images = images;
        notifyDataSetChanged();
    }


    class CarameViewHolder extends RecyclerView.ViewHolder {
        View rootView;

        @BindView(R.id.camera)
        RelativeLayout camera;

        public CarameViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            rootView = itemView;
        }

        void bindCarame(int position) {

        }

        @OnClick({R.id.camera})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.camera:
                    //开启相机
                    break;
            }
        }
    }


    class AlbumViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        @BindView(R.id.iv_thumb)
        ImageView     ivThumb;
        @BindView(R.id.mask)
        View          mask;
        @BindView(R.id.cb_check)
        SuperCheckBox cbCheck;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            rootView = itemView;
        }

        void bindAlbum(int position) {
            ImageItem imageItem = getItem(position);
             PicassoImageLoader.displayImage(mActivity,imageItem.path,ivThumb,mImageItemWidth,mImageItemWidth);
            // GlideImageLoader.displayImage(mActivity,imageItem.path,ivThumb,mImageItemWidth,mImageItemWidth);

        }

        @OnClick({R.id.iv_thumb, R.id.cb_check})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_thumb:
                    //查看原图
                    break;
                case R.id.cb_check:
                    break;
            }
        }
    }


}
