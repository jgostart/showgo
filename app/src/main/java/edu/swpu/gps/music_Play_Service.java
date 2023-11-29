package edu.swpu.gps;

//public class TestOneService {
//}

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Kathy on 17-2-6.
 */

public class music_Play_Service extends Service {







    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 处理接收到的广播
//            Log.d("MyService", "子线程接收到广播");
            String message = intent.getStringExtra("message");
            //在这里进行UI更新

//            Log.d("主线程你再骂！","主线程你再骂！");
            Log.d("MUSIC_SEVER---->>>>>>>>", "子线程收到：");

            Log.d("MUSIC_SEVER---->>>>>>>>", message);

            if(message.equals("Hello, World!主线程我囸你仙人")){
                Log.d(">>>>>>>>>>>>>>>>", "WWWWWWWWWWWW");
            }
//            Log.d("MyService", String.valueOf(context));
//            String rr=intent.getStringExtra();
//            Log.d("MyService", rr);

        }
    };

//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        // 注册广播接收器
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(MainActivity.MY_ACTION);
//        registerReceiver(receiver, filter);
//    }

//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//        // 取消注册广播接收器
//        unregisterReceiver(receiver);
//    }




    @Override
    public void onCreate() {
        Log.i("Kathy_LINE!!!!!!_START!!!!!!","onCreate - Thread ID = " + Thread.currentThread().getId());


        // 注册广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction(MainActivity.MY_ACTION);
        registerReceiver(receiver, filter);



        super.onCreate();
    }



    public void ssr(){
//        Intent intent = getIntent();
//        String dd=intent.getStringExtra("sg");
        String dd="dfd";

        Intent intent = new Intent("com.example.MY_ACTION");
//        广播


//        registerReceiver(mBroadcastReceiver,new IntentFilter("MY_MESSAGE"));
        //开启一个子线程
//        registerReceiver(mBroadcastReceiver,new IntentFilter("MY_MESSAGE"));



//        int b=0;
//                while (true){
//            try {
//                b=b+1;
//                Log.i("Kathy", "源神！线程！启动！主线程说"+dd+"onStartCommand - startId = "  + ", Thread ID = " + Thread.currentThread().getId());
//
//
////                // 添加需要传递的数据
////                intent.putExtra("message", "Hello, World!主线程我囸你仙人");
////                // 发送广播
////                sendBroadcast(intent);
//
//
//
////                Intent intent = new Intent("MY_MESSAGE");
////                intent.putExtra("message","A message from sub-thread.");
////                sendBroadcast(intent);
//
//
//
//                Thread.sleep(3000);
//
//                if(b>100){
////                    return super.onStartCommand(intent, flags, startId);
//                }
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent, flags, startId);
        String jg=intent.getStringExtra("yiyan");
        Log.i("Kathy", "源神sfgwsffse！启动！主线程说"+ jg);

//                mHandler.sendEmptyMessage(1);

//
//        while (true){
//            try {
//                b=b+1;
//                Log.i("Kathy", "线程！启动！onStartCommand - startId = " + startId + ", Thread ID = " + Thread.currentThread().getId());
//
//                Thread.sleep(3000);
//
//                if(b>100){
//                    return super.onStartCommand(intent, flags, startId);
//                }
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
        Log.i("Kathy", "线程！启动！onStartCommand - startId = " + startId + ", Thread ID = " + Thread.currentThread().getId());



        new Thread(new Runnable() {
            @Override

            public void run() {
                Log.e("bai", "executed at " + new Date().toString());

//                String path2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
//
//                String path = path2+"/creep.m4a";
//                MediaPlayer mediaPlayer=new MediaPlayer();
//
//                try {
////                    mediaPlayer.setDataSource("https://freepd.com/music/Amazing%20Grace.mp3") ;
//                    mediaPlayer.setDataSource(path) ;
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//
//
//                //需使用异步缓冲
////                mediaPlayer.prepareAsync() ;
//                try {
//                    mediaPlayer.prepare();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//                mediaPlayer.start();


//                ssr();
            }

        }).start();



        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("Kathy", "线程！岂懂？onBind - Thread ID = " + Thread.currentThread().getId());
        return null;
    }

    @Override
    public void onDestroy() {
        Log.i("Kathy", "onDestroy - Thread ID = " + Thread.currentThread().getId());


        // 取消注册广播接收器
        unregisterReceiver(receiver);


        super.onDestroy();
    }
}