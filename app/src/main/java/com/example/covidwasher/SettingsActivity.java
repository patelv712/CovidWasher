package com.example.covidwasher;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {

    Button noti;
    TextView enable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        enable = (TextView) findViewById(R.id.notiEnable);


        noti = (Button) findViewById(R.id.enableNoti);
        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enable.setText("Notifications enabled!");
                enable.setTextSize(10);
                enable.setVisibility(View.VISIBLE);

                Calendar cal = Calendar.getInstance();

                cal.set(Calendar.HOUR_OF_DAY, 17);
                cal.set(Calendar.MINUTE, 13);
                cal.set(Calendar.SECOND, 0);

                Intent intent = new Intent(getApplicationContext(), NotiReci.class);
                intent.setAction("TIME TO WASH HANDS!");

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, pendingIntent);
                //alarm triggered even if in sleep
            }
        });
        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar) ;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings");
    }
}