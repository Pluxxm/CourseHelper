package com.example.coursehelper.ui.activity;

import android.content.Context;
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

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private Button btn_login;
    private Button btn_loginToRegister;
    private EditText et_user_pwd;
    private EditText et_user_id;

    private UserBiz mUserBiz = new UserBiz();

    private static final String KEY_USERID = "key_userid";
    private static final String KEY_USERPWD = "key_userpwd";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        initIntent(getIntent());
    }

    private void initView() {
        btn_login = findViewById(R.id.btn_login);
        btn_loginToRegister = findViewById(R.id.btn_loginToRegister);
        et_user_id = findViewById(R.id.et_user_id);
        et_user_pwd = findViewById(R.id.et_user_pwd);

        btn_login.setOnClickListener(this);
        btn_loginToRegister.setOnClickListener(this);
    }

    /**
     * 登录按钮的监听
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                //首先进行账号密码正确性检测
                //等待查询接口
                String userid = et_user_id.getText().toString();
                String userpwd = et_user_pwd.getText().toString();

                if(TextUtils.isEmpty(userid) || TextUtils.isEmpty(userpwd)){
                    T.showToast("学号或者密码不能为空");
                    return;
                }
                startLoadingProgress();
//                mUserBiz.login(userid, userpwd, new CommonCallBack<List<User>>() {
//                    @Override
//                    public void onError(Exception e) {
//                        stopLoadingProgress();
//                        T.showToast(e.getMessage());
//                    }
//
//                    @Override
//                    public void onSuccess(List<User> response) {
//                        stopLoadingProgress();
//                        T.showToast("登陆成功");
//                        //TODO 保存用户登录信息 获取用户信息 创建UserInfoHolder对象
//                        //UserInfoHolder.getInstance().setUser(response);
//                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
////                        Bundle bundle = new Bundle();
////                        bundle.putSerializable("user",response);
//                        if(response.get(0).getNickname()== null) {
//                            Log.i("USER", "response对象为空");
//                        }else{
//                            Log.i("USER", response.get(0).getNickname());
//                        }
//                        //intent.putExtras(bundle);
//                        startActivity(intent);
//                    }
//                });
               mUserBiz.login(userid, userpwd, new CommonCallBack<List<User>>() {
                   @Override
                   public void onError(Exception e) {
                       stopLoadingProgress();
                       T.showToast(e.getMessage());
                   }

                   @Override
                   public void onSuccess(List<User> response) {
                       stopLoadingProgress();
                        T.showToast("登陆成功");
                        //TODO 保存用户登录信息 获取用户信息 创建UserInfoHolder对象
                        //UserInfoHolder.getInstance().setUser(response);
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("user",response.get(0));
                        if(response.get(0).getNickname()== null) {
                            Log.i("USER", "response对象为空");
                        }else{
                            Log.i("USER", response.get(0).getNickname());
                        }
                        intent.putExtras(bundle);
                        startActivity(intent);
                   }
               });
                //TODO 登陆成功？
//                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                startActivity(intent);
                break;
            case R.id.btn_loginToRegister:
                Intent intent1 = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        initIntent(intent);
    }

    private void initIntent(Intent intent) {
        if(intent == null){
            return;
        }
        String userid = intent.getStringExtra(KEY_USERID);
        String userpwd = intent.getStringExtra(KEY_USERPWD);

        if(TextUtils.isEmpty(userid) || TextUtils.isEmpty(userpwd)){
            return;
        }
        Log.i("USER",userid+" "+userpwd);

        et_user_id.setText(userid);
        et_user_pwd.setText(userpwd);
    }

    public static void launch(Context context , String userid, String userpwd){
        Intent intent = new Intent(context,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(KEY_USERID,userid);
        intent.putExtra(KEY_USERPWD,userpwd);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUserBiz.onDestroy();
    }
}
