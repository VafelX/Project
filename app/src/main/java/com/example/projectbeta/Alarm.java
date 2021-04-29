package com.example.projectbeta;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class Alarm extends AppCompatActivity {

    private AppCompatButton imgbtn_on, imgbtn_off;
    MediaPlayer mediaPlayer;
    AlarmManager alarmManager;
    TimePicker timePicker;
    TextView tv_update;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        timePicker = (TimePicker) findViewById(R.id.timepecker);
        tv_update = findViewById(R.id.tv_update);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        imgbtn_off = findViewById(R.id.btn_off);
        imgbtn_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Alarm.this, MainActivity.class);
                startActivity(i);
            }
        });

        Intent my_intent = new Intent(getApplicationContext(), AlarmReceiver.class);

        final Calendar calendar = Calendar.getInstance();
        imgbtn_on = findViewById(R.id.btn_on);
        imgbtn_on.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                calendar.set(Calendar.MINUTE, timePicker.getMinute());

                int hour = timePicker.getHour();
                int min = timePicker.getMinute();

                String hour_string = String.valueOf(hour);
                String min_string = String.valueOf(min);

                if (hour > 12) {
                    hour_string = String.valueOf(hour - 12);
                }
                if (min < 10) {
                    min_string = "0" + String.valueOf(min);
                }

                setTimeText("Будильник поставлен на " + hour_string + ":" + min_string);

                pendingIntent = PendingIntent.getBroadcast(Alarm.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

            }
        });

    }

    private void setTimeText(String s) {
        tv_update.setText(s);

    }

    public void play(View v) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.daydream);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }

        mediaPlayer.start();
    }

    public void stop(View v) {
        stopPlayer();
    }

    public void stopPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}