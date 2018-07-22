package com.example.coursehelper.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursehelper.R;
import com.example.coursehelper.UserInfoHolder;
import com.example.coursehelper.bean.PersonalCourse;
import com.example.coursehelper.bean.User;
import com.example.coursehelper.biz.TableBiz;
import com.example.coursehelper.net.CommonCallBack;
import com.example.coursehelper.ui.activity.AddCourseActivity;
import com.example.coursehelper.ui.activity.MainActivity;
import com.example.coursehelper.utils.T;

import java.util.ArrayList;
import java.util.List;

public class TableFragment extends Fragment {
    //星期几
    private RelativeLayout day;
    private Button btn_addCourse;

    int currentCoursesNumber = 0;
    int maxCoursesNumber = 0;

    private TableBiz mTableBiz = new TableBiz();
    private User user;

    ArrayList<PersonalCourse> coursesList = new ArrayList<>();

    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table, container, false);
        btn_addCourse = view.findViewById(R.id.btn_addCourse);
        btn_addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("user",user);
                Intent intent = new Intent(getActivity(), AddCourseActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, Activity.RESULT_FIRST_USER);
            }
        });
        loadData();
        return view;
    }

    public void getUser(User user){
        this.user = user;
        Log.i("TABLEFRAGMENT","tableFragment拿到了user,用户名为："+user.getNickname());
    }

    private void loadData() {
        //TODO 请求后台从数据库中加载此用户的课程表
        //TODO 需要给后台提供用户账户信息
        String user_id = user.getUser_id();
        mTableBiz.listCourseTable(user_id, new CommonCallBack<List<PersonalCourse>>() {
            @Override
            public void onError(Exception e) {
                T.showToast(e.getMessage());
            }

            @Override
            public void onSuccess(List<PersonalCourse> response) {
                int count = 0;
                coursesList.clear();
                T.showToast("获取课程表信息成功");
                for(PersonalCourse p: response){
                    coursesList.add(new PersonalCourse(p));
                    createLeftView(coursesList.get(count));
                    createCourseView(coursesList.get(count));
                    count++;
                    Log.i("TABLE","有 "+ count + "节课");
                }
            }
        });
        //TODO 传递来的数据加载视图
    }
    //保存数据到数据库
    private void saveData(PersonalCourse course) {
        Log.i("SAVE",course.getCourse_day());
        mTableBiz.loadCourseTable(course.getUser_id(), course.getCourse_name(), course.getInfo(), course.getTeacher(),
                course.getStart_week(), course.getEnd_week(), course.getCourse_day(), course.getStart_time(), course.getEnd_time(),
                new CommonCallBack<List<PersonalCourse>>() {
                    @Override
                    public void onError(Exception e) {
                        T.showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(List<PersonalCourse> response) {
                        Log.i("POST","成功POST");
                    }
                });
        //TODO 将数据打包发给后台存储
    }
    //创建课程节数视图
    private void createLeftView(PersonalCourse course) {
        String strlen = course.getEnd_time();
        int len = Integer.valueOf(strlen).intValue();
        if (len > maxCoursesNumber) {
            for (int i = 0; i < len-maxCoursesNumber; i++) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.left_view, null);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(110,180);
                view.setLayoutParams(params);

                TextView text = view.findViewById(R.id.class_number_text);
                text.setText(String.valueOf(++currentCoursesNumber));

                LinearLayout leftViewLayout = getActivity().findViewById(R.id.left_view_layout);
                leftViewLayout.addView(view);
            }
        }
        maxCoursesNumber = len;
    }
    //创建课程视图
    private void createCourseView(final PersonalCourse course) {
        int height = 180;
        String strgetDay = course.getCourse_day();
        int getDay = 1;
        switch (strgetDay) {
            case "周一":
                getDay = 1;
                break;
            case "周二":
                getDay = 2;
                break;
            case "周三":
                getDay = 3;
                break;
            case "周四":
                getDay = 4;
                break;
            case "周五":
                getDay = 5;
                break;
            case "周六":
                getDay = 6;
                break;
            case "周日":
                getDay = 7;
                break;
            default:
                getDay = 8;
                break;
        }
        if ((getDay < 1 || getDay > 7) || Integer.parseInt(course.getStart_time()) > Integer.parseInt(course.getEnd_time())){
            Log.i("TABLE", "星期几没写对,或课程结束时间比开始时间还早~~");
        Toast.makeText(getActivity(), "星期几没写对,或课程结束时间比开始时间还早~~", Toast.LENGTH_LONG).show();
    }
        else {
            switch (getDay) {
                case 1: day = getActivity().findViewById(R.id.monday); break;
                case 2: day = getActivity().findViewById(R.id.tuesday); break;
                case 3: day = getActivity().findViewById(R.id.wednesday); break;
                case 4: day = getActivity().findViewById(R.id.thursday); break;
                case 5: day = getActivity().findViewById(R.id.friday); break;
                case 6: day = getActivity().findViewById(R.id.saturday); break;
                case 7: day = getActivity().findViewById(R.id.weekday); break;
            }
            final View v = LayoutInflater.from(getActivity()).inflate(R.layout.course_card, null); //加载单个课程布局
            v.setY(height * (Integer.parseInt(course.getStart_time())-1)); //设置开始高度,即第几节课开始
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT,(Integer.parseInt(course.getEnd_time())-Integer.parseInt(course.getStart_time())+1)*height - 8); //设置布局高度,即跨多少节课
            v.setLayoutParams(params);
            TextView text = v.findViewById(R.id.text_view);
            text.setText(course.getCourse_name() + "\n" +
                    course.getTeacher() + "\n" + course.getStart_week()+"-"
                    +course.getEnd_week()+"周"); //显示课程名
            day.addView(v);
            //长按删除课程
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    v.setVisibility(View.GONE);//先隐藏
                    day.removeView(v);//再移除课程视图
                    //TODO 向后台发消息删除记录
//                    SQLiteDatabase sqLiteDatabase =  databaseHelper.getWritableDatabase();
//                    sqLiteDatabase.execSQL("delete from courses where course_name = ?", new String[] {course.getCourseName()});
                    return true;
                }
            });
        }
    }

    //TODO 添加课程页面传递来的消息是data
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == Activity.RESULT_FIRST_USER && resultCode == 0 && data != null) {
            PersonalCourse course = (PersonalCourse) data.getSerializableExtra("personalcourse");
            Log.i("INTENT","进入了onActivityResult中的if");
            //创建课程表左边视图(节数)
            if(course == null) {
                Log.i("INTENT","未正常执行onActivityResult,course为空对象");
                return;
            }
            createLeftView(course);
            //创建课程表视图
            createCourseView(course);
            //存储数据到数据库
            saveData(course);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTableBiz.onDestroy();
    }
}
