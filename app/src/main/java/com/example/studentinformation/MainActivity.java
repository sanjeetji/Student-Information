package com.example.studentinformation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button submit, goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.stringEdit);
        submit = findViewById(R.id.submit);
        goButton = findViewById(R.id.goBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();
                reverseString(str);
            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, StudentActivity.class);
                startActivity(i);
            }
        });
    }

    private void reverseString(String str) {
        String revrseString = null;
        char[] chars = str.toCharArray();
        char[] reversedChar = new char[chars.length];
        reversedChar[reversedChar.length - 1] = chars[0];
        int r = reversedChar.length - 2;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != chars[i - 1]) {
                reversedChar[r] = chars[i];
                r--;
            }
        }
        revrseString = new String(Arrays.copyOfRange(reversedChar, r + 1, reversedChar.length));
        Toast.makeText(this, revrseString, Toast.LENGTH_SHORT).show();
    }
}