package com.example.coursehelper.biz;

import android.app.DownloadManager;

import com.example.coursehelper.bean.User;
import com.example.coursehelper.config.Config;
import com.example.coursehelper.net.CommonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UserBiz {
    public void login(String user_id, String password
            , CommonCallBack<List<User>>commonCallBack){
        OkHttpUtils
                .post()
                .url(Config.baseUrl+"login")
                .tag(this)
                .addParams("user_id",user_id)
                .addParams("password",password)
                .build()
                .execute(commonCallBack);
    }
    public void register(String user_id, String nickname, String password
            , CommonCallBack<List<User>>commonCallBack){
        OkHttpUtils
                .post()
                .url(Config.baseUrl+"register")
                .tag(this)
                .addParams("user_id",user_id)
                .addParams("nickname",nickname)
                .addParams("password",password)
                .build()
                .execute(commonCallBack);
    }
//    public void returnInfo(String user_id){
//
//    }

    public void onDestroy(){
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
