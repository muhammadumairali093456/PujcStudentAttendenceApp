package com.example.pujcstudentattendenceapp.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pujcstudentattendenceapp.R;

import com.example.pujcstudentattendenceapp.controller.ApplicationContext;
import com.example.pujcstudentattendenceapp.db.DBAdapter;
import com.example.pujcstudentattendenceapp.model.Attendence_model;
import com.example.pujcstudentattendenceapp.model.Attendence_session_model;
import com.example.pujcstudentattendenceapp.model.Faculty_model;
import com.example.pujcstudentattendenceapp.model.Student_model;

import java.util.ArrayList;
import java.util.Calendar;

public class AddAttandanceSessionActivity  extends Activity {
    private ImageButton date;
    private Calendar cal;
    private int day;
    private int month;
    private int dyear;
    private EditText dateEditText;
    EditText subject;
    String branch,year;
    Spinner spinnerbranch,spinneryear;
    private String[] branchString = new String[] { "I.T","L.L.B","B.B.A","B.com","BSM"};
    private String[] yearString = new String[] {"2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032","2033","2034","2035","2036","2037","2038","2039","2040","2041","2042","2043","2044","2045","2046","2047","2048","2049","2050"};
   DBAdapter db;

   Button btnaddatten;
   Button btnviewatten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_attandance);

        spinnerbranch=(Spinner)findViewById(R.id.spinnerbranchView);
        spinneryear=(Spinner)findViewById(R.id.spinneryearView);



        spinnerbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {

                ((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
                branch =(String) spinnerbranch.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        ArrayAdapter<String> adapter_branch = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, branchString);
        adapter_branch
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerbranch.setAdapter(adapter_branch);

        ///......................spinner2

        spinneryear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {

                ((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
                year =(String) spinneryear.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, yearString);
        adapter_year
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneryear.setAdapter(adapter_year);








        date = (ImageButton)findViewById(R.id.DatePickerButton);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        dyear = cal.get(Calendar.YEAR);
        dateEditText = (EditText) findViewById(R.id.DateEditText);

        subject = (EditText) findViewById(R.id.Subject);
      date.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              showDialog(0);
          }
      });




        btnaddatten = (Button)findViewById(R.id.add_attendence);
        btnviewatten = (Button)findViewById(R.id.view_attendence);


        btnaddatten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                   String   dateattendance =  dateEditText.getText().toString().trim();

                     Attendence_session_model attendanceSession = new Attendence_session_model();
                     Faculty_model model = ((ApplicationContext) AddAttandanceSessionActivity.this.getApplicationContext()).getFaculty();

                     attendanceSession.setAttendance_session_faculty_id(model.getFaculty_id());
                     attendanceSession.setAttendance_session_department(branch);
                     attendanceSession.setAttendance_session_class(year);
                     attendanceSession.setAttendance_session_date(dateattendance);
                     attendanceSession.setAttendance_session_subject(subject.getText().toString());

                     DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);

                         int sessionId = dbAdapter.addAttendanceSession(attendanceSession);

                         ArrayList<Student_model> studentList = dbAdapter.getAllStudentByBranchYear(branch, year);
                         ((ApplicationContext) AddAttandanceSessionActivity.this.getApplicationContext()).setStudentList(studentList);


                         Intent intent = new Intent(AddAttandanceSessionActivity.this, AddAttendanceActivity.class);
                         intent.putExtra("sessionId", sessionId);
                         startActivity(intent);


            }
        });



        btnviewatten=(Button)findViewById(R.id.view_attendence);
        btnviewatten.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Attendence_session_model attendanceSession = new Attendence_session_model();
                Faculty_model model=((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).getFaculty();

                attendanceSession.setAttendance_session_faculty_id(model.getFaculty_id());
                attendanceSession.setAttendance_session_department(branch);
                attendanceSession.setAttendance_session_class(year);
              //  attendanceSession.setAttendance_session_date(dateEditText.getText().toString());
                attendanceSession.setAttendance_session_subject(subject.getText().toString());

                DBAdapter dbAdapter = new DBAdapter(AddAttandanceSessionActivity.this);

                ArrayList<Attendence_model> attendanceList = dbAdapter.getTotalAttendanceBySessionID(attendanceSession);
                ((ApplicationContext)AddAttandanceSessionActivity.this.getApplicationContext()).setAttendanceList(attendanceList);

                Intent intent = new Intent(AddAttandanceSessionActivity.this,ViewAttendanceByTeacherActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, dyear, month, day);
    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            dateEditText.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
        }
    };


}
