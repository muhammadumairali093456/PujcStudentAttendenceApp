package com.example.pujcstudentattendenceapp.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pujcstudentattendenceapp.R;
import com.example.pujcstudentattendenceapp.db.DBAdapter;
import com.example.pujcstudentattendenceapp.model.Student_model;

public class AddStudentActivity  extends Activity {

    Button registerButton;
    EditText textName;
    EditText textrollno;

    EditText textcontact;
    EditText textaddress;
    Spinner spinnerbranch,spinneryear;
    String deptname,year;
    private String[] branchString = new String[] { "I.T","L.L.B","B.B.A","B.com","BSM"};
    private String[] yearString = new String[] {"2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032","2033","2034","2035","2036","2037","2038","2039","2040","2041","2042","2043","2044","2045","2046","2047","2048","2049","2050"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstudent);
        spinnerbranch=(Spinner)findViewById(R.id.spinnerdept);
        spinneryear=(Spinner)findViewById(R.id.spinneryear);
        textName=(EditText)findViewById(R.id.editTextname);
        textrollno=(EditText)findViewById(R.id.editTextrollno);
        textcontact=(EditText)findViewById(R.id.editTextContact);
        textaddress=(EditText)findViewById(R.id.editTextaddress);

        registerButton=(Button)findViewById(R.id.submitbtn);

        spinnerbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {

                ((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
                deptname =(String) spinnerbranch.getSelectedItem();

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




        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //......................................validation
                String name = textName.getText().toString();
                String rollno = textrollno.getText().toString();
                String phone_no = textcontact.getText().toString();
                String address = textaddress.getText().toString();
             //   String deptname = textDeptName.getText().toString();
            //    String year = studyYear.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    textName.setError("please enter name");
                }

                else if (TextUtils.isEmpty(rollno)) {
                    textrollno.setError("please enter rollno");
                }
                else if (TextUtils.isEmpty(phone_no)) {
                    textcontact.setError("please enter phoneno");
                }

                else if (TextUtils.isEmpty(address)) {
                    textaddress.setError("enter address");
                }

                else {

                    Student_model student = new Student_model();

                    student.setStudent_name(name);
                    student.setStudent_rollno(rollno);
                    student.setStudent_mobilenumber(phone_no);
                    student.setStudent_address(address);
                    student.setStudent_department(deptname);
                    student.setStudent_year(year);
                    DBAdapter dbAdapter= new DBAdapter(AddStudentActivity.this);
                    dbAdapter.addStudent(student);

                    Intent intent =new Intent(AddStudentActivity.this,MenuActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "student added successfully", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


}
