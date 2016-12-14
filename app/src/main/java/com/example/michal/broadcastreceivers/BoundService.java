package com.example.michal.broadcastreceivers;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by michal on 14.12.2016.
 */

public class BoundService extends Service {
        private Handler handler = new Handler();

        private final IBinder mBinder = new LocalBinder();

        public class LocalBinder extends Binder {
            BoundService getService() {
                return BoundService.this;
            }
        }

        @Override
        public IBinder onBind(Intent intent) {
            return mBinder;
        }
        public void generateToast() {
            handler.post(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(), "Welcome in BoundService", Toast.LENGTH_SHORT).show();
                }
            });
        }
}
