MottaBack
=========

Simple Android solution to pass background tasks into service, executing them and obtaining result in main UI thread.

Usage:

Step 1. Start a service catching background tasks to execute. Start it before you want to run first task, for example, in Application class:

@Override
public void onCreate() {
  super.onCreate();
  Intent startBackgroundService = new Intent(this, BackgroundExecutorService.class);
  startService(startBackgroundService);
}

Step 2. Extend MottaTask<T> with your own task. <T> - it's a class of event this task will return. You have to implement only one method - getTaskResult<T>:

Step 3. Run task anywhere:

EventBus bus = EventBus.getDefault();
YourTask task = new YourTask();
bus.post(task); //that's all - task will be run in BacgkroundExecutorService

Step 4. Receive result. To do this, register to EventBus and implement method public void onEvent(<T> event):


So, one task running and result receiving absolutely inderpendent. And no boilerplate code needed to make and register listeners etc.

Plese, take a look at com.ottamotta.mottaback.demo module. This simple app use Android GeoService, which must be runned into background, to get addresses matching your input.

You'll find a MottaTask implementation (GetAddressesTask), task running and result handling in AddressFragment. 



