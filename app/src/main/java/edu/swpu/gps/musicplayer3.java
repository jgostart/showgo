package edu.swpu.gps;

//import static com.google.android.material.snackbar.BaseTransientBottomBar.handler;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class musicplayer3 extends Activity {



    private Button play ;
    private Button pauseButton;
    private Button stopButton;

    private String now_music_file_name="Very Brady Special.mp3";

    private TextView nowmusic;

    private  int play_type=0;
    SeekBar sb2;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 处理接收到的广播
//            Log.d("MyService", "子线程接收到广播");
            String message = intent.getStringExtra("message");
            //在这里进行UI更新

//            Log.d("主线程你再骂！","主线程你再骂！");


//            Log.d("停停停停停停停停停停停停停停停停停停停停", "停停停停停停停停停停停停");
//            Log.d("停停停停停停停停停停停停停停停停停停停停", String.valueOf(play_type));

            String[] parts = message.split("---spline---");
            Log.d("LINEEEEEEEEEEE", Arrays.toString(parts));


//            int rr= sz;


            if (parts[0].equals("plpc")) {
//                播放进度
//                sb2.setProgress(rr);
                Log.d("水水水水水水水水水水水水", Arrays.toString(parts));
                String sz = parts[1];
                int rr = Integer.parseInt(sz);
                sb2.setProgress(rr);

                if(parts[2].equals("isplay")){
            Log.d("正在播放", "<<<<<<<<<<<<<>>>>>>>>>>>>>>");

                    play.setText("暂停");
                }else {
                    play.setText("播放");
                }

            }
            if (parts[0].equals("nowname")) {
//                正在播放音乐名
//                sb2.setProgress(rr);
                Log.d("水水水水水水水水水水水水", Arrays.toString(parts));
                now_music_file_name=parts[1];
                nowmusic.setText(parts[1]);

            }
//            Log.d("MyService", String.valueOf(context));
//            String rr=intent.getStringExtra();
//            Log.d("MyService", rr);

        }
    };


    String path2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();

//    String path = path2+"/creep.m4a";

    Intent intent = new Intent("com.example.MY_ACTION");
//        广播


    MediaPlayer mediaPlayer=new MediaPlayer();
    public interface Callback {
        void onCallback(

        );
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play3);


//        mp2list=mp3_file_get_helper();






        // 注册广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction(MainActivity.MY_ACTION);
        registerReceiver(receiver, filter);



        String iid = getIntent().getStringExtra("key");
        String iid2 = getIntent().getStringExtra("kouwei");
        String iid3 = getIntent().getStringExtra("name");
        String iid4 = getIntent().getStringExtra("shicai");
        String iid5 = getIntent().getStringExtra("caiputx");
        String iid6 = getIntent().getStringExtra("ziliao");
        String iid7 = getIntent().getStringExtra("id");

        String path = path2+"/"+iid3+".mp3";
        Log.d("PPPPPPPPPT", path);

        Log.d("收到", iid);
        Log.d("收到", iid2);
        Log.d("收到", iid3);
        Log.d("收到", iid4);
        Log.d("收到", iid5);
        Log.d("收到", iid6);
        Log.d("收到", iid7);


       play = findViewById(R.id.play);

        Button fst = findViewById(R.id.fst);
        Button next=findViewById(R.id.next);

        nowmusic=findViewById(R.id.nowmusic);

//        SeekBar sb2 = (SeekBar) findViewById(R.id.seekbar2);
        sb2 = (SeekBar) findViewById(R.id.seekbar2);


        String msg="path---spline---"+path;
        intent.putExtra("message", msg);
        // 发送广播
        sendBroadcast(intent);




//                mediaPlayer = MediaPlayer.create(this, R.raw.creep); // 替换为你的音频文件



//                mediaPlayer = MediaPlayer.create(this, R.raw.creep); // 替换为你的音频文件





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









//        加载cover

        //                ImageView img=  findViewById(R.id.book_img);

        ImageView cv=findViewById(R.id.cover);
        ImageOptions imageOptions;
        imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(480), DensityUtil.dip2px(480))
                .setRadius(DensityUtil.dip2px(5))
// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
// 加载中或错误图片的ScaleType
//.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.drawable.imgbad)
                .build();
//            x.image().bind(img, "https://5b0988e595225.cdn.sohucs.com/images/20170922/fe15d13a3e764a3bbaede340e47692ca.jpeg", imageOptions);//加载图片的控件，和加载网络图片的地址

        x.image().bind(cv, iid7, imageOptions);//加载图片的控件，和加载网络图片的地址



        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if( play_type==0){
////                    等于0是没有播放
//                    Log.d("收到", "哎哟你干嘛嗨嗨哎哟");
//                    // 添加需要传递的数据
//                    String msg="path---spline---"+path;
//                    intent.putExtra("message", msg);
//                    // 发送广播
//                    sendBroadcast(intent);
//                }

                if( true){
//                    等于0是没有播放
                    Log.d("收到", "哎哟你干嘛嗨嗨哎哟");
                    // 添加需要传递的数据
                    String msg="pause---spline---oooop";
                    intent.putExtra("message", msg);
                    // 发送广播
                    sendBroadcast(intent);
                }

//                mediaPlayer.reset();
//
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//
//                mediaPlayer.reset();



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



                // 步骤1：创建线程类 （继承自Thread类）
//                class MyThread extends Thread {
//
//                    // 步骤2：复写run（），内容 = 定义线程行为
//                    @Override
//                    public void run(){
//
//
//
//
//                        while (true){
//                            int rt=mediaPlayer.getCurrentPosition();
//                            int lon=mediaPlayer.getDuration();
//                            float sf=(100)*rt/lon;
//
//                            sb2.setProgress((int) sf);
//
////                            Log.d("进度", String.valueOf(rt));
////                            Log.d("进度", String.valueOf(lon));
////
////                            Log.d("进度", String.valueOf(sf));
//
//                            try {
//                                Thread.sleep(500);
//                            } catch (InterruptedException e) {
//                                throw new RuntimeException(e);
//                            }
//
//
//
//
//
//                        }
//
//                    }
//                }
//
//                // 步骤3：创建线程对象，即 实例化线程类
//                MyThread mt=new MyThread();
//
//                // 步骤4：通过 线程对象 控制线程的状态，如 运行、睡眠、挂起  / 停止
//                // 此处采用 start（）开启线程
//                mt.start();




//                int yy=mediaPlayer.getDuration();





            }





        });

//        List<String> mpln=mp3_file_name_get_helper();
        List<String> mpln=mp3_file_get_helper();

        int lens=mpln.size();
        Log.d("踩踩", String.valueOf(mpln.get(0)));
//        Log.d("踩踩", iid3);

        Log.d("踩踩", now_music_file_name);

//        Log.d("踩踩", String.valueOf(mpl));


        fst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mediaPlayer.pause();
                int ind=mpln.indexOf(now_music_file_name);
                String to_play="";
                if(ind==0){
                    to_play=mpln.get(lens-1);
                }else {
                    to_play=mpln.get(ind-1);
                }
                String msg="path---spline---"+path2+"/"+to_play;
                intent.putExtra("message", msg);
                // 发送广播
                sendBroadcast(intent);

//                mediaPlayer.start();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mediaPlayer.stop();
//                mediaPlayer.reset();
//                mediaPlayer.release();
                String to_play="";

                int ind=mpln.indexOf(now_music_file_name);
                if(ind==lens-1){
                    to_play=mpln.get(0);
                }else {
                    to_play=mpln.get(ind+1);
                }

//                [path, /storage/emulated/0/Download/A Very Brady Special.mp3]
                String msg="path---spline---"+path2+"/"+to_play;
                Log.d("踩踩", msg);
                intent.putExtra("message", msg);
                // 发送广播
                sendBroadcast(intent);

            }
        });



        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener( ) {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //手动调节进度

                int dest = seekBar.getProgress();


//                int time =mediaPlayer.getDuration();
//                int time =yy;

                int max = seekBar.getMax();


//                int rt=mediaPlayer.getCurrentPosition();
//                int lon=mediaPlayer.getDuration();
//                float sf=(100)*rt/lon;
//                float tim=dest*lon/100;
//                mediaPlayer.seekTo((int) tim);


                String msg="cgpc---spline---"+dest;
                intent.putExtra("message", msg);
                // 发送广播
                sendBroadcast(intent);

                Log.d("进度》》》》》》》", String.valueOf(dest));

//                mediaPlayer.seekTo(time * dest / max);
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                // TODO Auto-generated method stub

            }
        });





    }



    public List<String> mp3_file_name_get_helper(){
        final int PERMISSION_REQUEST_CODE = 1;
        ListView listView;
        List<String> mp3Files;
        mp3Files = new ArrayList<>();

        // 检查并请求读取存储权限
        if (ContextCompat.checkSelfPermission(musicplayer3.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(musicplayer3.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE);
        } else {
//            getMp3Files();

            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

            File directory = new File(path);
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".mp3")) {
                        String fn=file.getName();
                        String[] parts = fn.split("\\.");
                        mp3Files.add(parts[0]);
                    }
                    if (file.isFile() && file.getName().endsWith(".m4a")) {
//                        mp3Files.add(file.getName());
                        String fn=file.getName();
                        String[] parts = fn.split("\\.");
                        mp3Files.add(parts[0]);
                    }
                }
            }

//            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mp3Files);
//        listView.setAdapter(adapter);
            return mp3Files;

        }



        return mp3Files;






    }
    public  List<String> mp3_file_get_helper() {
        final int PERMISSION_REQUEST_CODE = 1;
        ListView listView;
        List<String> sse;
        sse = new ArrayList<>();

        // 检查并请求读取存储权限
        if (ContextCompat.checkSelfPermission(musicplayer3.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(musicplayer3.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE);
        } else {
//            getMp3Files();

            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

            File directory = new File(path);
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".mp3")) {
                        sse.add(file.getName());
//                        Log.d("CAOCAOCACAOCAOC",file.getName());
                    }
                    if (file.isFile() && file.getName().endsWith(".m4a")) {
                        sse.add(file.getName());
                    }
                }
            }

//            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mp3Files);
//        listView.setAdapter(adapter);
            return sse;

        }
        return sse;

    }


        @Override
    public void onDestroy() {
        Log.i("Kathy", "onDestroy - Thread ID = " + Thread.currentThread().getId());


        // 取消注册广播接收器
        unregisterReceiver(receiver);


        super.onDestroy();
    }

}
//        playButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                playAudio();
//            }
//        });
//
//        pauseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                pauseAudio();
//            }
//        });
//
//        stopButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                stopAudio();
//            }
//        });
//    }

//    private void playAudio() {
//        if (!mediaPlayer.isPlaying()) {
//            mediaPlayer.start();
//        }
//    }
//
//    private void pauseAudio() {
//        if (mediaPlayer.isPlaying()) {
//            mediaPlayer.pause();
//        }
//    }
//
//    private void stopAudio() {
//        if (mediaPlayer.isPlaying()) {
//            mediaPlayer.stop();
//
//            try {
//                mediaPlayer.setDataSource(path);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
////            mediaPlayer = MediaPlayer.create(this, Uri.parse(path)); // 重新创建 MediaPlayer 对象
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (mediaPlayer != null) {
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
//    }




