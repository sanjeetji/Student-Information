package com.example.studentinformation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentinformation.adpater.StudentAdapter;
import com.example.studentinformation.helper.DataBaseHelper;
import com.example.studentinformation.model.StudentData;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StudentAdapter studentAdapter;
    ArrayList<StudentData> studentDataArrayList;
    EditText editName, editRoll, editAddress;
    String name, roll, address;
    DataBaseHelper dataBaseHelper;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        dataBaseHelper = new DataBaseHelper(this);
        initView();
    }

    private void initView() {
        editName = findViewById(R.id.name);
        editRoll = findViewById(R.id.roll);
        editAddress = findViewById(R.id.address);
        recyclerView = findViewById(R.id.recycler);
        button = findViewById(R.id.submitStu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editName.getText().toString().trim();
                roll = editRoll.getText().toString().trim();
                address = editAddress.getText().toString().trim();
                if (name.equals("")) {
                    Toast.makeText(StudentActivity.this, "Name is Mandatory !", Toast.LENGTH_SHORT).show();
                } else if (roll.equals("")) {
                    Toast.makeText(StudentActivity.this, "Roll no is Mandatory !", Toast.LENGTH_SHORT).show();
                } else if (roll.equals("0")) {
                    Toast.makeText(StudentActivity.this, "Roll No can't be Zero !", Toast.LENGTH_SHORT).show();
                } else if (address.equals("")) {
                    Toast.makeText(StudentActivity.this, "Address is Mandatory !", Toast.LENGTH_SHORT).show();
                } else {
                    boolean r = dataBaseHelper.hasObject(roll);
                    if (r == true) {
                        Toast.makeText(StudentActivity.this, "Record is Already Exists !", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean isInserted = dataBaseHelper.insertContact(name, roll, address);
                        if (isInserted == true) {
                            Toast.makeText(StudentActivity.this, "Data is Inserted !", Toast.LENGTH_SHORT).show();
                            getData();
                        } else {
                            Toast.makeText(StudentActivity.this, "Data is not Inserted !", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });
    }

    private void getData() {
        ArrayList<StudentData> studentData = new ArrayList<>();
        studentData = dataBaseHelper.getAllStudents();
        Log.e("", "Data is :" + studentData);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        studentDataArrayList = new ArrayList<>();
        studentAdapter = new StudentAdapter(studentData, this);
        recyclerView.setAdapter(studentAdapter);
    }
}