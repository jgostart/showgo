package edu.swpu.gps;

//package com.example.yiwennews;//注意这个是你的安卓工程，博主举得例子是yiwennews这个工程文件
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.os.Bundle;
public class onSTART extends AppCompatActivity { //dongActivity是博主刚刚新建的empty Activity名字，你如果不一样需要更改成你自己的新建的empty Activity名字
    private ImageView welcomeImg = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onstart);//这个是新建的empty Activity对应的xml部署文件名字
        ActionBar actionBar = getSupportActionBar();//消除APP该Activity界面标题栏
        if(actionBar!=null){ //消除APP该Activity界面标题栏
            actionBar.hide(); //消除APP该Activity界面标题栏
        }
//        消除APP该Activity界面标题栏
                welcomeImg = (ImageView) findViewById(R.id.wrap);
        AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);
        anima.setDuration(3000);// 设置简单动画的显示时间
        welcomeImg.startAnimation(anima);
        anima.setAnimationListener(new AnimationImpl());
    }
    private class AnimationImpl implements AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            welcomeImg.setBackgroundResource(R.mipmap.ic_launcher);//这个是你开发的APP开机的图片，yw是图片名字，对应下面要谈到的图片所在位置，这个是关键要注意，往往很多人出错就在这里
        }
        @Override
        public void onAnimationEnd(Animation animation) {
            skip(); // 动画结束后跳转到别的页面
        }
        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }
    private void skip() {
        startActivity(new Intent(this, MainActivity.class));//动画开屏后返回APP主界面
        finish(); //结束动画Activity进程
    }
}