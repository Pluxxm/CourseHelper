package com.example.coursehelper.ui.activity;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

class BaseActivity extends AppCompatActivity{
    private ProgressDialog mProgressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(0xff000000);
        }

        mProgressBar = new ProgressDialog (this);
        mProgressBar.setMessage("加载中...");
    }

    protected void stopLoadingProgress() {
        if(mProgressBar != null && mProgressBar.isShowing()){
            mProgressBar.dismiss();
        }
    }
    protected void startLoadingProgress() {
        mProgressBar.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLoadingProgress();
        mProgressBar = null;
    }
}
