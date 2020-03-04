package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask <Void, Void, String>{
    private WeakReference<TextView> mTextView;

    SimpleAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }

//    Executed in separate thread
    @Override
    protected String doInBackground(Void... voids) {
//        Generate random number
        Random r = new Random();
        int n = r.nextInt(11);

        int s = n * 200;

//        Sleep for the random amount of time
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

//    Executed in the main thread
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}
