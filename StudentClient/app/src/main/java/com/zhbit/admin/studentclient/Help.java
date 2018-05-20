package com.zhbit.admin.studentclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class Help extends AppCompatActivity{
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_help);

        initTv();
    }

    private void initTv(){
        tv=(TextView)findViewById(R.id.tv);
    }
}
