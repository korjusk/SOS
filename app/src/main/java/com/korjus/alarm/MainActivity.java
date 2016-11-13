package com.korjus.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AlarmManager mgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        Intent i = new Intent(this, CallActivity.class);
        i.setAction(Intent.ACTION_MAIN);
        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        //PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);

        final EditText editText = (EditText) findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int delay = Integer.decode(String.valueOf(editText.getText())) * 1000 * 60; // in minutes
                Log.d("u8i", "onClick: " + String.valueOf(delay));
                mgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + delay, pendingIntent);
                finish();
            }
        });
    }
}
