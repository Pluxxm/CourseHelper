package com.example.coursehelper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.coursehelper.R;
import com.example.coursehelper.bean.OptionalCourse;
import com.example.coursehelper.fragment.CoursesFragment;

import java.util.List;
import java.util.zip.Inflater;

public class CourseAllAdapter extends RecyclerView.Adapter<CourseAllViewHolder> implements View.OnClickListener {
    protected Context context;
    protected List<OptionalCourse> courses;
    private LayoutInflater inflater;

    private OnItemClickListener mOnItemClickListener;

    public CourseAllAdapter(Context context, List<OptionalCourse> courses) {
        this.context = context;
        this.courses = courses;
        inflater = LayoutInflater.from(context);
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

    @NonNull
    @Override
    public CourseAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_all_courses, parent,false);
        CourseAllViewHolder courseAllViewHolder = new CourseAllViewHolder(view);

        view.setOnClickListener(this);

        return courseAllViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAllViewHolder holder, final int position) {
        OptionalCourse optionalCourse = courses.get(position);
        String time = optionalCourse.getStart_week()+"-"+ optionalCourse.getEnd_week()+"周       "
                + optionalCourse.getCourse_day()+"  "+ optionalCourse.getStart_time()+"-"+ optionalCourse.getEnd_time()+"节";

        holder.course_name.setText(optionalCourse.getTitle());
        holder.course_type.setText(optionalCourse.getCourse_type());
        holder.course_teacher.setText(optionalCourse.getTeacher());
        holder.course_time.setText(time);

        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return null != courses ? courses.size():0 ;
    }

}
class CourseAllViewHolder extends RecyclerView.ViewHolder{
    public TextView course_name;
    public TextView course_teacher;
    public TextView course_type;
    public TextView course_time;
    public CourseAllViewHolder(View itemView) {
        super(itemView);
        course_name = itemView.findViewById(R.id.course_name);
        course_time = itemView.findViewById(R.id.course_time);
        course_type = itemView.findViewById(R.id.course_type);
        course_teacher = itemView.findViewById(R.id.course_teacher);
    }
}

