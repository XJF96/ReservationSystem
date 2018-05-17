package com.zhbit.admin.studentclient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 * @date 2018/5/5 0005
 */

public class PersonalCenter extends AppCompatActivity {
    private TextView S_NumTv;
    private TextView S_NameTv;
    private TextView S_AgeTv;
    private TextView S_SexTv;
    private TextView S_ClassTv;
    private TextView S_DepartmentTv;
    private TextView S_PhoneTv;
    private Button changePwdBt;

    private String result;
    private String personalCenterS_Num;

    private String[] myPersonal=new String[6];
    private ArrayList list;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_personalcenter);

        initTv();

        Bundle bundle=this.getIntent().getExtras();
        personalCenterS_Num=bundle.getString("personalCenterS_Num");

        S_NumTv.setText(personalCenterS_Num);
        S_NameTv.setText("张三");
        S_AgeTv.setText("19");
        S_SexTv.setText("男");
        S_ClassTv.setText("计科1班");
        S_DepartmentTv.setText("计算机系");
        S_PhoneTv.setText("13445679878");

        initBtClick();

        PersonalCenter.QueryAddressTask queryAddressTask = new PersonalCenter.QueryAddressTask();
        //启动后台任务
        queryAddressTask.execute(personalCenterS_Num);
    }

    private void initTv(){
        S_NumTv=(TextView)findViewById(R.id.PersonalS_NumTv);
        S_NameTv=(TextView)findViewById(R.id.PersonalS_NameTv);
        S_AgeTv=(TextView)findViewById(R.id.PersonalS_AgeTv);
        S_SexTv=(TextView)findViewById(R.id.PersonalS_SexTv);
        S_ClassTv=(TextView)findViewById(R.id.PersonalS_ClassTv);
        S_DepartmentTv=(TextView)findViewById(R.id.PersonalS_DepartmentTv);
        S_PhoneTv=(TextView)findViewById(R.id.PersonalS_PhoneTv);
        changePwdBt=(Button)findViewById(R.id.changePwdBt);
    }
    private void initBtClick(){
        changePwdBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PersonalCenter.this,"待更改密码",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getRemoteInfo(String phoneSec) throws Exception{
        //String WSDL_URI = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?WSDL";//wsdl 的uri
        String WSDL_URI = "http://192.168.191.1:8086/Service1.asmx?WSDL";

        String namespace = "http://tempuri.org/";
        String methodName = "selectStu";

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId

        request.addProperty("StuNO", phoneSec);
        //request.addProperty("userId", "");

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        //由于是发送请求，所以是设置bodyOut
        envelope.bodyOut = request;
        //由于是.net开发的webservice，所以这里要设置为true
        envelope.dotNet = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        httpTransportSE.call(namespace+methodName, envelope);

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
        // 获取第一个返回的结果
        result = object.getProperty(0).toString();

        Log.d("debug",result);

        return result;
    }

    class QueryAddressTask extends AsyncTask<String, Integer, String> {
        @Override
        protected  String doInBackground(String... params) {//后台
            // 查询信息*/
            try {
                result = getRemoteInfo(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //将结果返回给onPostExecute方法
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            // 将WebService返回的结果显示在TextView中

            //S_NumTv.setText(result);
        }
    }
}
