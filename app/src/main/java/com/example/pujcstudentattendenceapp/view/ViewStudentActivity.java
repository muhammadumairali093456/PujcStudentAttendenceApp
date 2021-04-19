package com.example.pujcstudentattendenceapp.view;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pujcstudentattendenceapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewStudentActivity extends Activity {

    Button submit;
    String branch,year;
    Spinner spinnerbranch,spinneryear;
    private String[] branchString = new String[] { "I.T","L.L.B","B.B.A","B.com","BSM"};
    private String[] yearString = new String[] {"2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032","2033","2034","2035","2036","2037","2038","2039","2040","2041","2042","2043","2044","2045","2046","2047","2048","2049","2050"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewstudent);



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

        submit=(Button)findViewById(R.id.buttonsub);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ViewStudentActivity.this,ViewStudentByBranchYear.class);
                intent.putExtra("branch", branch);
                intent.putExtra("year", year);
                startActivity(intent);



            }
        });

    }


}
