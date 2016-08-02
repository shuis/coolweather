package com.example.coolweather.util;

/**
 * Created by Administrator on 2016/8/2.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
