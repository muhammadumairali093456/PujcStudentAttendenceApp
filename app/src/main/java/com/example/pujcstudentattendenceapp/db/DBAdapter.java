package com.example.pujcstudentattendenceapp.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.constraintlayout.widget.Constraints;


import com.example.pujcstudentattendenceapp.model.Attendence_model;
import com.example.pujcstudentattendenceapp.model.Attendence_session_model;
import com.example.pujcstudentattendenceapp.model.Faculty_model;
import com.example.pujcstudentattendenceapp.model.Student_model;

import java.util.ArrayList;

public class DBAdapter extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "Attendance";

    // Contacts table name
    private static final String FACULTY_INFO_TABLE = "faculty_table";
    private static final String STUDENT_INFO_TABLE = "student_table";
    private static final String ATTENDANCE_SESSION_TABLE = "attendance_session_table";
    private static final String ATTENDANCE_TABLE = "attendance_table";
    public static final String TABLE_NAME ="registeruser";

    // Contacts Table Columns names
    public static final String COL_1 ="ID";
    public static final String COL_2 ="username";
    public static final String COL_3 ="password";


    private static final String KEY_FACULTY_ID = "faculty_id";
    private static final String KEY_FACULTY_FIRSTNAME = "faculty_firstname";
    private static final String KEY_FACULTY_LASTNAME = "faculty_Lastname";
    private static final String KEY_FACULTY_MO_NO = "faculty_mobilenumber";
    private static final String KEY_FACULTY_ADDRESS = "faculty_address";
    private static final String KEY_FACULTY_USERNAME = "faculty_username";
    private static final String KEY_FACULTY_PASSWORD = "faculty_password";

    private static final String KEY_STUDENT_ID = "student_id";
    private static final String KEY_STUDENT_NAME = "student_name";
    private static final String KEY_STUDENT_ROLLNO = "student_rollno";
    private static final String KEY_STUDENT_MO_NO = "student_mobilenumber";
    private static final String KEY_STUDENT_ADDRESS = "student_address";
    private static final String KEY_STUDENT_DEPARTMENT = "student_department";
    private static final String KEY_STUDENT_YEAR = "student_year";

    private static final String KEY_ATTENDANCE_SESSION_ID = "attendance_session_id";
    private static final String KEY_ATTENDANCE_SESSION_FACULTY_ID = "attendance_session_faculty_id";
    private static final String KEY_ATTENDANCE_SESSION_DEPARTMENT = "attendance_session_department";
    private static final String KEY_ATTENDANCE_SESSION_CLASS = "attendance_session_class";
    private static final String KEY_ATTENDANCE_SESSION_DATE = "attendance_session_date";
    private static final String KEY_ATTENDANCE_SESSION_SUBJECT = "attendance_session_subject";


    private static final String KEY_SESSION_ID = "attendance_session_id";
    private static final String KEY_ATTENDANCE_STUDENT_ID = "attendance_student_id";
    private static final String KEY_ATTENDANCE_STATUS = "attendance_status";



    public DBAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT)");


        String queryFaculty="CREATE TABLE "+ FACULTY_INFO_TABLE +" (" +
                KEY_FACULTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_FACULTY_FIRSTNAME + " TEXT , " +
                KEY_FACULTY_LASTNAME + " TEXT , " +
                KEY_FACULTY_MO_NO + " TEXT , " +
                KEY_FACULTY_ADDRESS + " TEXT," +
                KEY_FACULTY_USERNAME + " TEXT," +
                KEY_FACULTY_PASSWORD + " TEXT " + ")";
        Log.d("queryFaculty",queryFaculty);

        String queryStudent="CREATE TABLE "+ STUDENT_INFO_TABLE +" (" +
                KEY_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_STUDENT_NAME + " TEXT , " +
                KEY_STUDENT_ROLLNO + " TEXT  , " +
                KEY_STUDENT_MO_NO + " TEXT  , " +
                KEY_STUDENT_ADDRESS + " TEXT," +
                KEY_STUDENT_DEPARTMENT + " TEXT," +
                KEY_STUDENT_YEAR + " TEXT " + ")";
        Log.d("queryStudent",queryStudent );

        String queryAttendanceSession="CREATE TABLE "+ ATTENDANCE_SESSION_TABLE +" (" +
                KEY_ATTENDANCE_SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_ATTENDANCE_SESSION_FACULTY_ID + " INTEGER , " +
                KEY_ATTENDANCE_SESSION_DEPARTMENT + " TEXT , " +
                KEY_ATTENDANCE_SESSION_CLASS + " TEXT , " +
                KEY_ATTENDANCE_SESSION_DATE + " DATE ," +
                KEY_ATTENDANCE_SESSION_SUBJECT + " TEXT " + ")";
        Log.d("queryAttendanceSession",queryAttendanceSession );

        String queryAttendance="CREATE TABLE "+ ATTENDANCE_TABLE +" (" +
                KEY_SESSION_ID + " INTEGER , " +
                KEY_ATTENDANCE_STUDENT_ID + " INTEGER , " +
                KEY_ATTENDANCE_STATUS + " TEXT " + ")";
        Log.d("queryAttendance",queryAttendance );
        try
        {
            db.execSQL(queryFaculty);
            db.execSQL(queryStudent);
            db.execSQL(queryAttendanceSession);
            db.execSQL(queryAttendance);
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

       db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

        String queryFaculty="CREATE TABLE  "+ FACULTY_INFO_TABLE +" (" +
                KEY_FACULTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                KEY_FACULTY_FIRSTNAME + " TEXT ,  " +
                KEY_FACULTY_LASTNAME + " TEXT ,  " +
                KEY_FACULTY_MO_NO + " TEXT , " +
                KEY_FACULTY_ADDRESS + " TEXT ," +
                KEY_FACULTY_USERNAME + " TEXT ," +
                KEY_FACULTY_PASSWORD + " TEXT  " +")";
        Log.d("queryFaculty",queryFaculty);

        String queryStudent="CREATE TABLE "+ STUDENT_INFO_TABLE +" (" +
                KEY_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_STUDENT_NAME + " TEXT , " +
                KEY_STUDENT_ROLLNO + " TEXT  , " +
                KEY_STUDENT_MO_NO + " TEXT ,  " +
                KEY_STUDENT_ADDRESS + " TEXT ," +
                KEY_STUDENT_DEPARTMENT + " TEXT ," +
                KEY_STUDENT_YEAR + " TEXT  " + ")";
        Log.d("queryStudent",queryStudent );


        String queryAttendanceSession="CREATE TABLE "+ ATTENDANCE_SESSION_TABLE +" (" +
                KEY_ATTENDANCE_SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_ATTENDANCE_SESSION_FACULTY_ID + " INTEGER , " +
                KEY_ATTENDANCE_SESSION_DEPARTMENT + " TEXT , " +
                KEY_ATTENDANCE_SESSION_CLASS + " TEXT , " +
                KEY_ATTENDANCE_SESSION_DATE + " TEXT ," +
                KEY_ATTENDANCE_SESSION_SUBJECT + " TEXT" +")";
        Log.d("queryAttendanceSession",queryAttendanceSession );

        String queryAttendance="CREATE TABLE "+ ATTENDANCE_TABLE +" (" +
                KEY_SESSION_ID + " INTEGER , " +
                KEY_ATTENDANCE_STUDENT_ID + " INTEGER , " +
                KEY_ATTENDANCE_STATUS + " TEXT  " + ")";
        Log.d("queryAttendance",queryAttendance );

        try
        {
            db.execSQL(queryFaculty);
            db.execSQL(queryStudent);
            db.execSQL(queryAttendanceSession);
            db.execSQL(queryAttendance);
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }
    }

    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        long res = db.insert(TABLE_NAME,null,contentValues);
        db.close();
        return  res;
    }

    public void updatePassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_3, password);
        db.update(TABLE_NAME, values, COL_2+" = ?",new String[] { username });
        db.close();
    }

    public boolean checkattendance(String date){
        String[] columns = {
                KEY_ATTENDANCE_SESSION_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = KEY_ATTENDANCE_SESSION_DATE  + " = ?";
        String[] selectionArgs = { date };

        Cursor cursor = db.query(ATTENDANCE_SESSION_TABLE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }
    public boolean checkUser(String username){
        String[] columns = {
                COL_1
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COL_2 + " = ?";
        String[] selectionArgs = { username };

        Cursor cursor = db.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    public boolean checkUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }

    //facult crud
    public void addFaculty(Faculty_model faculty) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT  INTO faculty_table (faculty_firstname,faculty_Lastname,faculty_mobilenumber,faculty_address,faculty_username,faculty_password) values ('"+
                faculty.getFaculty_firstname()+"', '"+
                faculty.getFaculty_lastname()+"', '"+
                faculty.getFaculty_mobilenumber()+"', '"+
                faculty.getFaculty_address()+"', '"+
                faculty.getFaculty_username()+"', '"+
                faculty.getFaculty_password()+"')";
        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }
    public Faculty_model validateFaculty(String userName,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT  * FROM faculty_table where faculty_username='"+userName+"' and faculty_password='"+password+"'";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {

            Faculty_model faculty = new Faculty_model();
            faculty.setFaculty_id(Integer.parseInt(cursor.getString(0)));
            faculty.setFaculty_firstname(cursor.getString(1));
            faculty.setFaculty_lastname(cursor.getString(2));
            faculty.setFaculty_mobilenumber(cursor.getString(3));
            faculty.setFaculty_address(cursor.getString(4));
            faculty.setFaculty_username(cursor.getString(5));
            faculty.setFaculty_password(cursor.getString(6));
            return faculty;
        }
        return null;
    }

    public ArrayList<Faculty_model> getAllFaculty()
    {
        Log.d("in get all","in get all" );
        ArrayList<Faculty_model> list = new ArrayList<Faculty_model>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM faculty_table";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                Faculty_model faculty = new Faculty_model();
                faculty.setFaculty_id(Integer.parseInt(cursor.getString(0)));
                faculty.setFaculty_firstname(cursor.getString(1));
                faculty.setFaculty_lastname(cursor.getString(2));
                faculty.setFaculty_mobilenumber(cursor.getString(3));
                faculty.setFaculty_address(cursor.getString(4));
                faculty.setFaculty_username(cursor.getString(5));
                faculty.setFaculty_password(cursor.getString(6));
                list.add(faculty);

            }while(cursor.moveToNext());
        }
        return list;
    }
    public void deleteFaculty(int facultyId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM faculty_table WHERE faculty_id="+facultyId ;

        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }
    //student crud
    public void addStudent(Student_model student) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT  INTO   student_table (student_name,student_rollno,student_mobilenumber,student_address,student_department,student_year)  values ('"+
                student.getStudent_name()+"', '"+
                student.getStudent_rollno()+"','"+
                student.getStudent_mobilenumber()+"', '"+
                student.getStudent_address()+"', '"+
                student.getStudent_department()+"', '"+
                student.getStudent_year()+"')";
        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }

    public ArrayList<Student_model> getAllStudent()
    {
        ArrayList<Student_model> list = new ArrayList<Student_model>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM student_table";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                Student_model student = new Student_model();
                student.setStudent_id(Integer.parseInt(cursor.getString(0)));
                student.setStudent_name(cursor.getString(1));
                student.setStudent_rollno(cursor.getString(2));
                student.setStudent_mobilenumber(cursor.getString(3));
                student.setStudent_address(cursor.getString(4));
                student.setStudent_department(cursor.getString(5));
                student.setStudent_year(cursor.getString(6));
                list.add(student);
            }while(cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<Student_model> getAllStudentByBranchYear(String branch,String year)
    {
        ArrayList<Student_model> list = new ArrayList<Student_model>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM student_table where student_department='"+branch+"' and student_year='"+year+"'";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                Student_model student = new Student_model();
                student.setStudent_id(Integer.parseInt(cursor.getString(0)));
                student.setStudent_name(cursor.getString(1));
                student.setStudent_rollno(cursor.getString(2));
                student.setStudent_mobilenumber(cursor.getString(3));
                student.setStudent_address(cursor.getString(4));
                student.setStudent_department(cursor.getString(5));
                student.setStudent_year(cursor.getString(6));
                list.add(student);
            }while(cursor.moveToNext());
        }
        return list;
    }
    public Student_model getStudentById(int studentId)
    {
        Student_model student = new Student_model();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM student_table where student_id="+studentId;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{

                student.setStudent_id(Integer.parseInt(cursor.getString(0)));
                student.setStudent_name(cursor.getString(1));
                student.setStudent_rollno(cursor.getString(2));
                student.setStudent_mobilenumber(cursor.getString(3));
                student.setStudent_address(cursor.getString(4));
                student.setStudent_department(cursor.getString(5));
                student.setStudent_year(cursor.getString(6));

            }while(cursor.moveToNext());
        }
        return student;
    }
    public void deleteStudent(int studentId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM student_table WHERE student_id="+studentId ;

        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }

    //attendance session Table crud
    public int addAttendanceSession(Attendence_session_model attendanceSession) {

           SQLiteDatabase db = this.getWritableDatabase();

           String query = "INSERT INTO attendance_session_table (attendance_session_faculty_id,attendance_session_department,attendance_session_class,attendance_session_date,attendance_session_subject) values ('" +
                   attendanceSession.getAttendance_session_faculty_id() + "', '" +
                   attendanceSession.getAttendance_session_department() + "','" +
                   attendanceSession.getAttendance_session_class() + "', '" +
                   attendanceSession.getAttendance_session_date() + "', '" +
                   attendanceSession.getAttendance_session_subject() + "')";
           Log.d("query", query);
           db.execSQL(query);

           String query1 = "select  max(attendance_session_id) from attendance_session_table";
           Cursor cursor = db.rawQuery(query1, null);

           if (cursor.moveToFirst()) {
               int sessionId = Integer.parseInt(cursor.getString(0));

               return sessionId;
           }

           db.close();

           return 0;

    }
    public ArrayList<Attendence_session_model> getAllAttendanceSession()
    {
        ArrayList<Attendence_session_model> list = new ArrayList<Attendence_session_model>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM attendance_session_table";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                Attendence_session_model attendanceSession = new Attendence_session_model();
                attendanceSession.setAttendance_session_id(Integer.parseInt(cursor.getString(0)));
                attendanceSession.setAttendance_session_faculty_id(Integer.parseInt(cursor.getString(1)));
                attendanceSession.setAttendance_session_department(cursor.getString(2));
                attendanceSession.setAttendance_session_class(cursor.getString(3));
                attendanceSession.setAttendance_session_date(cursor.getString(4));
                attendanceSession.setAttendance_session_subject(cursor.getString(5));
                list.add(attendanceSession);
            }while(cursor.moveToNext());
        }
        return list;
    }
    public void deleteAttendanceSession(int attendanceSessionId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM attendance_session_table WHERE attendance_session_id="+attendanceSessionId ;

        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }

    //attendance crud
    public void addNewAttendance(Attendence_model attendance) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO attendance_table values ("+
                attendance.getAttendance_session_id()+", "+
                attendance.getAttendance_student_id()+", '"+
                attendance.getAttendance_status()+"')";
        Log.d("query", query);
        db.execSQL(query);
        db.close();
    }
    public ArrayList<Attendence_model> getAttendanceBySessionID(Attendence_session_model attendanceSession)
    {
        int attendanceSessionId=0;
        ArrayList<Attendence_model> list = new ArrayList<Attendence_model>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM attendance_session_table where attendance_session_faculty_id="+attendanceSession.getAttendance_session_faculty_id()+""
                +" AND attendance_session_department='"+attendanceSession.getAttendance_session_department()+"' AND attendance_session_class='"+attendanceSession.getAttendance_session_class()+"'" +
                " AND attendance_session_date='"+attendanceSession.getAttendance_session_date()+"' AND attendance_session_subject='"+attendanceSession.getAttendance_session_subject()+"'";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                attendanceSessionId=(Integer.parseInt(cursor.getString(0)));
            }while(cursor.moveToNext());
        }

        String query1="SELECT  * FROM attendance_table where attendance_session_id=" + attendanceSessionId+" order by attendance_student_id";
        Cursor cursor1 = db.rawQuery(query1, null);
        if(cursor1.moveToFirst())
        {
            do{
                Attendence_model attendance = new Attendence_model();
                attendance.setAttendance_session_id(Integer.parseInt(cursor1.getString(0)));
                attendance.setAttendance_student_id(Integer.parseInt(cursor1.getString(1)));
                attendance.setAttendance_status(cursor1.getString(2));
                list.add(attendance);

            }while(cursor1.moveToNext());
        }
        return list;
    }
    public ArrayList<Attendence_model> getTotalAttendanceBySessionID(Attendence_session_model attendanceSession)
    {
        int attendanceSessionId=0;
        ArrayList<Attendence_model> list = new ArrayList<Attendence_model>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM attendance_session_table where attendance_session_faculty_id="+attendanceSession.getAttendance_session_faculty_id()+""
                +" AND attendance_session_department='"+attendanceSession.getAttendance_session_department()+"' AND attendance_session_class='"+attendanceSession.getAttendance_session_class()+"'" +
                " AND attendance_session_subject='"+attendanceSession.getAttendance_session_subject()+"'";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do{
                attendanceSessionId=(Integer.parseInt(cursor.getString(0)));

                String query1="SELECT * FROM attendance_table where attendance_session_id=" + attendanceSessionId+" order by attendance_student_id";
                Cursor cursor1 = db.rawQuery(query1, null);
                if(cursor1.moveToFirst())
                {
                    do{
                        Attendence_model attendance = new Attendence_model();
                        attendance.setAttendance_session_id(Integer.parseInt(cursor1.getString(0)));
                        attendance.setAttendance_student_id(Integer.parseInt(cursor1.getString(1)));
                        attendance.setAttendance_status(cursor1.getString(2));
                        list.add(attendance);

                    }while(cursor1.moveToNext());
                }

                Attendence_model attendance = new Attendence_model();
                attendance.setAttendance_session_id(0);
                attendance.setAttendance_status("Date : " + cursor.getString(4));
                list.add(attendance);

            }while(cursor.moveToNext());
        }


        return list;
    }
    public ArrayList<Attendence_model> getAllAttendanceByStudent()
    {
        ArrayList<Attendence_model> list = new ArrayList<Attendence_model>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  attendance_student_id,count(*) FROM attendance_table where attendance_status='P' group by attendance_student_id";

        Log.d("query", query);

        Cursor cursor = db.rawQuery(query, null);



        if(cursor.moveToFirst())
        {
            do{
                Log.d("studentId","studentId:"+cursor.getString(0)+", Count:"+cursor.getString(1));
                Attendence_model attendance = new Attendence_model();
                attendance.setAttendance_student_id(Integer.parseInt(cursor.getString(0)));
                attendance.setAttendance_session_id(Integer.parseInt(cursor.getString(1)));
                list.add(attendance);

            }while(cursor.moveToNext());
        }
        return list;
    }


}
