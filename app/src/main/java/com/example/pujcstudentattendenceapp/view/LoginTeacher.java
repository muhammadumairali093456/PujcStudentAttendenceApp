package com.example.pujcstudentattendenceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pujcstudentattendenceapp.R;

import com.example.pujcstudentattendenceapp.controller.ApplicationContext;
import com.example.pujcstudentattendenceapp.db.DBAdapter;
import com.example.pujcstudentattendenceapp.model.Faculty_model;

public class LoginTeacher extends AppCompatActivity {

    EditText username,password;
    Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teacher);
        username = (EditText)findViewById(R.id.editTextusernameteacher);
        password = (EditText)findViewById(R.id.editTextteacherpassword);
        Login =(Button)findViewById(R.id.loginteacher);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = username.getText().toString();
                String pass_word = password.getText().toString();

                if (TextUtils.isEmpty(user_name))
                {
                    username.setError("Invalid User Name");
                }
                else if(TextUtils.isEmpty(pass_word))
                {
                    password.setError("enter password");
                }
                DBAdapter dbAdapter = new DBAdapter(LoginTeacher.this);
                Faculty_model faculty_valid = dbAdapter.validateFaculty(user_name, pass_word);

                if(faculty_valid!=null)
                {
                    Intent intent = new Intent(LoginTeacher.this,AddAttandanceSessionActivity.class);
                    startActivity(intent);
                    ((ApplicationContext) LoginTeacher.this.getApplicationContext()).setFaculty(faculty_valid);
                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
