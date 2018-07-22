package com.example.coursehelper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coursehelper.R;
import com.example.coursehelper.bean.PersonalCourse;
import com.example.coursehelper.bean.User;
import com.example.coursehelper.utils.T;

public class AddCourseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcourse);
        setFinishOnTouchOutside(false);

        Bundle bundle = getIntent().getExtras();
        final User user = (User) bundle.getSerializable("user");
        Log.i("ADD",user.getUser_id());

        final EditText inputCourseName = (EditText) findViewById(R.id.course_name);
        final EditText inputTeacher = (EditText) findViewById(R.id.teacher_name);
        final EditText inputInfo = (EditText) findViewById(R.id.class_room);
        final EditText inputDay = (EditText) findViewById(R.id.week);
        final EditText inputStart = (EditText) findViewById(R.id.classes_begin);
        final EditText inputEnd = (EditText) findViewById(R.id.classes_ends);
        final EditText inputStartWeek = (EditText) findViewById(R.id.classes_beginweek);
        final EditText inputEndWeek = (EditText) findViewById(R.id.classes_endweek);

        Button okButton = (Button) findViewById(R.id.button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course_name = inputCourseName.getText().toString();
                String teacher = inputTeacher.getText().toString();
                String info = inputInfo.getText().toString();
                String course_day = inputDay.getText().toString();
                String start_time = inputStart.getText().toString();
                String end_time = inputEnd.getText().toString();
                String start_week = inputStartWeek.getText().toString();
                String end_week = inputEndWeek.getText().toString();
                String user_id = user.getUser_id();

                if (course_name.equals("") || course_day.equals("") || start_time.equals("") || end_time.equals("")) {
                    Toast.makeText(AddCourseActivity.this, "基本课程信息未填写", Toast.LENGTH_SHORT).show();
                }
                else {
                    PersonalCourse course = new PersonalCourse(user_id, course_name, teacher, start_week,
                            end_week, course_day, start_time, end_time, info);
                    Log.i("ADD",course.getCourse_day());
                    Intent intent = new Intent(AddCourseActivity.this, MainActivity.class);
                    intent.putExtra("personalcourse",course);
                    Log.i("INTENT","intent传过去了");

                    setResult(0,intent);
                    finish();
                }
            }
        });
    }
}
