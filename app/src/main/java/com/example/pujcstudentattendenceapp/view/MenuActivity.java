package com.example.pujcstudentattendenceapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pujcstudentattendenceapp.R;

import com.example.pujcstudentattendenceapp.controller.ApplicationContext;
import com.example.pujcstudentattendenceapp.db.DBAdapter;
import com.example.pujcstudentattendenceapp.model.Attendence_model;

import java.util.ArrayList;

public class MenuActivity extends Activity {


    Button addStudent;
    Button addTeacher;
    Button viewStudent;
    Button viewTeacher;
    Button logout;
    Button attendancePerStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        addStudent = (Button) findViewById(R.id.buttonaddstudent);
        addTeacher = (Button) findViewById(R.id.buttonaddteacher);
        viewStudent = (Button) findViewById(R.id.buttonViewstudent);
        viewTeacher = (Button) findViewById(R.id.buttonviewteacher);
        logout = (Button) findViewById(R.id.buttonlogout);

        addStudent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent =new Intent(MenuActivity.this,AddStudentActivity.class);
                startActivity(intent);
            }
        });

        addTeacher.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent =new Intent(MenuActivity.this,AddTeacherActivity.class);
                startActivity(intent);
            }
        });

        viewTeacher.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent =new Intent(MenuActivity.this,ViewTeacherActivity.class);
                startActivity(intent);
            }
        });


        viewStudent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent =new Intent(MenuActivity.this,ViewStudentActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent =new Intent(MenuActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        attendancePerStudent=(Button)findViewById(R.id.attendancePerStudentButton);
        attendancePerStudent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DBAdapter dbAdapter = new DBAdapter(MenuActivity.this);
                ArrayList<Attendence_model> attendanceList=dbAdapter.getAllAttendanceByStudent();
                ((ApplicationContext)MenuActivity.this.getApplicationContext()).setAttendanceList(attendanceList);

                Intent intent = new Intent(MenuActivity.this,ViewAttendancePerStudentActivity.class);
                startActivity(intent);

            }
        });

    }

}
