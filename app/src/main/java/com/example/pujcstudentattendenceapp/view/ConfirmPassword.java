package com.example.pujcstudentattendenceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pujcstudentattendenceapp.R;

import com.example.pujcstudentattendenceapp.db.DBAdapter;

public class ConfirmPassword extends AppCompatActivity {

    EditText password;
    EditText confirmpassword;
    Button resetpassword;
    DBAdapter databaseHelper;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);
        password = (EditText) findViewById(R.id.editTextteacherpassword);
        confirmpassword = (EditText) findViewById(R.id.editTextconfirmpassword);
        resetpassword = (Button) findViewById(R.id.reset);
        databaseHelper = new DBAdapter(this);
        Intent intent = getIntent();
        username = intent.getStringExtra("UserName");

        setTitle("Reset password");

        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePassword();
            }
        });
    }
    private void updatePassword() {

        String value1 = password.getText().toString().trim();
        String value2 = confirmpassword.getText().toString().trim();

        if (value1.isEmpty() && value2.isEmpty()) {
            Toast.makeText(this, "fill all fields ", Toast.LENGTH_LONG).show();
            return;
        }

        if (!value1.contentEquals(value2)) {
            Toast.makeText(this, "password doesn't match", Toast.LENGTH_LONG).show();
            return;
        }

        if (!databaseHelper.checkUser(username)) {

            Toast.makeText(this, "username doesn't exist", Toast.LENGTH_LONG).show();
            return;

        } else {
            databaseHelper.updatePassword(username, value1);

            Toast.makeText(this, "password reset successfully", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }
}
