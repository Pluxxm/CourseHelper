package com.example.coursehelper.biz;

import com.example.coursehelper.bean.OptionalCourse;
import com.example.coursehelper.bean.User;
import com.example.coursehelper.config.Config;
import com.example.coursehelper.net.CommonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

public class CoursesBiz {
    public void loadCourse(CommonCallBack<List<OptionalCourse>> commonCallBack){
        OkHttpUtils
                .get()
                .tag(this)  //用来取消请求
                .url(Config.baseUrl+"opcourse?user_id=-1")
                .build()
                .execute(commonCallBack);
        //TODO 返回一个optinalCourseList对象
    }
    public void selectCourse(String user_id, CommonCallBack<List<OptionalCourse>> commonCallBack){
        OkHttpUtils
                .get()
                .tag(this)
                .url(Config.baseUrl+"opcourse?user_id="+user_id)
                .build()
                .execute(commonCallBack);
    }
    public void onDestroy(){
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
