package com.example.pujcstudentattendenceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pujcstudentattendenceapp.R;

import com.example.pujcstudentattendenceapp.db.DBAdapter;

public class LoginAdmin extends AppCompatActivity {

    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    TextView mTextViewforgetpassword;
    DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        db = new DBAdapter(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mButtonLogin = (Button)findViewById(R.id.button_login);
        mTextViewRegister = (TextView)findViewById(R.id.textview_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginAdmin.this,Register.class);
                startActivity(registerIntent);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                if (TextUtils.isEmpty(user))
                {
                    mTextUsername.setError("Invalid User Name");
                }
                else if(TextUtils.isEmpty(pwd))
                {
                    mTextPassword.setError("enter password");
                }
                else{
                    Boolean res = db.checkUser(user, pwd);
                    if(res == true)
                    {
                        Intent intent =new Intent(LoginAdmin.this,MenuActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(LoginAdmin.this,"Login Error",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        mTextViewforgetpassword = (TextView)findViewById(R.id.forgetpassword);

        mTextViewforgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgetIntent = new Intent(LoginAdmin.this,ForgetPassword.class);
                startActivity(forgetIntent);
            }
        });

    }
}
