package com.ottamotta.mottaback;

import java.util.concurrent.Callable;

/**
 * These objects when posted via EventBus will be catched in BackgroundExecutorService,
 * executed and returned to main UI thread
 *
 * @param <T> type of event to be returned; YOU MUST IMPLEMENT public void onEvent<T> in classes
 *           you want to receive this task results and register these classes to EventBus
 */
public abstract class MottaTask<T> implements Callable<T> {

    @Override
    public T call() throws Exception {
        return getTaskResult();
    }

    public abstract T getTaskResult();

}
