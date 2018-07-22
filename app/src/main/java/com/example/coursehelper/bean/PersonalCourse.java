package com.example.coursehelper.bean;

import java.io.Serializable;

public class PersonalCourse implements Serializable{
    String user_id;
    String course_name;
    String teacher;
    String start_week;
    String end_week;
    String course_day;
    String start_time;
    String end_time;
    String info;

    public PersonalCourse(PersonalCourse p){
        this.user_id = p.user_id;
        this.course_name = p.course_name;
        this.teacher = p.teacher;
        this.start_week = p.start_week;
        this.end_week = p.end_week;
        this.course_day = p.course_day;
        this.start_time = p.start_time;
        this.end_time = p.end_time;
        this.info = p.info;
    }

    public PersonalCourse(String user_id, String course_name, String teacher,
                          String start_week, String end_week, String course_day,
                          String start_time, String end_time, String info) {
        this.user_id = user_id;
        this.course_name = course_name;
        this.teacher = teacher;
        this.start_week = start_week;
        this.end_week = end_week;
        this.course_day = course_day;
        this.start_time = start_time;
        this.end_time = end_time;
        this.info = info;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
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
