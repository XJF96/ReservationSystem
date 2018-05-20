package com.zhbit.admin.administerclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/17 0017.
 */

public class Reservation extends AppCompatActivity {
    //    private Spinner time;
    private Spinner place;
    private Spinner function;

//    private String S_Num;

    private List<String> placeList=null;
    private List<String> FunctionList=null;

    private String[] S_place = {"弘毅楼", "明德楼", "天佑楼", "艺悦楼", "知行楼", "求是楼", "精工楼"};
    private String[] S_function = {"物理", "化学", "上机", "天文", "沙盘", "会议", "艺术"};

    private ArrayAdapter S_place_adapter;
    private ArrayAdapter S_function_adapter;

//    Intent intent_getS_Num=getIntent();
//    private String S_Num=intent_getS_Num.getStringExtra("S_Num");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_reservation);

        initComponent();
        setData();

//        //适配器
        S_place_adapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item, placeList);
//        //设置样式
        S_place_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //加载适配器
        place.setAdapter(S_place_adapter);

        S_function_adapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item, FunctionList);
//        //设置样式
        S_function_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //加载适配器
        function.setAdapter(S_function_adapter);

    }

    private void initComponent(){
        placeList = new ArrayList<>();
        FunctionList = new ArrayList<>();

//        time=(Spinner)findViewById(R.id.time);
        place=(Spinner)findViewById(R.id.place);
        function=(Spinner)findViewById(R.id.function);
    }

    private void setData() {
        for (int i = 0; i < S_place.length; i++) {
            placeList.add(S_place[i]);
        }
        for (int i = 0; i < S_function.length; i++) {
            FunctionList.add(S_function[i]);
        }
    }


    public void onItemSelectedPlace(AdapterView<?> parent, View view, int position, long id) {
        String placeName = (String) S_place_adapter.getItem(position);
//        tv.setText(cityName);
    }
    public void onNothingSelectedPlace(AdapterView<?> parent) {

    }

    public void onItemSelectedFunction(AdapterView<?> parent, View view, int position, long id) {
        String placeName = (String) S_function_adapter.getItem(position);
//        tv.setText(cityName);
    }
    public void onNothingSelectedFunction(AdapterView<?> parent) {

    }

}

