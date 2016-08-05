package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2016/8/5.
 *
 * 由于service默认执行在主线程中，所以service中需要单开一个线程，不然很容易造成ANR，
 * 可以使用intentservice，集成了线程和自动关闭
 */
public class server extends Service{

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("ln","函数执行");
            }
        }).start();

        AlarmManager manager= (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour=30*100;
        long time= SystemClock.elapsedRealtime()+anHour;
        Intent i = new Intent(this,boastcastrecevice.class);
        PendingIntent pi=PendingIntent.getBroadcast(this,0,i,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,time,pi);
        return super.onStartCommand(intent, flags, startId);
    }

}
