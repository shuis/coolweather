package com.example.coolweather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.coolweather.service.AutoUpdataService;

/**
 * Created by Administrator on 2016/8/3.
 */
public class AutoUpdataReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AutoUpdataService.class);
        context.startService(i);
    }
}
