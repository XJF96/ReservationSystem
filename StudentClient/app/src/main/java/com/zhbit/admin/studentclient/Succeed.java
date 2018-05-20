package com.zhbit.admin.studentclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/5/5 0005.
 */

public class Succeed extends AppCompatActivity{
    private TextView tvsucceed;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_succeed);

        initTv();
    }

    private void initTv(){
        tvsucceed=(TextView)findViewById(R.id.tvsucceed);
    }
}
