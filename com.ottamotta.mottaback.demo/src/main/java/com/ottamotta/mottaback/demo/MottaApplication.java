package com.ottamotta.mottaback.demo;

import android.app.Application;
import android.content.Intent;

import com.ottamotta.mottaback.BackgroundExecutorService;

public class MottaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //It's important to start a service which receive events with MottaTask instances and executes them in background
        Intent startBackgroundService = new Intent(this, BackgroundExecutorService.class);
        startService(startBackgroundService);
    }
}
