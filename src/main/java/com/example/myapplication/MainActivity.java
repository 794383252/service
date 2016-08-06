package com.example.myapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener {

    server.myBind bind;
    Button bind_server;
    Button unbind_server;


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("ln", "这是onServiceConnected");
            bind= (server.myBind) service;
            bind.getProgress();
            bind.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("ln", "这是onServiceDisconnected");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    public void init() {
        bind_server = (Button) findViewById(R.id.bind_server);
        unbind_server = (Button) findViewById(R.id.unbind_server);

        bind_server.setOnClickListener(this);
        unbind_server.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bind_server:
                Log.i("ln","执行点击事件");
                //调用onServiceConnected
                Intent bindintent = new Intent(MainActivity.this, server.class);
                bindService(bindintent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_server:
                unbindService(connection);
                break;
        }
    }
}
