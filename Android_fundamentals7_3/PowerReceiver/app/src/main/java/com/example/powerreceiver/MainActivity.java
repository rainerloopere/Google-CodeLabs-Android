package com.example.powerreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CustomReceiver mReceiver = new CustomReceiver();
    //    Using package name in the custom broadcast action string
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//       Create a filter to specify which kinds of intents we receive
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        Log.d(this.getClass().getName(), "Intent Filter created");

//       Register the receiver - we are able to receive broadcasts as long as MainActivity is running
        this.registerReceiver(mReceiver, filter);
        Log.d(this.getClass().getName(), "Receiver registered");

//        Register a custom local receiver
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver,
                        new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }

    @Override
    protected void onDestroy() {
        //Unregister the receiver
        this.unregisterReceiver(mReceiver);

        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(mReceiver);

        super.onDestroy();


    }

    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);

        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }
}
