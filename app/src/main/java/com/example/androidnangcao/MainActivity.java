package com.example.androidnangcao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidnangcao.adapter.adaptersubject;
import com.example.androidnangcao.dangki.ActivityStudent;
import com.example.androidnangcao.database.database;
import com.example.androidnangcao.lophoc.ActivityAddSubject;
import com.example.androidnangcao.lophoc.ActivitySubjectlnformation;
import com.example.androidnangcao.lophoc.Activityupdate;
import com.example.androidnangcao.model.Subject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    ListView listViewsubject;
    ArrayList<Subject> ArrayListSubject;
    com.example.androidnangcao.database.database database;
    com.example.androidnangcao.adapter.adaptersubject adaptersubject;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // ánh xạ toolbar và listview
        // maping xml
        toolbar = findViewById(R.id.toolbarSubject);
        listViewsubject =  findViewById(R.id.listviewSubject);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);

        ArrayListSubject = new ArrayList<>();

        //Cursor : con trỏ chuọt
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()){
            int id =cursor.getInt(0);
            String title = cursor.getString(1);
            int credit =  cursor.getInt(2);
            String time = cursor.getString(3);
            String place = cursor.getString(4);

            ArrayListSubject.add(new Subject(id,title,credit,time,place));
        }
        adaptersubject = new adaptersubject(MainActivity.this,ArrayListSubject);
        listViewsubject.setAdapter(adaptersubject);

        cursor.moveToFirst();
        cursor.close();
        listViewsubject.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(MainActivity.this, ActivityStudent.class);
            int id_subject = ArrayListSubject.get(i).getId();
            intent.putExtra("id_subject",id_subject);
            startActivity(intent);
            return;

        });
    }
    // back
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuadd:
                Intent intent = new Intent(MainActivity.this, ActivityAddSubject.class);
                startActivity(intent);
                break;
            default:
                Intent intent1 = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    // phuong thu xem chi tiet
    public void information(final int pos){
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            if (id==pos){
                Intent intent = new Intent(MainActivity.this, ActivitySubjectlnformation.class);
                // lấy tham số
                intent.putExtra("id",pos);
                String title =cursor.getString(1);
                int credit =cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);
                //gui du lieu qua activity update
                intent.putExtra("title",title);
                intent.putExtra("credit",credit);
                intent.putExtra("time",time);
                intent.putExtra("place",place);

                startActivity(intent);
            }
        }
        cursor.close();
    }
    // phuong thu xem chi tiet

    // thông báo
    //phuong thuc xoa
    public void delete(final int position){
        Dialog dialog = new Dialog(this);
        //nạp layout
        dialog.setContentView(R.layout.dialogdelete);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.StudentYesdele);
        Button btnNo = dialog.findViewById(R.id.StudentNodele);
        //(V->) lamdam java8
        btnYes.setOnClickListener(v -> {
            database = new database(MainActivity.this);

            database.DeleteSubject(position);
            //xoa student
            database.DeleteSubjectStuden(position);
            // sau khi xóa nó sẻ quay lại màn hình các khóa học
            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            // Toast.maketext(...) hiện thi thông báo
            Toast.makeText(getApplicationContext(),"Xóa Thành Công",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });

        btnNo.setOnClickListener(v -> dialog.cancel());
        dialog.show();
    }
    //phuong thuc xoa

    //  phuong thuc cap nhat
    public void update(final int pos){
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            if (id == pos){
                Intent intent = new Intent(MainActivity.this, Activityupdate.class);

                String title=cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                //gui du lieu qua activity update
                intent.putExtra("id",id);
                intent.putExtra("title",title);
                intent.putExtra("credit",credit);
                intent.putExtra("time",time);
                intent.putExtra("place",place);

                startActivity(intent);
            }
        }
    }
}
