package com.example.pujcstudentattendenceapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pujcstudentattendenceapp.R;

import com.example.pujcstudentattendenceapp.db.DBAdapter;
import com.example.pujcstudentattendenceapp.model.Faculty_model;

public class AddTeacherActivity extends Activity {

    Button registerButton;
    EditText textFirstName;
    EditText textLastName;
    EditText textcontact;
    EditText textaddress;
    EditText textusername;
    EditText textpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addteacher);
        textFirstName=(EditText)findViewById(R.id.editTextFirstName);
        textLastName=(EditText)findViewById(R.id.editTextLastName);
        textcontact=(EditText)findViewById(R.id.editTextPhone);
        textaddress=(EditText)findViewById(R.id.editTextadd);
        textusername=(EditText)findViewById(R.id.editTextUsername);
        textpassword=(EditText)findViewById(R.id.editTextpass);
        registerButton=(Button)findViewById(R.id.Registerbtn);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String first_name = textFirstName.getText().toString();
                String last_name = textLastName.getText().toString();
                String phone_no = textcontact.getText().toString();
                String address = textaddress.getText().toString();
                String userName = textusername.getText().toString();
                String passWord = textpassword.getText().toString();

                if (TextUtils.isEmpty(first_name)) {
                    textFirstName.setError("please enter firstname");
                }
                else if (TextUtils.isEmpty(last_name)) {
                    textLastName.setError("please enter lastname");
                }
                else if (TextUtils.isEmpty(phone_no)) {
                    textcontact.setError("please enter phoneno");
                }

                else if (TextUtils.isEmpty(address)) {
                    textaddress.setError("enter address");
                }
                else if (TextUtils.isEmpty(userName)) {
                    textcontact.setError("please enter username");
                }
                else if (TextUtils.isEmpty(passWord)) {
                    textaddress.setError("enter password");
                }
                else {

                    Faculty_model faculty = new Faculty_model();
                    faculty.setFaculty_firstname(first_name);
                    faculty.setFaculty_lastname(last_name);
                    faculty.setFaculty_mobilenumber(phone_no);
                    faculty.setFaculty_address(address);
                    faculty.setFaculty_username(userName);
                    faculty.setFaculty_password(passWord);

                    DBAdapter dbAdapter = new DBAdapter(AddTeacherActivity.this);
                    dbAdapter.addFaculty(faculty);

                    Intent intent =new Intent(AddTeacherActivity.this,MenuActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Faculty added successfully", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}
