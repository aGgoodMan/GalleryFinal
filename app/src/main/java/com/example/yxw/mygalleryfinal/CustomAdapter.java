package com.example.yxw.mygalleryfinal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by yxw on 2016/12/26.
 */

public class CustomAdapter extends BaseAdapter{
    LayoutInflater inflater;
    Context context;
    List<PhotoInfo> list;

    public CustomAdapter(Context context, List<PhotoInfo> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.img_layout,viewGroup,false);
        ImageView img = (ImageView) view.findViewById(R.id.imageview);
        PhotoInfo photoInfo = list.get(i);
        Log.e("====", "===getView==="+photoInfo.getPhotoPath());
        Glide.with(context).load(photoInfo.getPhotoPath()).into(img);
        return view;
    }
}
