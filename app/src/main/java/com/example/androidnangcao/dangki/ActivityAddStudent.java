package com.example.androidnangcao.dangki;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.androidnangcao.R;
import com.example.androidnangcao.database.database;
import com.example.androidnangcao.dangki.ActivityStudent;
import com.example.androidnangcao.model.Student;

// thêm mới Đăng kí khóa học
public class ActivityAddStudent extends AppCompatActivity {
    Button buttonAddstudent;
    EditText editextStudentName,editextstudentcode,edittextdateofbirth;
    RadioButton radioButtonMale,RadioButtonFeMale;
    database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        buttonAddstudent = findViewById(R.id.buttonAđdStudent);
        edittextdateofbirth = findViewById(R.id.EditTextStudentBirthday);
        editextstudentcode = findViewById(R.id.EditTextStudentCode);
        editextStudentName = findViewById(R.id.EditTextStudentName);

        radioButtonMale = findViewById(R.id.radiobuttonMale);
        RadioButtonFeMale=findViewById(R.id.radiobuttonFeMale);

        Intent intent = getIntent();
        int id_subject  = intent.getIntExtra("id_subject",0);

        database = new database(this);
        editextStudentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editextStudentName.setText("");
            }
        });
        editextstudentcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editextstudentcode.setText("");
            }
        });
        edittextdateofbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittextdateofbirth.setText("");
            }
        });
        buttonAddstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd(id_subject);
            }
        });
    }

    private void DialogAdd(int id_subject) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogaddstudent);
        dialog.setCanceledOnTouchOutside(false);

        Button btnyes=dialog.findViewById(R.id.buttonYesstudent);
        Button btnno = dialog.findViewById(R.id.buttonNostudent);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editextStudentName.getText().toString().trim();
                String code = editextstudentcode.getText().toString().trim();
                String birtday = edittextdateofbirth.getText().toString().trim();
                String sex = "";
                if (radioButtonMale.isChecked()){
                    sex="Nam";
                }else if (RadioButtonFeMale.isChecked()){
                    sex="Nữ";
                }
                if (name.equals("")|| code.equals("")||birtday.equals("")||sex.equals("")){
                    Toast.makeText(ActivityAddStudent.this,"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }else {
                    Student student = Createstudent(id_subject);
                    database.Addstudent(student);

                    Intent intent= new Intent(ActivityAddStudent.this,ActivityStudent.class);
                    intent.putExtra("id_subject",id_subject);
                    startActivity(intent);
                    dialog.show();
                    Toast.makeText(ActivityAddStudent.this,"Lưu thanh cong ",Toast.LENGTH_SHORT).show();

                }

            }
        });
        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    private Student Createstudent(int id_subject){
        String name = editextStudentName.getText().toString().trim();
        String code = editextstudentcode.getText().toString().trim();
        String birtday = edittextdateofbirth.getText().toString().trim();
        String sex = "";
        if (radioButtonMale.isChecked()){
            sex="Nam";
        }else if (RadioButtonFeMale.isChecked()){
            sex="Nữ";
        }
        Student student = new Student(name,sex,code,birtday,id_subject);
        return student;
    }
}