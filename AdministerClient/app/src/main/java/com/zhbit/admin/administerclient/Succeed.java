package com.zhbit.admin.administerclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/5/17 0017.
 */

public class Succeed extends AppCompatActivity {
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