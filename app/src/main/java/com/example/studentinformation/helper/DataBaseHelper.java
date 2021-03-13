package com.example.studentinformation.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.studentinformation.model.StudentData;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDbName.db";
    public static final String CONTACTS_TABLE_NAME = "students";
    public static final String CONTACT_COLUMN_ID = "id";
    public static final String CONTACT_COLUMN_NAME = "name";
    public static final String CONTACT_COLUMN_ROLL = "roll";
    public static final String CONTACT_COLUMN_ADDRESS = "address";
    private static final String TAG = "DataBaseHelper";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table students " + "(id integer primary key, name, roll, address text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS students");
        onCreate(db);
    }

    public boolean insertContact(String name, String roll, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CONTACT_COLUMN_NAME, name);
        cv.put(CONTACT_COLUMN_ROLL, roll);
        cv.put(CONTACT_COLUMN_ADDRESS, address);
        long result = db.insert(CONTACTS_TABLE_NAME, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<StudentData> getAllStudents() {
        ArrayList<StudentData> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "SELECT * FROM students ORDER BY CAST (roll AS INTEGER) ASC";
        Cursor cursor = db.rawQuery(selection, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndex(CONTACT_COLUMN_NAME));
                String roll = cursor.getString(cursor.getColumnIndex(CONTACT_COLUMN_ROLL));
                String address = cursor.getString(cursor.getColumnIndex(CONTACT_COLUMN_ADDRESS));
                data.add(new StudentData(name, roll, address));
                Log.e("===", "Data is :" + data);
                Log.e("===", "Data size is :" + data.size());
                cursor.moveToNext();
            }
        }
        return data;
    }

    public boolean hasObject(String roll) {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + CONTACTS_TABLE_NAME + " WHERE " + CONTACT_COLUMN_ROLL + " =?";
        Cursor cursor = db.rawQuery(selectString, new String[]{roll});
        boolean hasObject = false;
        if (cursor.moveToFirst()) {
            hasObject = true;
            int count = 0;
            while (cursor.moveToNext()) {
                count++;
            }
            Log.d(TAG, String.format("%d records found", count));
        }
        cursor.close();
        db.close();
        return hasObject;
    }
}
