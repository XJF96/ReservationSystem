package com.zhbit.admin.studentclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2018/5/5 0005.
 */

public class PersonalCenter extends AppCompatActivity {
    //private TextView tvsucceed;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_personalcenter);

        initTv();
    }

    private void initTv(){
        //tvsucceed=(TextView)findViewById(R.id.tvsucceed);
    }
}
