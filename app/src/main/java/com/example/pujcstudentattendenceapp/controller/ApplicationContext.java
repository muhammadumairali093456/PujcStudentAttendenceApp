package com.example.pujcstudentattendenceapp.controller;

import android.app.Application;


import com.example.pujcstudentattendenceapp.model.Attendence_model;
import com.example.pujcstudentattendenceapp.model.Attendence_session_model;
import com.example.pujcstudentattendenceapp.model.Faculty_model;
import com.example.pujcstudentattendenceapp.model.Student_model;

import java.util.ArrayList;

public class ApplicationContext extends Application {

    private Faculty_model faculty;
    private Attendence_session_model attendanceSession;
    private ArrayList<Student_model> studentList;
    private ArrayList<Attendence_model> attendanceList;

    public Faculty_model getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty_model faculty) {
        this.faculty = faculty;
    }

    public Attendence_session_model getAttendanceSession() {
        return attendanceSession;
    }

    public void setAttendanceSession(Attendence_session_model attendanceSession) {
        this.attendanceSession = attendanceSession;
    }

    public ArrayList<Student_model> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student_model> studentList) {
        this.studentList = studentList;
    }

    public ArrayList<Attendence_model> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(ArrayList<Attendence_model> attendanceList) {
        this.attendanceList = attendanceList;
    }
}
