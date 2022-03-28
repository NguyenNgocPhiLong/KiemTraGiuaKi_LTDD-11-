package com.example.androidnangcao.lophoc;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidnangcao.MainActivity;
import com.example.androidnangcao.R;
import com.example.androidnangcao.database.database;
import com.example.androidnangcao.dangki.ActivityStudent;
//import com.example.androidnangcao.lophoc.ActivitySubject;
import com.example.androidnangcao.model.Subject;

// cập nhật khóa học
public class Activityupdate extends AppCompatActivity {
    EditText edTitle,edCreadit,edTime,edPlace;
    Button btnupdate;
    com.example.androidnangcao.database.database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_activityupdate);

        edTitle=findViewById(R.id.ed1);
        edCreadit=findViewById(R.id.ed2);
        edTime=findViewById(R.id.ed3);
        edPlace= findViewById(R.id.ed4);
        btnupdate = findViewById(R.id.btupdate);

        //lay du lieuu
        Intent intent = getIntent();

        int id = intent.getIntExtra("id",0);
        String title = intent.getStringExtra("title");
        int credit = intent.getIntExtra("credit",0);
        String time = intent.getStringExtra("time");
        String place = intent.getStringExtra("place");

        edTitle.setText(title);
        edTime.setText(time);
        edPlace.setText(place);
        edCreadit.setText(credit+"");

        database = new database(this);
        //lay du lieuu


        // riset form nhan 2 lan click EditText
        edTitle.setOnClickListener(v -> edTitle.setText(""));
        edTime.setOnClickListener(v -> edTime.setText(""));
        edPlace.setOnClickListener(v -> edPlace.setText(""));
        btnupdate.setOnClickListener(v -> Dialogupdate(id));
        edCreadit.setOnClickListener(v -> edCreadit.setText(""));
        // riset form nhan 2 lan click EditText

    }

    private void Dialogupdate(int id) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogupdate);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes= dialog.findViewById(R.id.buttonYesupdate);
        Button btnNo= dialog.findViewById(R.id.buttonNoupdate);

        btnYes.setOnClickListener(v -> {
            String  subjecttitle = edTitle.getText().toString().trim();
            String credit = edCreadit.getText().toString().trim();
            String time = edTime.getText().toString().trim();
            String place = edPlace.getText().toString().trim();

            // kiểm tra form có rổng hay không || hay còn gọi là testkey
            if (subjecttitle.equals("")||credit.equals("")||time.equals("")||place.equals("")){
                Toast.makeText(Activityupdate.this,"Nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            }else {
                Subject subject = updatesubject();

                database.UpdateSubject(subject,id);
                    // nếu form không rổng thì cập nhật thành công
                Intent intent = new Intent(Activityupdate.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(Activityupdate.this,"Sửa Thành Công",Toast.LENGTH_SHORT).show();

            }
        });

        btnNo.setOnClickListener(v -> dialog.cancel());


        // dialog.show(); hiện thị thông báo  theo dạng xml
        dialog.show();
    }


    // luu du lieu edit cap nhat
    private Subject updatesubject(){
        String  subjecttitle = edTitle.getText().toString().trim();
        int credit =Integer.parseInt(edCreadit.getText().toString().trim());
        String time = edTime.getText().toString().trim();
        String place = edPlace.getText().toString().trim();

        Subject subject = new Subject(subjecttitle,credit,time,place);
        return subject;
    }
}