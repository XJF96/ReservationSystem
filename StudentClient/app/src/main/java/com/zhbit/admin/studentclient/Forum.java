package com.zhbit.admin.studentclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2018/5/5 0005.
 */

public class Forum extends AppCompatActivity{
    //private TextView tvsucceed;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_forum);

        initTv();
    }

    private void initTv(){
        //tvsucceed=(TextView)findViewById(R.id.tvsucceed);
    }
}
