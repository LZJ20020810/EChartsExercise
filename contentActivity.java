package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.myapplication.R;

public class contentActivity extends AppCompatActivity {

    private smsGetObserver sss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        sss=new smsGetObserver(this);

        //注册一个内容观察器
        Uri uri= Uri.parse("content://sms");

        getContentResolver().registerContentObserver(uri,true,sss);


    }

    //取消注册
    protected void onDestroy(){
        super.onDestroy();
        getContentResolver().unregisterContentObserver(sss);
    }

    private static class smsGetObserver extends ContentObserver {
        private final Context mContext;
        public smsGetObserver(Context context){
            super(new Handler(Looper.getMainLooper()));
            this.mContext=context;
        }

        @Override
        public void onChange(boolean selfChange, @Nullable Uri uri) {
            super.onChange(selfChange, uri);

            //监听的具体实现
            if(uri==null){
                return;
            }
            if(uri.toString().equals("content://sms")||uri.toString().equals("content://sms/raw")){
                return;
            }

            //通过内容解析器获取符合条件的结果集游标
            Cursor cursor=mContext.getContentResolver().query(uri,new String[]{"address","body","date"},null,null,"date DESC");

            if(cursor.moveToNext()){
                //短信的发送号码
                @SuppressLint("Range") String sender=cursor.getString(cursor.getColumnIndex("address"));
                //短信的发送内容
                @SuppressLint("Range") String content=cursor.getString(cursor.getColumnIndex("content"));

                Log.d("ning",String.format("sender:%s,content:%s",sender,content));

            }
            cursor.close();

        }
    }
}