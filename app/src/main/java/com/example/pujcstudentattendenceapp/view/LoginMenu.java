package com.example.pujcstudentattendenceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pujcstudentattendenceapp.R;

public class LoginMenu extends AppCompatActivity {

    Button adminlogin,teacherlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_menu);
        adminlogin =(Button) findViewById(R.id.loginadmin);
        teacherlogin = (Button)findViewById(R.id.loginteacher);
        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginMenu.this, LoginAdmin.class);
                startActivity(i);
            }
        });
        teacherlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginMenu.this,LoginTeacher.class);
                startActivity(intent);
            }
        });

    }
}
