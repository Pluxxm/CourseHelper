package com.example.coursehelper;

import android.app.Application;

import com.example.coursehelper.utils.T;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class ResApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        T.init(this);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
