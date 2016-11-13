package com.korjus.alarm;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class CallActivity extends AppCompatActivity {
    Ringtone r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_call);

        WakeLocker.acquire(this);
        getSupportActionBar().hide();

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();


        ImageView imageView = (ImageView) findViewById(R.id.screenPix);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(r.isPlaying()) r.stop();
                //android.os.Process.killProcess(android.os.Process.myPid());
                finish();
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        if(r.isPlaying()) r.stop();
        WakeLocker.release();
        finish();
    }
}
