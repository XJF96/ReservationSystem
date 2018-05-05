package com.zhbit.admin.studentclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Administrator on 2018/5/4 0004.
 */

public class Register extends AppCompatActivity{
    private EditText etNum;
    private EditText etpsw;
    private EditText etagpsw;
    private EditText etphone;
    private EditText etname;
    private Button btn_register_enter;

    private String snum;
    private String psw;
    private String agpsw;
    private String phone;
    private String name;

    private String result;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initRegTv();//初始化组件

        snum=etNum.getText().toString().trim();
        psw=etpsw.getText().toString().trim();
        agpsw=etagpsw.getText().toString().trim();
        phone=etphone.getText().toString().trim();
        name=etname.getText().toString().trim();
        System.out.print("信息："+snum+psw+agpsw+phone+name);

        btn_register_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Register.this,"注册成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRegTv(){
        etNum=(EditText)findViewById(R.id.S_Num);
        etpsw=(EditText)findViewById(R.id.S_psw);
        etagpsw=(EditText)findViewById(R.id.agS_psw);
        etphone=(EditText)findViewById(R.id.phone);
        etname=(EditText)findViewById(R.id.name);
        btn_register_enter=(Button) findViewById(R.id.btn_register_enter);
    }

    public String getRemoteInfo(String phoneSec) throws Exception{
        //String WSDL_URI = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?WSDL";//wsdl 的uri
        String WSDL_URI = "http://192.168.191.1:8086/Service1.asmx?WSDL";

        String namespace = "http://tempuri.org/";//namespace
        String methodName = "selectPwd";//要调用的方法名称selectAdminPassword

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId

        request.addProperty("S_Num", phoneSec);//mobileCode  mgNo
        //request.addProperty("userId", "");

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
        envelope.dotNet = true;//由于是.net开发的webservice，所以这里要设置为true

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        httpTransportSE.call(namespace+methodName, envelope);//调用

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
        // 获取返回的结果
        result = object.getProperty(0).toString();

        Log.d("debug",result);
        return result;
    }
}
