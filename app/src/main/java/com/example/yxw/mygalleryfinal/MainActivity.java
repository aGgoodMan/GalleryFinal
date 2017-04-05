package com.example.yxw.mygalleryfinal;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class MainActivity extends AppCompatActivity {
    String path = Environment.getExternalStorageDirectory() + File.separator + "yxw";
    private ImageView img;
    cn.finalteam.galleryfinal.widget.HorizontalListView list;
    private CustomAdapter adapter;
    List<PhotoInfo> piList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
//        setAdapter();
    }

    private void findViews() {
        img = (ImageView) findViewById(R.id.img);
        list = (cn.finalteam.galleryfinal.widget.HorizontalListView) findViewById(R.id.list);
    }

    private void setAdapter() {
        adapter = new CustomAdapter(this, piList);
        list.setAdapter(adapter);
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.openCamera:
                openCamera();
                break;
            case R.id.openList:
                openList();
                break;
            case R.id.openCrop:
                openCrop();
                break;
        }
    }

    private void openCrop() {
        ThemeConfig config = ThemeConfig.ORANGE;
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableEdit(true)
                .setMutiSelectMaxSize(8)
                .setEnableCrop(true)
                .setEnableCamera(true)
                .setEnablePreview(true)
                .build();
        ImageLoader loader = new GlideImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(this, loader, config).build();
        GalleryFinal.init(coreConfig);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "yxw/IMG20161226114935.jpg";
        GalleryFinal.openCrop(1002, functionConfig, path, onHanlderResultCallback);
    }

    private void openList() {
        ThemeConfig config = ThemeConfig.GREEN;
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableEdit(true)
                .setMutiSelectMaxSize(8)
                .setEnableCrop(true)
                .setEnableCamera(true)
                .setEnablePreview(true)
                .build();
        ImageLoader loader = new GlideImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(this, loader, config).build();
        GalleryFinal.init(coreConfig);
        GalleryFinal.openGalleryMuti(1001, functionConfig, onHanlderResultCallback);

    }

    private void openCamera() {
        ThemeConfig config = ThemeConfig.DARK;
        ImageLoader loader = new GlideImageLoader();
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setMutiSelectMaxSize(8)
                .setEnableCamera(true)
                .setEnableCrop(true)
                .setEnableEdit(true)
                .build();
        CoreConfig coreConfig = new CoreConfig.Builder(this, loader, config)
                .setFunctionConfig(functionConfig)
                .setNoAnimcation(false)
                .setEditPhotoCacheFolder(new File(path))
                .setTakePhotoFolder(new File(path))
                .build();
        GalleryFinal.init(coreConfig);
        GalleryFinal.openGalleryMuti(1001, functionConfig, onHanlderResultCallback);
    }

    private GalleryFinal.OnHanlderResultCallback onHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
//            Toast.makeText(MainActivity.this, String.valueOf(resultList.size()), Toast.LENGTH_SHORT).show();
//            Glide.with(MainActivity.this).load(resultList.get(0).getPhotoPath()).into(img);
//            if (resultList.size() > 0) {
//                Toast.makeText(MainActivity.this, "有路径:" + resultList.get(0).getPhotoPath(), Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(MainActivity.this, "没有路径", Toast.LENGTH_SHORT).show();
//            }
            if (resultList != null) {
                Toast.makeText(MainActivity.this, "======" + resultList.size(), Toast.LENGTH_SHORT).show();
                    piList.clear();
                    piList.addAll(resultList);
//                    adapter.notifyDataSetChanged();
                setAdapter();

//                adapter = new CustomAdapter(MainActivity.this, resultList);
//                list.setAdapter(adapter);
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {

        }
    };
}
