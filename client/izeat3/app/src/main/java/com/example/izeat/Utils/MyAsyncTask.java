package com.example.izeat.Utils;

import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;

public class MyAsyncTask extends AsyncTask<Void, Void, Long> {

    // 1 - Implement listeners methods (Callback)
    public interface Listeners {
        void onPreExecute();
        void doInBackground();
        void onPostExecute(Long success);
    }

    // 2 - Declare callback
    private final WeakReference<Listeners> callback;

    // 3 - Constructor
    public MyAsyncTask(Listeners callback){
        this.callback = new WeakReference<>(callback);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.callback.get().onPreExecute(); // 4 - Call the related callback method
        Log.e("TAG", "AsyncTask is started.");
    }

    @Override
    protected void onPostExecute(Long success) {
        super.onPostExecute(success);
        Log.e("TAG", "AsyncTask is finished.");
        this.callback.get().onPostExecute(success); // 4 - Call the related callback method
    }

    @Override
    protected Long doInBackground(Void... voids) {
        Log.e("TAG", "AsyncTask doing some big work...");
        this.callback.get().doInBackground(); // 4 - Call the related callback method
        return System.currentTimeMillis();
    }
}