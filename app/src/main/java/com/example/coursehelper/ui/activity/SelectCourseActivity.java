package com.example.coursehelper.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

import com.example.coursehelper.R;
import com.example.coursehelper.adapter.SelectCourseAdapter;
import com.example.coursehelper.bean.OptionalCourse;
import com.example.coursehelper.bean.User;
import com.example.coursehelper.biz.CoursesBiz;
import com.example.coursehelper.net.CommonCallBack;
import com.example.coursehelper.utils.T;

import java.util.ArrayList;
import java.util.List;

public class SelectCourseActivity extends BaseActivity implements SelectCourseAdapter.OnItemClickListener {
    android.support.v7.widget.Toolbar toolbar;
    RecyclerView recyclerView;
    public List<OptionalCourse> selectCourses = new ArrayList<>();
    private CoursesBiz mCoursesBiz = new CoursesBiz();
    private SelectCourseAdapter selectCourseAdapter;
    private User user;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.arg1) {
                case 0:
                    //TODO

                case 1:
                    //TODO
                    stopLoadingProgress();
                    method();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcourse);

        Bundle bundle = getIntent().getExtras();
        user = (User) bundle.getSerializable("user");
        Log.i("SELECT","在selectActivity里获得了user "+user.getUser_id());
        initView();
        initEvent();

        setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.container_select);
    }

    private void initEvent() {  //TODO
        startLoadingProgress();
        mCoursesBiz.selectCourse(user.getUser_id(), new CommonCallBack<List<OptionalCourse>>() {
            @Override
            public void onError(Exception e) {
                stopLoadingProgress();
                T.showToast(e.getMessage());
            }

            @Override
            public void onSuccess(List<OptionalCourse> response) {
                stopLoadingProgress();
                List<OptionalCourse> courses = new ArrayList<>();
                T.showToast("获取筛选课程信息成功");
                courses.clear();
                for(int i = 0; i < response.size()-1; ++i){
                    OptionalCourse p = response.get(i);
                    courses.add(p);
                    Log.i("SELECT",p.getTitle());
                }
                selectCourses = courses;
                Log.i("SELECT",selectCourses.size()+"");

            }
        });
        startLoadingProgress();
        new Thread(new Runnable(){
            public void run(){
                try {
                    Thread.sleep(900);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.arg1 = 1;
                handler.sendMessage(msg);
            }
        }).start();
    }

    private void method() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        selectCourseAdapter = new SelectCourseAdapter(SelectCourseActivity.this, selectCourses);
        Log.i("SELECT","哈哈哈"+selectCourses.size()+"");
        selectCourseAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(selectCourseAdapter);
    }

    @Override
    public void onItemClick(int position) {
        OptionalCourse optinalCourse = selectCourses.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("optinalCourse",optinalCourse);
        Intent intent = new Intent(this,OptinalCourseInfoActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCoursesBiz.onDestroy();
        stopLoadingProgress();
    }
}
