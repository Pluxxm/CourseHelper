package com.example.coursehelper.bean;

import java.io.Serializable;

public class OptionalCourse implements Serializable {
    int course_id;
    String title;
    String course_type;
    String teacher;
    String start_week;
    String end_week;
    String course_day;
    String start_time;
    String end_time;
    String info;

    public OptionalCourse(int course_id, String title, String course_type,
                          String teacher, String start_week, String end_week, String course_day,
                          String start_time, String end_time, String info) {
        this.course_id = course_id;
        this.title = title;
        this.course_type = course_type;
        this.teacher = teacher;
        this.start_week = start_week;
        this.end_week = end_week;
        this.course_day = course_day;
        this.start_time = start_time;
        this.end_time = end_time;
        this.info = info;
    }

    public OptionalCourse(OptionalCourse course){
        this.course_id = course.course_id;
        this.title = course.title;
        this.course_type = course.course_type;
        this.teacher = course.teacher;
        this.start_week = course.start_week;
        this.end_week = course.end_week;
        this.course_day = course.course_day;
        this.start_time = course.start_time;
        this.end_time = course.end_time;
        this.info = course.info;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourse_type() {
        return course_type;
    }

    public void setCourse_type(String course_type) {
        this.course_type = course_type;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getStart_week() {
        return start_week;
    }

    public void setStart_week(String start_week) {
        this.start_week = start_week;
    }

    public String getEnd_week() {
        return end_week;
    }

    public void setEnd_week(String end_week) {
        this.end_week = end_week;
    }

    public String getCourse_day() {
        return course_day;
    }

    public void setCourse_day(String course_day) {
        this.course_day = course_day;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
