package com.example.projectbeta;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextClock;
import android.widget.TimePicker;

import java.util.Calendar;

public class Alarm extends AppCompatActivity {

    private AppCompatButton imgbtn_on, imgbtn_off;
    //AlarmManager alarmManager;
    TimePicker timePicker;
    PendingIntent pendingIntent;
    TextClock textClock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        timePicker = (TimePicker) findViewById(R.id.timepecker);
        textClock = findViewById(R.id.txtClock);
        //alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

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
//                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
//                calendar.set(Calendar.MINUTE, timePicker.getMinute());

                int hour = timePicker.getHour();
                int min = timePicker.getMinute();

                String hour_string = String.valueOf(hour);
                String min_string = String.valueOf(min);




              pendingIntent = PendingIntent.getBroadcast(Alarm.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);


                //12-вой формат
                Intent i = new Intent(Alarm.this, MainActivity.class);
                String time12 = AlarmTime12();
                String times12 = "Будильник поставлен на: " + AlarmTime12();
                i.putExtra("times12", times12);
                i.putExtra("time12", time12);
                //24-вой формат
                String time24 = AlarmTime24();
                String times24 = "Будильник поставлен на: " + AlarmTime24();
                i.putExtra("times24", times24);
                i.putExtra("time24", time24);
                startActivity(i);

            }
        });



    }

    public String AlarmTime12(){
        Integer alarmHours12 = timePicker.getCurrentHour();
        Integer alarmMinutes12 = timePicker.getCurrentMinute();
        String stringAlMinutes12;

        if (alarmMinutes12 < 10){
            stringAlMinutes12 = "0";
            stringAlMinutes12 = stringAlMinutes12.concat(alarmMinutes12.toString());
        }else {
            stringAlMinutes12 = alarmMinutes12.toString();
        }

        String alarmTime12;

        if (alarmHours12 > 12){
            alarmHours12 = alarmHours12 - 12;
            alarmTime12 = alarmHours12.toString().concat(":").concat(stringAlMinutes12).concat(" PM");
        }else {
            alarmTime12 = alarmHours12.toString().concat(":").concat(stringAlMinutes12).concat(" AM");
        }

        return alarmTime12;
    }



    public String AlarmTime24(){
        Integer alarmHours24 = timePicker.getCurrentHour();
        Integer alarmMinutes24 = timePicker.getCurrentMinute();

        String stringAlMinutes12;

        if (alarmMinutes24 < 10){
            stringAlMinutes12 = "0";
            stringAlMinutes12 = stringAlMinutes12.concat(alarmMinutes24.toString());
        }else {
            stringAlMinutes12 = alarmMinutes24.toString();
        }

        String alarmTime24;

        alarmTime24 = alarmHours24.toString().concat(":").concat(stringAlMinutes12);


        return alarmTime24;
    }

}