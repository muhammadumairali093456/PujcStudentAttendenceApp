package com.example.pujcstudentattendenceapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pujcstudentattendenceapp.R;

import com.example.pujcstudentattendenceapp.db.DBAdapter;
import com.example.pujcstudentattendenceapp.model.Student_model;

import java.util.ArrayList;

public class ViewStudentByBranchYear extends Activity {
    ArrayList<Student_model> studentList;
    private ListView listView ;
    private ArrayAdapter<String> listAdapter;
    String branch;
    String year;

    DBAdapter dbAdapter = new DBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.__listview_main);

        listView=(ListView)findViewById(R.id.listview);
        final ArrayList<String> student_List = new ArrayList<String>();

        branch=getIntent().getExtras().getString("branch");
        year =getIntent().getExtras().getString("year");

        studentList=dbAdapter.getAllStudentByBranchYear(branch, year);

        for(Student_model student : studentList)
        {
                String users = "Student Name:     " + student.getStudent_name() + "\n" + "Student Rollno:   " + student.getStudent_rollno();

                student_List.add(users);
                Log.d("users: ", users);

        }
        listAdapter = new ArrayAdapter<String>(this, R.layout.view_student_list, R.id.label, student_List);
        if(listAdapter.getCount()!=0){
            listView.setAdapter(listAdapter);

        }else{
            Toast.makeText(getApplicationContext(),"No Student Enroll in this  year",Toast.LENGTH_LONG).show();
        }

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int position, long arg3) {



                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewStudentByBranchYear.this);

                alertDialogBuilder.setTitle(getTitle()+"decision");
                alertDialogBuilder.setMessage("Are you sure?");

                alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        student_List.remove(position);
                        listAdapter.notifyDataSetChanged();
                        listAdapter.notifyDataSetInvalidated();

                        dbAdapter.deleteStudent(studentList.get(position).getStudent_id());
//                         studentList=dbAdapter.getAllStudentByBranchYear(branch, year);
//
//                        for(Student_model student : studentList)
//                        {
//                            String users = " Name: " + student.getStudent_name()+"\n rollno:"+student.getStudent_rollno();
//                            student_List.add(users);
//                            Log.d("users: ", users);
//
//                        }
                    }

                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // cancel the alert box and put a Toast to the user
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "You choose cancel",
                                Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                // show alert
                alertDialog.show();





                return false;
            }
        });
    }



}
