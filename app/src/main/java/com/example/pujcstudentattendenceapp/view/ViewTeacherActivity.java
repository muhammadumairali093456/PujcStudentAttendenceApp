package com.example.pujcstudentattendenceapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pujcstudentattendenceapp.R;

import com.example.pujcstudentattendenceapp.db.DBAdapter;
import com.example.pujcstudentattendenceapp.model.Faculty_model;

import java.util.ArrayList;


public class ViewTeacherActivity extends Activity {

    ArrayList<Faculty_model> facultyList;
    private ListView listView;
    private ArrayAdapter<String> listAdapter;

    DBAdapter dbAdapter = new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.__listview_main);

        listView = (ListView) findViewById(R.id.listview);
        final ArrayList<String> faculty_List = new ArrayList<String>();

        facultyList = dbAdapter.getAllFaculty();

        for (Faculty_model faculty : facultyList) {
            String users = " FirstName: " + faculty.getFaculty_firstname() + "\nLastname:" + faculty.getFaculty_lastname();

            faculty_List.add(users);
            Log.d("users: ", users);

        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.view_faculty_list, R.id.labelF, faculty_List);
        listView.setAdapter(listAdapter);


    }
}