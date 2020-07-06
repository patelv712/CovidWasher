package com.example.covidwasher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.covidwasher.util.PrefUtil;

public class TimerExpired extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        PrefUtil.setTimerState(MainActivity.TimerState.Stopped, context);
        PrefUtil.setAlarmSetTime(0L, context);
    }
}