package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.ArrayList;

public class check extends AppCompatActivity {

    private static final String[] PERMISSIONS=new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private static final int REQUEST_ALL_CODES=1;


    private ArrayList<ImageInfo> list=new ArrayList<>();

    private GridLayout gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        //手动扫描图片入库
        MediaScannerConnection.scanFile(this,new String[]{
                Environment.getExternalStorageState().toString()
        },null,null);

//        gl=findViewById(R.id.gl);
//
//
//        //加载图片列表
//        loadImageList();
//
//        //显示图像网络
//        showImageGrid();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==REQUEST_ALL_CODES){
            //加载图片列表
            loadImageList();

            //显示图像网络
            showImageGrid();
        }
    }

    //显示图像网络
    private void showImageGrid() {
        //清空
        gl.removeAllViews();

        for(ImageInfo info:list){
            ImageView view=new ImageView(this);

            Bitmap bitMap=BitmapFactory.decodeFile(info.path);
            view.setImageBitmap(bitMap);

            //设置图片的缩放类型
            view.setScaleType(ImageView.ScaleType.FIT_CENTER);

            //设置点击事件
            view.setOnClickListener(v->{

            });
            //将图片添加到网络布局
            gl.addView(view);

        }

    }

    //加载图片列表
    @SuppressLint("Range")
    private void loadImageList() {
        //MediaStore
        String[] columns=new String[]{
                MediaStore.Images.Media._ID,//图片编号
                MediaStore.Images.Media.TITLE,//图片标题
                MediaStore.Images.Media.SIZE,//图片大小
                MediaStore.Images.Media.DATA//图片路径
        };

        Cursor cursor=getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                columns,
                "_size < 307200",
                null,
                "_size DESC"
        );

        int count=0;
        if(cursor!=null){
            while (cursor.moveToNext() && count < 6) {
                ImageInfo info=new ImageInfo();
                info.id=cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID));
                info.name=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.TITLE));
                info.size=cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.SIZE));
                info.path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

                count++;
                list.add(info);
                Log.d("ning","images:"+info.toString());

            }
        }

    }
}