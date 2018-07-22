package com.example.coursehelper.ui.activity;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.coursehelper.R;
import com.example.coursehelper.bean.OptionalCourse;
import com.example.coursehelper.bean.PersonalCourse;
import com.example.coursehelper.bean.User;
import com.example.coursehelper.biz.CoursesBiz;
import com.example.coursehelper.fragment.CoursesFragment;
import com.example.coursehelper.fragment.MypageFragment;
import com.example.coursehelper.fragment.TableFragment;
import com.example.coursehelper.net.CommonCallBack;
import com.example.coursehelper.utils.T;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_viewCourses;
    private Button btn_myPage;
    private Button btn_courseTable;

    private CoursesFragment coursesFragment;  //全部通选课页面
    private TableFragment tableFragment; //课程表页面
    private MypageFragment mypageFragment; //我的信息页面

    public User user;
    public Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        this.getFragmentManager()
                .beginTransaction()
                .add(R.id.container_content,coursesFragment)
                .add(R.id.container_content,tableFragment)
                .add(R.id.container_content,mypageFragment)
                .hide(coursesFragment)
                .hide(mypageFragment)
                .show(tableFragment)
                .commit();

    }

    private void initView() {
        btn_viewCourses = findViewById(R.id.btn_viewCourses);
        btn_courseTable = findViewById(R.id.btn_courseTable);
        btn_myPage = findViewById(R.id.btn_myPage);

        coursesFragment = new CoursesFragment();
        tableFragment = new TableFragment();
        mypageFragment = new MypageFragment();

        btn_viewCourses.setOnClickListener(this);
        btn_courseTable.setOnClickListener(this);
        btn_myPage.setOnClickListener(this);

        bundle = getIntent().getExtras();
        user = (User) bundle.getSerializable("user");
        ((TableFragment)tableFragment).getUser(user);
        coursesFragment.getUser(user);
        mypageFragment.setUser(user);
    }

    /**
     * 功能菜单的监听
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_viewCourses:
                this.getFragmentManager()
                        .beginTransaction()
                        .show(coursesFragment)
                        .hide(tableFragment)
                        .hide(mypageFragment)
                        .commit();

                //coursesFragment.initEvent();
                coursesFragment.getUser(user);
                Log.i("MAIN","在MainActivity里获得了courseFragment里的user");

                btn_viewCourses.setBackgroundResource(R.drawable.menu_courses_iconclicked_selector);
                btn_courseTable.setBackgroundResource(R.drawable.menu_table_icon_selector);
                btn_myPage.setBackgroundResource(R.drawable.menu_mypage_icon_selector);
                break;
            case R.id.btn_courseTable:
                ((TableFragment)tableFragment).getUser(user);

                this.getFragmentManager()
                        .beginTransaction()
                        .hide(coursesFragment)
                        .show(tableFragment)
                        .hide(mypageFragment)
                        .commit();

                btn_viewCourses.setBackgroundResource(R.drawable.menu_courses_icon_selector);
                btn_courseTable.setBackgroundResource(R.drawable.menu_table_iconclicked_selector);
                btn_myPage.setBackgroundResource(R.drawable.menu_mypage_icon_selector);
                break;
            case R.id.btn_myPage:
                mypageFragment.getUser();
                this.getFragmentManager()
                        .beginTransaction()
                        .hide(coursesFragment)
                        .hide(tableFragment)
                        .show(mypageFragment)
                        .commit();


                btn_viewCourses.setBackgroundResource(R.drawable.menu_courses_icon_selector);
                btn_courseTable.setBackgroundResource(R.drawable.menu_table_icon_selector);
                btn_myPage.setBackgroundResource(R.drawable.menu_mypage_iconclicked_selector);
                break;
            default:
                break;
        }
    }
}
