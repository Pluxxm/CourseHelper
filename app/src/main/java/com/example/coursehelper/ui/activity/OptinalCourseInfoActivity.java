package com.example.coursehelper.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.coursehelper.R;
import com.example.coursehelper.bean.OptionalCourse;

public class OptinalCourseInfoActivity extends AppCompatActivity {
    private TextView info_name;
    private TextView info_type;
    private TextView info_teacher;
    private TextView info_info;
    private TextView info_time;
    private TextView info_week;

    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimalcourseinfo);

        initView();
        initText();

    }

    private void initText() {
        Bundle bundle = getIntent().getExtras();
        OptionalCourse optionalCourse = (OptionalCourse) bundle.getSerializable("optinalCourse");

        info_name.setText(optionalCourse.getTitle());
        info_teacher.setText(optionalCourse.getTeacher());
        info_time.setText(optionalCourse.getCourse_day()+" "+optionalCourse.getStart_time()+"-"+optionalCourse.getEnd_time()+" 节");
        info_week.setText(optionalCourse.getStart_week()+"-"+optionalCourse.getEnd_week()+" 周");
        info_info.setText(optionalCourse.getInfo());
        info_type.setText(optionalCourse.getCourse_type());
    }

    private void initView() {
        info_info = findViewById(R.id.info_info);
        info_name = findViewById(R.id.info_name);
        info_teacher = findViewById(R.id.info_teacher);
        info_time = findViewById(R.id.info_time);
        info_type = findViewById(R.id.info_type);
        info_week = findViewById(R.id.info_week);

        toolbar = findViewById(R.id.toolbar_info);
        setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
