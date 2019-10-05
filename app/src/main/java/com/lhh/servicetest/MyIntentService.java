package com.lhh.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;
//运行完成后自动停止，调用onDestroy
public class MyIntentService extends IntentService {
    public MyIntentService() {//调用父类有参构造
        super("MyIntentService");
    }
//处理一些具体逻辑，不用担心ANR（服务无响应）问题，它运行在子线程。
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
   //打印当前线程的id
        Log.d("MyIntentService", "Thread id is "+ Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService", "onDestroy: ");
    }
}
