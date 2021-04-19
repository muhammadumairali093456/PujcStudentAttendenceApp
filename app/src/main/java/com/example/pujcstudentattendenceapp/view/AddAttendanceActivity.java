package com.example.pujcstudentattendenceapp.view;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.pujcstudentattendenceapp.R;

import com.example.pujcstudentattendenceapp.controller.ApplicationContext;
import com.example.pujcstudentattendenceapp.db.DBAdapter;
import com.example.pujcstudentattendenceapp.model.Attendence_model;
import com.example.pujcstudentattendenceapp.model.Student_model;

import java.util.ArrayList;

public class AddAttendanceActivity  extends Activity {

    ArrayList<Student_model> studentList;
    private ListView listView;
    private ArrayAdapter<String> listAdapter;
    int sessionId = 0;
    String status = "P";
    Button attendanceSubmit;
    String poranivalue = "";
    int poraniposition = 0;
    int jhanda = 0;
    DBAdapter dbAdapter = new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.__listview_main);

        sessionId = getIntent().getExtras().getInt("sessionId");


        listView = (ListView) findViewById(R.id.listview);
        final ArrayList<String> student_List = new ArrayList<String>();

        studentList = ((ApplicationContext) AddAttendanceActivity.this.getApplicationContext()).getStudentList();


        for (Student_model student : studentList) {
            String users = student.getStudent_name() + "\n" + student.getStudent_rollno();

            student_List.add(users);
            Log.d("users: ", users);

        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.add_student_attendance, R.id.labelA, student_List);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long id) {


                adapterView.getChildAt(position);
                final Student_model student = studentList.get(position);
                final Dialog dialog = new Dialog(AddAttendanceActivity.this);
                // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//...........
                dialog.setContentView(R.layout.test_layout);
                // set title and cancelable
                RadioGroup radioGroup;
                final RadioButton present;
                final RadioButton absent;
                radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.PresentradioButton) {

                            status = "P";
                        } else if (checkedId == R.id.AbsentradioButton) {

                            status = "A";
                        } else {
                        }


                    }
                });

                attendanceSubmit = (Button) dialog.findViewById(R.id.attendanceSubmitButton);
                attendanceSubmit.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View kuchbhi) {
                        Attendence_model attendence = new Attendence_model();

                        attendence.setAttendance_session_id(sessionId);
                        attendence.setAttendance_student_id(student.getStudent_id());
                        attendence.setAttendance_status(status);

                        DBAdapter dbAdapter = new DBAdapter(AddAttendanceActivity.this);
                        dbAdapter.addNewAttendance(attendence);

                        dialog.dismiss();

                    }
                });

                dialog.setCancelable(true);

                dialog.show();
                adapterView.getChildAt(position).setClickable(true);

            }

        });


    }
}