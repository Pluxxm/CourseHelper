package com.example.coursehelper;

import android.text.TextUtils;

import com.example.coursehelper.bean.User;
import com.example.coursehelper.ui.activity.RegisterActivity;
import com.example.coursehelper.utils.SPUtils;

public class UserInfoHolder {

    private static UserInfoHolder mInstance = new UserInfoHolder();
    private User mUser;

    private static final String KEY_USEID = "key_userid";

    public static UserInfoHolder getInstance(){
        return mInstance;
    }
    public void setUser(User user){
        mUser = user;
        if(user != null){
            //TODO 向服务器请求user对象
            //SPUtils.getInstance().put(KEY_USEID,user.getId());
        }
    }

    public User getUser(){
        User u = mUser;
        if(u == null){
//            String userid = (String) SPUtils.getInstance().get(KEY_USEID,"");
//            if(!TextUtils.isEmpty(userid)){
//                u = new User();
//                u.setId(Integer.parseInt(userid));
            }
            mUser = u;
            return u;
    }
}
