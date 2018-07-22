package com.example.coursehelper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.coursehelper.R;
import com.example.coursehelper.bean.OptionalCourse;
import com.example.coursehelper.ui.activity.SelectCourseActivity;

import java.util.List;

public class SelectCourseAdapter extends RecyclerView.Adapter<SelectCourseViewHolder> implements View.OnClickListener {
    protected Context context;
    protected List<OptionalCourse> optionalCourses;
    private LayoutInflater inflater;

    private OnItemClickListener mOnItemClickListener;

    public SelectCourseAdapter(Context context, List<OptionalCourse> optionalCourses){
        this.context = context;
        this.optionalCourses = optionalCourses;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SelectCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_all_courses,parent,false);
        SelectCourseViewHolder selectCourseViewHolder = new SelectCourseViewHolder(view);

        view.setOnClickListener(this);
        return selectCourseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectCourseViewHolder holder, int position) {
        OptionalCourse optionalCourse = optionalCourses.get(position);
        String time = optionalCourse.getStart_week()+"-"+optionalCourse.getEnd_week()+"周       "
                +optionalCourse.getCourse_day()+"  "+optionalCourse.getStart_time()+"-"+optionalCourse.getEnd_time()+"节";

        holder.course_name.setText(optionalCourse.getTitle());
        holder.course_type.setText(optionalCourse.getCourse_type());
        holder.course_teacher.setText(optionalCourse.getTeacher());
        holder.course_time.setText(time);

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return null != optionalCourses? optionalCourses.size():0 ;
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.onItemClick((Integer)v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        mOnItemClickListener = itemClickListener;
    }


    public interface  OnItemClickListener{
        void onItemClick(int position);
        //void onLongClick(int position);
    }
}
class SelectCourseViewHolder extends RecyclerView.ViewHolder{
    public TextView course_name;
    public TextView course_teacher;
    public TextView course_type;
    public TextView course_time;
    public SelectCourseViewHolder(View itemView) {
        super(itemView);
        course_name = itemView.findViewById(R.id.course_name);
        course_time = itemView.findViewById(R.id.course_time);
        course_type = itemView.findViewById(R.id.course_type);
        course_teacher = itemView.findViewById(R.id.course_teacher);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
