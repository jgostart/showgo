package edu.swpu.gps;

//public class TestOneService {
//}

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Created by Kathy on 17-2-6.
 */

public class TestOneService extends Service {

    @Override
    public void onCreate() {
        Log.i("Kathy_LINE!!!!!!_START!!!!!!","onCreate - Thread ID = " + Thread.currentThread().getId());
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent, flags, startId);
        int b=0;

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
        super.onDestroy();
    }
}