package com.example.projectbeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private ImageButton imgbtn_plus;
    TextView tv_time;
    TextClock tvClock;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;


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

        int hours12 = i.getIntExtra( "hours12", 0);
        String hours24 = i.getStringExtra("hours24");
        int minAll = i.getIntExtra("minAll", 0);
        minAll = minAll;

        String strH12 = String.valueOf(hours12);
        String strMinAll = String.valueOf(minAll);

        if (hours12 > 12){
            strH12 = String.valueOf(hours12 - 12);
        }
        if (minAll < 10){
            strMinAll = "0" + String.valueOf(minAll);
        }






        final Calendar now = Calendar.getInstance();

//        if (calendar.getTimeInMillis() < now.getTimeInMillis()){
//            calendar.add(Calendar.DAY_OF_MONTH, 1);
//        }
//
//        if (calendar.getTimeInMillis() == now.getTimeInMillis()) {
//            Timer t = new Timer();
//            t.scheduleAtFixedRate(new TimerTask() {
//                @Override
//                public void run() {
//                    if (tvClock.getText().toString().equals(timeHM12) || tvClock.getText().toString().equals(timeHM24)){
//                        r.play();
//                    }
//                }
//            },0,1000);
//
//
//        }


//        Timer t = new Timer();
//        t.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                if (tvClock.getText().toString().equals(timeHM12) || tvClock.getText().toString().equals(timeHM24)){
//                    r.play();
//                }else {
//                    r.stop();
//                }
//            }
//        },0,1000);



        Intent intent = getIntent();
        String times12 =  intent.getStringExtra("times12");
        tv_time.setText(times12);



    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        startAlarm(calendar);
    }

    private void startAlarm(Calendar calendar){
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        Intent my_intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, my_intent, 0);
        if (calendar.before(Calendar.getInstance())){
            calendar.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
}