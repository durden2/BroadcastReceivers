package com.example.michal.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  static
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.my.static");
                sendBroadcast(intent);
            }
        });

        //  dynamic
        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.my.dynamic");
                sendBroadcast(intent);
            }
        });
    }

    private IntentFilter filter =
            new IntentFilter("android.my.dynamic");
        //  Register a filter

    private BroadcastReceiver broadcast = new BroadcastReceiver(){
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            Toast.makeText(arg0, "I received a dynamic message!", Toast.LENGTH_LONG).show();
            Vibrator vibrator = (Vibrator) arg0.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(broadcast, filter);
        //Register our receiver in code
    }

    @Override
    public void onPause() {
        unregisterReceiver(broadcast);
        // Unregister receiver
        super.onPause();
    }
}
