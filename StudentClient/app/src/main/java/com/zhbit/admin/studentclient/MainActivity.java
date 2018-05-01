package com.zhbit.admin.studentclient;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity  {
    private EditText etProductName;
    private TextView tvResult;
    private Button btnSearch;
    private String result="";

    private EditText NameNumberText;
    private EditText PasswordText;
    private Button LoginButton;
    private  Button RegistrationButton;

    // WSDL文档的URL，192.168.17.156为PC的ID地址    http://localhost:54032/Service1.asmx?wsdlx
    //http://192.168.191.1:8086/
    //http://192.168.191.1:8086/Service1.asmx
//    String namespace="http://tempuri.org/";
//    String serviceUrl = "http://192.168.191.1:8086/Service1.asmx?wsdl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etProductName = (EditText) findViewById(R.id.etProductName);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//点击事件
                String phoneSec=etProductName.getText().toString().trim();
                if("".equals(phoneSec)){//||phoneSec.length()<7
                    etProductName.setError("您输入的号码查询有误！");
                    etProductName.requestFocus();
                    etProductName.setText("");
                    return;
                }

                //启动后台异步线程进行连接webService操作，并且根据返回结果在主线程中改变UI
                QueryAddressTask queryAddressTask = new QueryAddressTask();
                //启动后台任务
                queryAddressTask.execute(phoneSec);
            }
        });

        NameNumberText=(EditText) findViewById(R.id.NameNumberText);
        PasswordText=(EditText) findViewById(R.id.PasswordText);
        LoginButton = (Button) findViewById(R.id.LoginButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameNum=NameNumberText.getText().toString().trim();
                if("".equals(nameNum)){
                    NameNumberText.setError("您输入的账号为空");
                    NameNumberText.requestFocus();//获取焦点，就是输入光标
                    NameNumberText.setText("");
                    return;
                }

                QueryAddressTask queryAddressTask = new QueryAddressTask();
                //启动后台任务
                queryAddressTask.execute(nameNum);

                //加入检测结果返回
                if("".equals(result)){
                    //加载
                    Toast.makeText(MainActivity.this,"等待密码校验",Toast.LENGTH_SHORT).show();
                }
                if(PasswordText.getText().toString().trim().equals(result)){
                    //跳转页面
                    Intent intent=new Intent();
                    intent.setClass(MainActivity.this,Student.class);
                    intent.putExtra("S_Num",nameNum);
                    startActivity(intent);
                    //Toast.makeText(MainActivity.this,"等待跳入界面",Toast.LENGTH_SHORT).show();
                    //result="";
                }
                else{
                    Toast.makeText(MainActivity.this,"账号或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        });

        RegistrationButton = (Button) findViewById(R.id.RegistrationButton);


    }
    public String getRemoteInfo(String phoneSec) throws Exception{
        //String WSDL_URI = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?WSDL";//wsdl 的uri
        String WSDL_URI = "http://192.168.191.1:8086/Service1.asmx?WSDL";

        String namespace = "http://tempuri.org/";//namespace
        String methodName = "selectAdminPassword";//要调用的方法名称

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId

        request.addProperty("mgNo", phoneSec);//mobileCode
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


    class QueryAddressTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {//后台
            // 查询手机号码（段）信息*/
            try {
                result = getRemoteInfo(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //将结果返回给onPostExecute方法
            return result;
        }

        @Override
        //此方法可以在主线程改变UI
        protected void onPostExecute(String result) {
            // 将WebService返回的结果显示在TextView中
            tvResult.setText(result);
        }
    }
}
