package com.example.coursehelper.biz;

import android.util.Log;

import com.example.coursehelper.bean.PersonalCourse;
import com.example.coursehelper.bean.User;
import com.example.coursehelper.config.Config;
import com.example.coursehelper.net.CommonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

public class TableBiz {
    public void loadCourseTable(String user_id, String course_name, String info,
                                String teacher, String start_week, String end_week,
                                String course_day, String start_time, String end_time,
                                CommonCallBack<List<PersonalCourse>> commonCallBack){
        OkHttpUtils
                .post()
                .tag(this)
                .url(Config.baseUrl+"addcourse")
                .addParams("user_id", user_id)
                .addParams("course_name",course_name)
                .addParams("info",info)
                .addParams("teacher",teacher)
                .addParams("start_week",start_week)
                .addParams("end_week",end_week)
                .addParams("course_day",course_day)
                .addParams("start_time",start_time)
                .addParams("end_time",end_time)
                .build()
                .execute(commonCallBack);
        //TODO 返回一个personalCourseList对象
    }
    public void listCourseTable(String user_id, CommonCallBack<List<PersonalCourse>>commonCallBack){
        OkHttpUtils
                .get()
                .tag(this)
                .url(Config.baseUrl+"mycourse?user_id="+user_id)
                .build()
                .execute(commonCallBack);
        Log.i("TABLEBIZ",user_id);
    }
    public void onDestroy(){
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
