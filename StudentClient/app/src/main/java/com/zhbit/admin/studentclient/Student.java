package com.zhbit.admin.studentclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/4/29 0029.
 */

public class Student extends AppCompatActivity{
    private String S_Num;
    private String welcome;

    private TextView WelcomeText;
    private ImageView ReservationBt;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students);

        WelcomeText=(TextView)findViewById(R.id.welcomeText);
        ReservationBt=(ImageView)findViewById(R.id.ReservationBt);

        //取得启动该Activity的Intent对象
        Intent intent =getIntent();
        S_Num=intent.getStringExtra("S_Num");
        welcome="欢迎"+S_Num+"!";
        WelcomeText.setText(welcome);

        ReservationBt.setOnClickListener(new View.OnClickListener() {//跳转预约界面
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(Student.this,Reservation.class);
                intent.putExtra("S_Num",S_Num);//
                startActivity(intent);
            }
        });
    }
}
