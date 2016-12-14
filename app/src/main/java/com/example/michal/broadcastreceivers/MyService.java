package com.example.michal.broadcastreceivers;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by michal on 14.12.2016.
 */

public class MyService extends IntentService {
        private int i;
        private Handler handler = new Handler();
        public MyService() {
            super("MyService");
        }

        @Override
        protected void onHandleIntent(Intent arg0) {

            while(i<10){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Started Service", Toast.LENGTH_SHORT).show();
                    }
                });
                i++;
            }
        }

}
