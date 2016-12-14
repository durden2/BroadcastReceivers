package com.example.michal.broadcastreceivers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                // send intend as a broadcast
            }
        });

        //  dynamic
        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.my.dynamic");
                // send intend as a broadcast
            }
        });
    }

        //  Register a IntentFilter for 'android.my.dynamic'


        // Register a BroadcastReceiver doind the following
        //    Toast.makeText(arg0, "I received a dynamic message!", Toast.LENGTH_LONG).show();
        //    Vibrator vibrator = (Vibrator) arg0.getSystemService(Context.VIBRATOR_SERVICE);
        //    vibrator.vibrate(1000);


    @Override
    public void onResume() {
        super.onResume();
        //Register our receiver in code
    }

    @Override
    public void onPause() {
        // Unregister receiver
        super.onPause();
    }
}
