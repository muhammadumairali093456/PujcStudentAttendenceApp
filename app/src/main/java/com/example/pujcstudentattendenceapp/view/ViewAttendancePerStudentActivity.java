package com.example.pujcstudentattendenceapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pujcstudentattendenceapp.R;

import com.example.pujcstudentattendenceapp.controller.ApplicationContext;
import com.example.pujcstudentattendenceapp.db.DBAdapter;
import com.example.pujcstudentattendenceapp.model.Attendence_model;
import com.example.pujcstudentattendenceapp.model.Student_model;

import java.util.ArrayList;

public class ViewAttendancePerStudentActivity extends Activity {

    ArrayList<Attendence_model> attendanceList;
    private ListView listView ;
    private ArrayAdapter<String> listAdapter;

    DBAdapter dbAdapter = new DBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.__listview_main);
        listView=(ListView)findViewById(R.id.listview);
        final ArrayList<String> attendance_List = new ArrayList<String>();
        attendance_List.add("Present Count Per Student");
        attendanceList=((ApplicationContext)ViewAttendancePerStudentActivity.this.getApplicationContext()).getAttendanceList();

        for(Attendence_model attendance : attendanceList){

            String users = "";

            DBAdapter dbAdapter = new DBAdapter(ViewAttendancePerStudentActivity.this);
            Student_model student =dbAdapter.getStudentById(attendance.getAttendance_student_id());
            users = attendance.getAttendance_student_id()+".     "+student.getStudent_name()+","+student.getStudent_rollno()+"                  "+attendance.getAttendance_session_id();
            attendance_List.add(users);
        }
        listAdapter = new ArrayAdapter<String>(this, R.layout.view_attendance_list_per_student, R.id.labelAttendancePerStudent, attendance_List);
        listView.setAdapter( listAdapter );
    }

}
