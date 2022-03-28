package com.example.androidnangcao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.androidnangcao.R;
import com.example.androidnangcao.dangki.ActivityStudent;

import com.example.androidnangcao.model.Student;

import java.util.ArrayList;



public class  adapterstudent  extends BaseAdapter {
    ImageButton imgupdate,imginformation,imgdele;
    private ActivityStudent context;
    private ArrayList<Student> ArraylistStudent;

    public adapterstudent(ActivityStudent context, ArrayList<Student> arraylistStudent) {
        this.context = context;
        ArraylistStudent = arraylistStudent;
    }

    @Override
    public int getCount() {
        return ArraylistStudent.size();
    }

    @Override
    public Object getItem(int i) {
        return ArraylistStudent.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.liststudent,null);

        TextView txtName = view.findViewById(R.id.tvstudentName);
        TextView txtcode = view.findViewById(R.id.tvcode);


        imgdele = view.findViewById(R.id.studentdelete);

         imginformation = view.findViewById(R.id.studentinformation);
        imgupdate = view .findViewById( R.id.studentupdate);

        Student student = ArraylistStudent.get(i);

        txtName.setText(student.getStudent_name());
        txtcode.setText(student.getStudent_code());

        int id = student.getId_student();

        imgdele.setOnClickListener(v -> context.delete(id));

        imgupdate.setOnClickListener(v -> context.update(id));

        imginformation.setOnClickListener(v -> context.information(id));
        return view;
    }

}
