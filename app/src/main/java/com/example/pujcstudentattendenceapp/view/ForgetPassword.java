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

public class ForgetPassword extends AppCompatActivity {

    EditText Edittextusername;
    Button confirmuser;
    DBAdapter databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        Edittextusername = (EditText)findViewById(R.id.EditText_Username);
        confirmuser = (Button)findViewById(R.id.button_Changepassword);
        databaseHelper = new DBAdapter(this);
        setTitle("Recovery password");
        confirmuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verifyFromSQLite();

            }


        });
    }
    void verifyFromSQLite(){

        if (Edittextusername.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill your username", Toast.LENGTH_SHORT).show();
            return;
        }


        if (databaseHelper.checkUser(Edittextusername.getText().toString().trim())) {
            Intent accountsIntent = new Intent(this, ConfirmPassword.class);
            accountsIntent.putExtra("UserName", Edittextusername.getText().toString().trim());
            startActivity(accountsIntent);
        } else {
            Toast.makeText(this,"error username not exits",Toast.LENGTH_LONG) .show();
        }
    }


}
