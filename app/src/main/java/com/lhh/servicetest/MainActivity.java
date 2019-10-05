package com.lhh.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private MyService.DownloadBinder downloadBinder;
private ServiceConnection connection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder service) {
        downloadBinder = (MyService.DownloadBinder) service;//向下转型得到DownloadBinder,即得到Binder和DownloadBinder的public方法。
        downloadBinder.startDownload();
        downloadBinder.getProgress();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startService = findViewById(R.id.start_service);
        Button stopService = findViewById(R.id.stop_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        Button bindService = findViewById(R.id.bind_service);
        Button unBindService = findViewById(R.id.unbind_service);
        Button startIntentService = findViewById(R.id.start_intent_service);
        bindService.setOnClickListener(this);
        unBindService.setOnClickListener(this);
        startIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_service :
                Intent startIntent = new Intent(this,MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service :
                Intent stopIntent = new Intent(this,MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service :
                Intent bindIntent = new Intent(this,MyService.class);
                //绑定服务，BIND_AUTO_CREATE表示在活动和服务进行绑定后自动创建服务，
                // 使得MysService的onCreate执行，但onStartCommand（）不会执行。
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service :
             unbindService(connection);//解绑服务
                break;
            case R.id.start_intent_service :
                Log.d("MainActivity", "Thread id is "+Thread.currentThread().getId());
                Intent intentService = new Intent(this,MyIntentService.class);
                startService(intentService);
                break;
                default:
                    break;
        }
    }
}
