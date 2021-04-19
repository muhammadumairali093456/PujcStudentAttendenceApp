package com.example.pujcstudentattendenceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pujcstudentattendenceapp.R;

import com.example.pujcstudentattendenceapp.db.DBAdapter;

public class Register extends AppCompatActivity {

    DBAdapter db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DBAdapter(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username1);
        mTextPassword = (EditText)findViewById(R.id.edittext_password1);
        mTextCnfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        mButtonRegister = (Button)findViewById(R.id.button_register);
        mTextViewLogin = (TextView)findViewById(R.id.textview_login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(Register.this,MainActivity.class);
                startActivity(LoginIntent);
            }
        });
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();

                if(pwd.equals(cnf_pwd)){
                    if(!db.checkUser(user)) {
                        long val = db.addUser(user, pwd);
                        System.out.println(val);
                        if (val > 0) {
                            Toast.makeText(Register.this, "You have registered", Toast.LENGTH_SHORT).show();
                            Intent moveToLogin = new Intent(Register.this, LoginAdmin.class);
                            startActivity(moveToLogin);
                        } else {
                            Toast.makeText(Register.this, "Registeration Error", Toast.LENGTH_SHORT).show();
                        }
                   }
                    else {
                       Toast.makeText(Register.this, "User have already registered", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Register.this,"Password is not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
