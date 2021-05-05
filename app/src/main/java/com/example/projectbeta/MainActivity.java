package com.example.projectbeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageButton imgbtn_plus;
    TextView tv_time;
    TextClock tvClock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgbtn_plus = (ImageButton) findViewById(R.id.imgBtn_plus);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tvClock = findViewById(R.id.tvClock);

        imgbtn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this, Alarm.class);
                startActivity(i);
            }
        });

        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
        Intent i = getIntent();
        String timeHM12 = i.getStringExtra("time12");

        String timeHM24 = i.getStringExtra("time24");


        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (tvClock.getText().toString().equals(timeHM12) || tvClock.getText().toString().equals(timeHM24)){
                    r.play();
                }else {
                    r.stop();
                }
            }
        },0,1000);



        Intent intent = getIntent();
        String times12 =  intent.getStringExtra("times12");
        tv_time.setText(times12);



    }


}