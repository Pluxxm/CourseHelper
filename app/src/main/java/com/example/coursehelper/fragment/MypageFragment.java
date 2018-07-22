package com.example.coursehelper.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.coursehelper.R;
import com.example.coursehelper.bean.User;
import com.example.coursehelper.ui.activity.LoginActivity;

import java.util.List;

public class MypageFragment extends Fragment {
    private TextView tv_mypage_id;
    private TextView tv_mypage_name;
    private Button btn_logout;
    private User user;
    private String user_id = " ";
    private String nickName = " ";

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

//        Bundle bundle = getArguments();
//        user = (User) bundle.getSerializable("user");
        tv_mypage_id = view.findViewById(R.id.mypage_id);
        tv_mypage_name = view.findViewById(R.id.mypage_name);
        btn_logout = view.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void getUser(){
        Log.i("MYPAGEFRAGMENT","mypageFragment拿到了user,用户名为："+user.getNickname());
        updateView(user);
    }

    private void updateView(User user) {
        Log.i("MYPAGEFRAGMENT","updateView拿到了user,用户名为："+user.getUser_id());
        user_id = user.getUser_id();
        nickName = user.getNickname();

        tv_mypage_id.setText(user_id);
        tv_mypage_name.setText(nickName);
    }
}
