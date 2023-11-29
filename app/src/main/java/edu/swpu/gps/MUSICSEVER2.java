package edu.swpu.gps;

//public class TestOneService {
//}

import static android.content.Intent.getIntent;

import android.Manifest;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Kathy on 17-2-6.
 */

public class MUSICSEVER2 extends Service {


    String path2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();

    String path = path2+"/Amazing Grace.mp3";
    MediaPlayer mediaPlayer=new MediaPlayer();

    private String mu_name;








    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 处理接收到的广播
//            Log.d("MyService", "子线程接收到广播");
            String message = intent.getStringExtra("message");
            //在这里进行UI更新

//            Log.d("主线程你再骂！","主线程你再骂！");
            Log.d("MyService", "子线程收到：");

            String[] parts = message.split("---spline---");
            Log.d("LINEEEEEEEEEEE", Arrays.toString(parts));




            if(parts[0].equals("path")){
                String[] parts2 = parts[1].split("/");
                int lons=parts2.length;
                mu_name=parts2[lons-1];
                Log.d("GGGGGGGGGGGGGGEEEEEEEEEEEETTTTTTTTTT",message);
                            try {
                                if(mediaPlayer.isPlaying()){
                                    mediaPlayer.stop();
//                                    mediaPlayer.release();
                                    mediaPlayer.reset();
                                }else {
                                    mediaPlayer.stop();
//                                    mediaPlayer.release();
                                    mediaPlayer.reset();
                                }

//                                mediaPlayer.reset();
//                    mediaPlayer.setDataSource("https://freepd.com/music/Amazing%20Grace.mp3") ;
                mediaPlayer.setDataSource(parts[1]) ;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }



            //需使用异步缓冲
//                mediaPlayer.prepareAsync() ;
            try {
                mediaPlayer.prepare();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mediaPlayer.start();
            }

            if(parts[0].equals("cgpc")){
                int rt=mediaPlayer.getCurrentPosition();
                int lon=mediaPlayer.getDuration();
                float sf=(100)*rt/lon;
                int dest= Integer.parseInt(parts[1]);
                float tim=dest*lon/100;
                mediaPlayer.seekTo((int) tim);

            }


            if(parts[0].equals("pause")){
                Log.d("PPPPPPPPP", "AAAAAAAAAA");
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }else {
                    mediaPlayer.start();
                }

//                int rt=mediaPlayer.getCurrentPosition();
//                int lon=mediaPlayer.getDuration();
//                float sf=(100)*rt/lon;
//                int dest= Integer.parseInt(parts[1]);
//                float tim=dest*lon/100;


            }
//            try {
////                    mediaPlayer.setDataSource("https://freepd.com/music/Amazing%20Grace.mp3") ;
//                mediaPlayer.setDataSource(path) ;
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//
//
//            //需使用异步缓冲
////                mediaPlayer.prepareAsync() ;
//            try {
//                mediaPlayer.prepare();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            mediaPlayer.start();



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
Intent intent = new Intent("com.example.MY_ACTION");
//        广播



    @Override
    public void onCreate() {
        Log.i("Kathy_LINE!!!!!!_START!!!!!!","onCreate - Thread ID = " + Thread.currentThread().getId());



        // 注册广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction(MainActivity.MY_ACTION);
        registerReceiver(receiver, filter);



//        try {
////                    mediaPlayer.setDataSource("https://freepd.com/music/Amazing%20Grace.mp3") ;
//            mediaPlayer.setDataSource(path) ;
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//
//        //需使用异步缓冲
////                mediaPlayer.prepareAsync() ;
//        try {
//            mediaPlayer.prepare();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        mediaPlayer.start();

//        SeekBar sb2 = (SeekBar) findViewById(R.id.seekbar2);
        class MyThread extends Thread {

            // 步骤2：复写run（），内容 = 定义线程行为
            @Override
            public void run(){
                String zt="isplay";




                while (true){
                    int rt=mediaPlayer.getCurrentPosition();
                    int lon=mediaPlayer.getDuration();
                    float sf=(100)*rt/lon;

//                    sb2.setProgress((int) sf);

//                            Log.d("进度", String.valueOf(rt));
//                            Log.d("进度", String.valueOf(lon));

                            Log.d("进度", String.valueOf(sf));
                            int rr= (int) sf;

                            if(mediaPlayer.isPlaying()){
                                zt="isplay";
                            }else {
                                zt="noplay";
                            }

                    String msg="plpc---spline---"+rr+"---spline---"+zt;
                    intent.putExtra("message", msg);
                    // 发送广播
                    sendBroadcast(intent);


                    String msg2="nowname---spline---"+mu_name;
                    intent.putExtra("message", msg2);
                    // 发送广播
                    sendBroadcast(intent);



                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }





                }

            }
        }

        // 步骤3：创建线程对象，即 实例化线程类
        MyThread mt=new MyThread();

        // 步骤4：通过 线程对象 控制线程的状态，如 运行、睡眠、挂起  / 停止
        // 此处采用 start（）开启线程
        mt.start();




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




        int b=0;
        while (true){
            try {
                b=b+1;
                Log.i("Kathy", "源神！线程！启动！主线程说"+dd+"onStartCommand - startId = "  + ", Thread ID = " + Thread.currentThread().getId());


                // 添加需要传递的数据
                intent.putExtra("message", "Hello, World!主线程我囸你仙人");
                // 发送广播
                sendBroadcast(intent);

                if(b==3){
                    intent.putExtra("message", "-1");
                    // 发送广播
                    sendBroadcast(intent);
                }



//                Intent intent = new Intent("MY_MESSAGE");
//                intent.putExtra("message","A message from sub-thread.");
//                sendBroadcast(intent);



                Thread.sleep(3000);

                if(b>100){
//                    return super.onStartCommand(intent, flags, startId);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent, flags, startId);
        String jg=intent.getStringExtra("yiyan");
        Log.i("Kathy", "源神sfgwsffse！启动！主线程说"+ jg);


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

//                mHandler.sendEmptyMessage(1);

                ssr();
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