package com.zhbit.admin.administerclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 *
 * @author Administrator
 * @date 2018/5/17 0017
 */

public class Help extends AppCompatActivity {
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