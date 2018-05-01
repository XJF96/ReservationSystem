package com.zhbit.admin.studentclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;

/**
 * Created by Administrator on 2018/4/29 0029.
 */

public class Reservation extends AppCompatActivity {
    private Spinner place;
//    private List<String> data_list;
//    private ArrayAdapter<String> arr_adapter;

//    Intent intent_getS_Num=getIntent();
//    private String S_Num=intent_getS_Num.getStringExtra("S_Num");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_reservation);

//        place=(Spinner)findViewById(R.id.place);
//
//        //数据
//        data_list = new ArrayList<String>();
//        data_list.add("弘毅楼");
//        data_list.add("明德楼");
//        data_list.add("天佑楼");
//        data_list.add("艺悦楼");
//        data_list.add("知行楼");
//        data_list.add("求是楼");
//        data_list.add("精工楼");
//
//        //适配器
//        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
//        //设置样式
//        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //加载适配器
//        place.setAdapter(arr_adapter);
   }
}
