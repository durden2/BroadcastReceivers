package com.example.michal.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by michal on 14.12.2016.
 */

public class FirstReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
                Toast.makeText(arg0, "I received a static message!", Toast.LENGTH_LONG).show();
        }

}
