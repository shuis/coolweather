package com.example.coolweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import com.example.coolweather.util.HttpCallbackListener;
import com.example.coolweather.util.HttpUtil;
import com.example.coolweather.util.Utility;

public class AutoUpdataService extends Service {


    @Override
    public IBinder onBind(Intent intent) {
     return null;
    }


    public void onStart(Intent intent, int flags,int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                updataWeather();
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour =8*60*60*1000;
        long triggerAtTime = SystemClock.elapsedRealtime()+anHour;
        Intent i =new Intent(this,AutoUpdataService.class);
        PendingIntent pi = PendingIntent.getBroadcast(this,0,i,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        super.onStartCommand(intent,flags,startId);


    }
public void updataWeather(){
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    String weatherCode = prefs.getString("weather_code","");
    String address = "http://www.weather.com.cn/data/cityinfo"+weatherCode+".html";
    HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
        @Override
        public void onFinish(String response) {
            Utility.handleWeatherResponse(AutoUpdataService.this,response);
        }


        @Override
        public void onError(Exception e) {
        e.printStackTrace();
        }
    });
}

}
