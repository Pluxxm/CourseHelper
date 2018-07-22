package com.example.coursehelper.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.coursehelper.R;
import com.example.coursehelper.adapter.CourseAllAdapter;
import com.example.coursehelper.bean.OptionalCourse;
import com.example.coursehelper.bean.User;
import com.example.coursehelper.biz.CoursesBiz;
import com.example.coursehelper.net.CommonCallBack;
import com.example.coursehelper.ui.activity.OptinalCourseInfoActivity;
import com.example.coursehelper.ui.activity.SelectCourseActivity;
import com.example.coursehelper.utils.T;

import java.util.ArrayList;
import java.util.List;

public class CoursesFragment extends Fragment implements CourseAllAdapter.OnItemClickListener {
    RecyclerView recyclerView_courses;
    private ProgressDialog mProgressBar;

    private Button btn_select;
    private Button btn_search;
    private List<OptionalCourse> courses = new ArrayList<>();
    private List<OptionalCourse> selectCourse = new ArrayList<>();

    private CoursesBiz mCoursesBiz = new CoursesBiz();
    private User user;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        recyclerView_courses = view.findViewById(R.id.recycler_courses);

        btn_select = view.findViewById(R.id.btn_select);
        btn_search = view.findViewById(R.id.btn_search);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectCourseActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        initEvent();

        recyclerView_courses.setLayoutManager(new GridLayoutManager(getActivity(),1));
        CourseAllAdapter courseAllAdapter = new CourseAllAdapter(getActivity(), courses);
        courseAllAdapter.setOnItemClickListener(this);
        recyclerView_courses.setAdapter(courseAllAdapter);

        return view;
    }
    public void initEvent(){
        mProgressBar = new ProgressDialog (getActivity());
        mProgressBar.setMessage("加载中...");
        startLoadingProgress();
        mCoursesBiz.loadCourse(new CommonCallBack<List<OptionalCourse>>() {
            @Override
            public void onError(Exception e) {
                stopLoadingProgress();
                T.showToast(e.getMessage());
            }

            @Override
            public void onSuccess(List<OptionalCourse> response) {
                stopLoadingProgress();
                T.showToast("获取课程信息成功");
                courses.clear();
                for(OptionalCourse p: response){
                    courses.add(new OptionalCourse(p));
                    Log.i("COURSE",p.getTitle());
                }
            }
        });
    }


    protected void stopLoadingProgress() {
        if(mProgressBar != null && mProgressBar.isShowing()){
            mProgressBar.dismiss();
        }
    }
    protected void startLoadingProgress() {
        mProgressBar.show();
    }

    @Override
    public void onItemClick(int position) {
        OptionalCourse optinalCourse = courses.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("optinalCourse",optinalCourse);
        Intent intent = new Intent(getActivity(),OptinalCourseInfoActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void getUser(User user){
        this.user = user;
        Log.i("MAIN","在courseFragment里获得了user "+user.getUser_id());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopLoadingProgress();
        mCoursesBiz.onDestroy();
        mProgressBar = null;
    }
}
