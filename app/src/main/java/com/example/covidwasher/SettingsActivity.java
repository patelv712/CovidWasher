package com.example.covidwasher;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {

    Button notiE;
    Button notiD;
    NotiReci noti = new NotiReci();
    private String m_Text = "";
   // private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        notiD = (Button) findViewById(R.id.disableNoti);
        notiD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noti.cancelNoti();
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Turn off notifications in settings",
                        Toast.LENGTH_SHORT);
                toast.show();
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 500);
                return;
            }
        });

        notiE = (Button) findViewById(R.id.enableNoti);
        //timePicker = (TimePicker)findViewById(R.id.startTime);
        notiE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noti.startNoti();
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Notifications are enabled",
                        Toast.LENGTH_SHORT);
                toast.show();
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 500);



                /*AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle(timePicker.getMinute());
                builder.show();*/



                Calendar cal = Calendar.getInstance();

                //temporary time until updates and bugs fixed
                cal.set(Calendar.HOUR, cal.get(Calendar.HOUR));
                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
                cal.set(Calendar.SECOND, 0);

                Intent intent = new Intent(getApplicationContext(), NotiReci.class);
                intent.setAction("TIME TO WASH HANDS!");

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 7200000, pendingIntent);
                //alarm triggered even if in sleep
            }
        });
        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar) ;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings");
    }


}