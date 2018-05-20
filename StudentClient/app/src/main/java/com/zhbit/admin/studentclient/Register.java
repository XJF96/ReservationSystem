package com.zhbit.admin.studentclient;

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

/**
 * Created by Administrator on 2018/5/4 0004.
 */

public class Register extends AppCompatActivity{
    private EditText etregisternum;
    private EditText etregisterpsw;
    private EditText etregisteragpsw;
    private EditText etregisterphone;
    private EditText etregistername;
    private EditText etregisterage;
    private EditText etregistersex;
    private EditText etregisterclass;
    private EditText etregisterdepartment;

    private Button btn_register_enter;
    private TextView registerresulttv;//显示服务器返回状态

    private String registersnum;
    private String registerpsw;
    private String registeragpsw;
    private String registerphone;
    private String registername;
    private String registerage;
    private String registersex;
    private String registersclass;
    private String registerdepartment;

    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initRegTv();//初始化组件



        System.out.print("信息："+registersnum+registerpsw+registeragpsw+registerphone+registername+registerage+registersex+registersclass+registerdepartment);

        btn_register_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registersnum=etregisternum.getText().toString().trim();
                registerpsw=etregisterpsw.getText().toString().trim();
                registeragpsw=etregisteragpsw.getText().toString().trim();
                registerphone=etregisterphone.getText().toString().trim();
                registername=etregistername.getText().toString().trim();
                registerage=etregisterage.getText().toString().trim();
                registersex=etregistersex.getText().toString().trim();
                registersclass=etregisterclass.getText().toString().trim();
                registerdepartment=etregisterdepartment.getText().toString().trim();

                if(registersnum.equals("")){
                    etregisternum.setError("您输入的账号为空");
                    etregisternum.requestFocus();//获取焦点，就是输入光标
                    etregisternum.setText("");
                    return;
                }
                if("".equals(registerpsw)){
                    etregisterpsw.setError("您输入的密码为空");
                    etregisterpsw.requestFocus();//获取焦点，就是输入光标
                    etregisterpsw.setText("");
                    return;
                }
                if("".equals(registeragpsw)){
                    etregisteragpsw.setError("您输入的密码错误");
                    etregisteragpsw.requestFocus();//获取焦点，就是输入光标
                    etregisteragpsw.setText("");
                    return;
                }
                if("".equals(registerphone)){
                    etregisterphone.setError("您输入的号码错误");
                    etregisterphone.requestFocus();//获取焦点，就是输入光标
                    etregisterphone.setText("");
                    return;
                }
                if("".equals(registername)){
                    etregistername.setError("您输入的姓名为空");
                    etregistername.requestFocus();//获取焦点，就是输入光标
                    etregistername.setText("");
                    return;
                }
                if("".equals(registerage)){
                    etregisterage.setError("您输入的年龄为空");
                    etregisterage.requestFocus();//获取焦点，就是输入光标
                    etregisterage.setText("");
                    return;
                }
                if("".equals(registersex)){
                    etregistersex.setError("您输入的性别为空");
                    etregistersex.requestFocus();//获取焦点，就是输入光标
                    etregistersex.setText("");
                    return;
                }
                if("".equals(registersclass)){
                    etregisterclass.setError("您输入的班级为空");
                    etregisterclass.requestFocus();//获取焦点，就是输入光标
                    etregisterclass.setText("");
                    return;
                }
                if("".equals(registerdepartment)){
                    etregisterdepartment.setError("您输入的院系为空");
                    etregisterdepartment.requestFocus();//获取焦点，就是输入光标
                    etregisterdepartment.setText("");
                    return;
                }

                if(registerpsw.equals(registeragpsw)){
                    Register.QueryAddressTask queryAddressTask = new Register.QueryAddressTask();
                    //启动后台任务
                    queryAddressTask.execute(registersnum,registername,registerage,registersex,registersclass,registerdepartment,registerphone,registeragpsw);
                }else{
                    Toast.makeText(Register.this,"两次输入密码不正确，请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initRegTv(){
        etregisternum=(EditText)findViewById(R.id.registerS_NumEt);
        etregisterpsw=(EditText)findViewById(R.id.registerS_pwdEt);
        etregisteragpsw=(EditText)findViewById(R.id.registeragS_pwdEt);
        etregisterphone=(EditText)findViewById(R.id.registerS_PhoneEt);
        etregistername=(EditText)findViewById(R.id.registerS_NameEt);
        etregisterage=(EditText)findViewById(R.id.registerS_AgeEt);
        etregistersex=(EditText)findViewById(R.id.registerS_SexEt);
        etregisterclass=(EditText)findViewById(R.id.registerS_ClassEt);
        etregisterdepartment=(EditText)findViewById(R.id.registerS_DepartmentEt);

        btn_register_enter=(Button) findViewById(R.id.btn_register_enter);
        registerresulttv=(TextView)findViewById(R.id.RegisterResult);
    }

    public String getRemoteInfo(String phoneSec,String name,String age,String sex,String sclass,String department,String phone,String agpsw) throws Exception{
        //String WSDL_URI = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?WSDL";//wsdl 的uri
        String WSDL_URI = "http://192.168.191.1:8086/Service1.asmx?WSDL";

        String namespace = "http://tempuri.org/";//namespace
        String methodName = "addStu";//要调用的方法名称selectAdminPassword

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId

        request.addProperty("StuNO", phoneSec);//mobileCode  mgNo
        request.addProperty("StuName", name);
        request.addProperty("StuAge", age);
        request.addProperty("StuSex", sex);
        request.addProperty("Class", sclass);
        request.addProperty("Department", department);
        request.addProperty("Tel", phone);
        request.addProperty("Password", agpsw);

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
            // 查询信息*/
            try {
                result = getRemoteInfo(params[0],params[1],params[2],params[3],params[4],params[5],params[6],params[7]);
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
            registerresulttv.setText("提交状态码："+result);
        }
    }
}
