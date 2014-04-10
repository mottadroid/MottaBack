package com.ottamotta.mottaback;

import android.os.Handler;
import de.greenrobot.event.EventBus;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Component running into BackroundExecutorService which actually hadnle background execution
 */
public class TaskManager {

    private static TaskManager instance;

    private EventBus bus = EventBus.getDefault();
    private ExecutorService executorService =  Executors.newFixedThreadPool(4);

    private TaskManager(){}

    public static synchronized TaskManager getInstance(){
        if (instance==null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public void execute(MottaTask task, Handler handler) {
        try {
            Future f = executorService.submit(task);
            final Object result = f.get();
            if (result != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        bus.post(result);
                    }
                });
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
