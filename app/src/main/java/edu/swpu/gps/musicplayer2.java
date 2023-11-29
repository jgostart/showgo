package edu.swpu.gps;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

//import androidx.recyclerview.widget.RecyclerView;

//import androidx.recyclerview.widget.LinearLayoutManager;

//import java.util.logging.Handler;

public class musicplayer2 extends Activity {






//播放器状态
//    private Button playPauseButton;
//    private ProgressBar progressBar;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;


//    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        mRecyclerView.setLayoutManager(linearLayoutManager);


//    MediaPlayer mediaPlayer;

    SeekBar seekBar;

    //本地歌曲的路径
    String path2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();

    String path = path2+"/A%20Very%20Brady%20Special.mp3";


//    //处理进度条更新
//    Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 0:
//                    //更新进度
//                    int position = mediaPlayer.getCurrentPosition();
//
//                    int time = mediaPlayer.getDuration();
//                    int max = seekBar.getMax();
//
//                    seekBar.setProgress(position * max / time);
//                    break;
//                default:
//                    break;
//            }
//
//        }
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musicplayer);

        Log.d("PPPPPPPPPT", path);


        String iid = getIntent().getStringExtra("key");
        String iid2 = getIntent().getStringExtra("kouwei");
        String iid3 = getIntent().getStringExtra("name");
        String iid4 = getIntent().getStringExtra("shicai");
        String iid5 = getIntent().getStringExtra("caiputx");
        String iid6 = getIntent().getStringExtra("ziliao");
        String iid7 = getIntent().getStringExtra("id");


        Log.d("收到", iid);
        Log.d("收到", iid2);
        Log.d("收到", iid3);
        Log.d("收到", iid4);
        Log.d("收到", iid5);
        Log.d("收到", iid6);
        Log.d("收到", iid7);



        Button    playPauseButton = findViewById(R.id.playButton);

        seekBar = (SeekBar) findViewById(R.id.seekbar);
        Button progressBar = findViewById(R.id.pauseButton);

        // 指定音乐文件的路径
        String musicPath = path;
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(musicPath);
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("播放？？？？？", ">>>>>>>>>>>>");
                if (isPlaying) {
                    pauseMusic();
                } else {
                    playMusic();
                }
            }
        });

        // 更新进度条
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (mediaPlayer != null) {
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    seekBar.setProgress(currentPosition);
                }
                sendEmptyMessageDelayed(0, 1000); // 每秒更新一次
            }
        };

        handler.sendEmptyMessage(0); // 开始更新进度条
    }

    private void playMusic() {
        mediaPlayer.start();
        isPlaying = true;
//        playPauseButton.setText("暂停");
//
//        progressBar.setMax(mediaPlayer.getDuration());
    }

    private void pauseMusic() {
        mediaPlayer.pause();
        isPlaying = false;
//        playPauseButton.setText("开始");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }





//        init();
        //初始化播放button
        Button playButton = (Button) findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                 TODO Auto-generated method stub
//                Toast.makeText(musicplayer2.this, "time is " , Toast.LENGTH_SHORT).show();
//                Toast.makeText(musicplayer2.this, "time is " + mediaPlayer.getDuration(), Toast.LENGTH_SHORT).show();

//                play();
            }

        });

        //初始化暂停button
        Button pauseButton = (Button) findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
//                pause();
            }
        });

        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //手动调节进度
                // TODO Auto-generated method stub
                int dest = seekBar.getProgress();
                int time = mediaPlayer.getDuration();
                int max = seekBar.getMax();

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

    //初始化音乐播放
//    void init() {
//
//        Log.d("START!!!!", "初始化！");
//        //进入Idle
//        mediaPlayer = new MediaPlayer();
//        try {
//            //初始化
//            mediaPlayer.setDataSource(path);
//
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//
//            // prepare 通过异步的方式装载媒体资源
//            mediaPlayer.prepareAsync();
//
//
//            //后台线程发送消息进行更新进度条
//            final int milliseconds = 100;
//            new Thread() {
//                @Override
//                public void run() {
//                    while (true) {
//                        try {
//                            sleep(milliseconds);
//                        } catch (InterruptedException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//
//                        mHandler.sendEmptyMessage(0);
//                    }
//                }
//            }.start();
//
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//
//    //測试播放音乐
//    void play() {
//        Log.d("PPPPPPPPPT", "播放");
//
//        mediaPlayer.start();
//    }
//
//    //暂停音乐
//    private void pause() {
//        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
//            mediaPlayer.pause();
//        }
//    }
//
//    //activity 退出时，停止播放音乐，释放资源
//    @Override
//    protected void onDestroy() {
//        // 在activity结束的时候回收资源
//        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
//            mediaPlayer.stop();
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
//        super.onDestroy();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.class.getModifiers(), menu);
//
////        #########################
//        return true;
//
//
//    }


}
