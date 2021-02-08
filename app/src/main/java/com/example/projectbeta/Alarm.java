package com.example.projectbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Alarm extends AppCompatActivity {

    private ImageButton imgbtn_on, imgbtn_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        imgbtn_off = findViewById(R.id.btn_off);
        imgbtn_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent( Alarm.this, MainActivity.class);
                startActivity(i);
            }
        });


        imgbtn_on = findViewById(R.id.btn_on);
    }
}