package com.example.androidnangcao.dangki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.androidnangcao.R;

// xem chi tiết Đăng kí khóa học
public class ActivityInformationStudent extends AppCompatActivity {
    TextView txtName,txtSex,txtCode,txtBirthday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_student);
        // ánh xá xml
        txtBirthday = findViewById(R.id.txtStudentdateofbirht);
        txtCode = findViewById(R.id.txtStudentCode);
        txtSex = findViewById(R.id.txtStudentSex);
        txtName = findViewById(R.id.txtStudentName);
        Intent intent  = getIntent();

        String name =intent.getStringExtra("name");
        String sex =intent.getStringExtra("sex");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birtday");

        txtName.setText(name);
        txtSex.setText(sex);
        txtCode.setText(code);
        txtBirthday.setText(birthday);
    }
}