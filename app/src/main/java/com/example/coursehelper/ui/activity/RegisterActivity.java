package com.example.coursehelper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.coursehelper.R;
import com.example.coursehelper.UserInfoHolder;
import com.example.coursehelper.bean.User;
import com.example.coursehelper.biz.UserBiz;
import com.example.coursehelper.net.CommonCallBack;
import com.example.coursehelper.utils.T;

import java.util.List;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private Button btn_register;
    private Button btn_registerToLogin;
    private EditText et_user_register_id;
    private EditText et_user_register_name;
    private EditText et_user_register_pwd;

    private UserBiz mUserBiz = new UserBiz();
    //public static String user_id;

    //将所获得数据存入数据库
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        btn_register = findViewById(R.id.btn_register);
        btn_registerToLogin = findViewById(R.id.btn_registerToLogin);
        et_user_register_id = findViewById(R.id.et_user_register_id);
        et_user_register_name = findViewById(R.id.et_user_register_name);
        et_user_register_pwd = findViewById(R.id.et_user_register_pwd);

        btn_register.setOnClickListener(this);
        btn_registerToLogin.setOnClickListener(this);
    }

    /**
     * 按钮监听
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                //注册按钮，注册完成进入主界面，并将用户信息存入数据库
                final String userid = et_user_register_id.getText().toString();
                String userpwd = et_user_register_pwd.getText().toString();
                final String username;
                if(et_user_register_name.getText().toString() == null){
                        username = "";
                }else{
                    username = et_user_register_name.getText().toString();
                }

                if(TextUtils.isEmpty(userid) || TextUtils.isEmpty(userpwd)){
                    T.showToast("学号或者密码不能为空");
                    return;
                }
                startLoadingProgress();
                mUserBiz.register(userid, username, userpwd, new CommonCallBack<List<User>>() {
                    @Override
                    public void onError(Exception e) {
                        stopLoadingProgress();
                        T.showToast(e.getMessage());
                        Log.i("TOAST",e.getMessage());
                    }

                    @Override
                    public void onSuccess(List<User> response) {
                        stopLoadingProgress();
                        T.showToast("注册成功，用户名为" + response.get(0).getNickname());
                        //Log.i("TOAST",response.toString());
                        //Log.i("TOAST",response.getUser_name());
                        LoginActivity.launch(RegisterActivity.this, String.valueOf(response.get(0).getUser_id()),response.get(0).getPassword());
                        finish();
                        //TODO 保存用户注册信息
                        //user_id = userid;
                    }
                });
                //TODO 注册成功？
//                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
//                startActivity(intent);
                break;
            case R.id.btn_registerToLogin:
                //用户返回到登录页面
                Intent intent1 = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
