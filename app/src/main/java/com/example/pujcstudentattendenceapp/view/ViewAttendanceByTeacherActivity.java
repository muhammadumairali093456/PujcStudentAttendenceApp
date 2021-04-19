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

import com.example.pujcstudentattendenceapp.controller.ApplicationContext;
import com.example.pujcstudentattendenceapp.db.DBAdapter;
import com.example.pujcstudentattendenceapp.model.Attendence_model;
import com.example.pujcstudentattendenceapp.model.Student_model;

import java.util.ArrayList;

public class ViewAttendanceByTeacherActivity extends Activity {

    ArrayList<Attendence_model> attendanceList;
    private ListView listView;
    private ArrayAdapter<String> listAdapter;

    DBAdapter dbAdapter = new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.__listview_main);

        listView = (ListView) findViewById(R.id.listview);
        final ArrayList<String> attendance_List = new ArrayList<String>();
        attendance_List.add("Id | StudentName |  Status");

        attendanceList = ((ApplicationContext) ViewAttendanceByTeacherActivity.this.getApplicationContext()).getAttendanceList();

        for (Attendence_model attendance : attendanceList) {
            String users = "";
            if (attendance.getAttendance_session_id() != 0) {
                DBAdapter dbAdapter = new DBAdapter(ViewAttendanceByTeacherActivity.this);
                Student_model student = dbAdapter.getStudentById(attendance.getAttendance_student_id());
                users =  "Student Name:   " + student.getStudent_name() + "\n" + "Student rollno:    " + student.getStudent_rollno() + "                  " + attendance.getAttendance_status();
            }

            else {
                users = attendance.getAttendance_status();
            }

            attendance_List.add(users);
            Log.d("users: ", users);

        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.view_attendance_list, R.id.labelAttendance, attendance_List);
        listView.setAdapter(listAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int position, long arg3) {



                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewAttendanceByTeacherActivity.this);

                alertDialogBuilder.setTitle(getTitle()+"decision");
                alertDialogBuilder.setMessage("Are you sure?");

                alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        attendance_List.remove(position);
                        listAdapter.notifyDataSetChanged();
                        listAdapter.notifyDataSetInvalidated();

                        dbAdapter.deleteAttendanceSession(attendanceList.get(position).getAttendance_session_id());

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