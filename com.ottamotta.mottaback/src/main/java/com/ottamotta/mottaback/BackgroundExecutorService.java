package com.ottamotta.mottaback;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import de.greenrobot.event.EventBus;

/**
 * This service should be started in your app to catch events with MottaTask<T> inheritors instances
 */
public class BackgroundExecutorService extends Service {

    private EventBus bus = EventBus.getDefault();
    private Handler mHandler = new Handler();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (!bus.isRegistered(this)) bus.register(this);
    }

    public void onEvent(MottaTask task) {
        TaskManager.getInstance().execute(task, mHandler);

    }

}
