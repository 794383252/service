package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2016/8/5.
 */
public class boastcastrecevice extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i=new Intent(context,server.class);
        context.startService(i);
    }
}
