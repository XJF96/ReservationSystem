package com.zhbit.admin.administerclient;

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

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity {
    private TextView tvResult;
    private String result="";

    private EditText NameNumberText;
    private EditText PasswordText;
    private Button LoginButton;
    private  TextView RegistrationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvResult = (TextView) findViewById(R.id.tvResult);

        initComponent();
        RegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });

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
                    intent.setClass(MainActivity.this,Administer.class);
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
    }

    private void initComponent(){
        RegistrationButton=(TextView)findViewById(R.id.RegistrationButton);
        NameNumberText=(EditText) findViewById(R.id.NameNumberText);
        PasswordText=(EditText) findViewById(R.id.PasswordText);
        LoginButton = (Button) findViewById(R.id.LoginButton);
    }

    public String getRemoteInfo(String phoneSec) throws Exception{
        //String WSDL_URI = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?WSDL";//wsdl 的uri
        String WSDL_URI = "http://192.168.191.1:8086/Service1.asmx?WSDL";

        String namespace = "http://tempuri.org/";
        //要调用的方法名称selectAdminPassword
        String methodName = "selectADPwd";

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId

        request.addProperty("mgNo", phoneSec);
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
        protected String doInBackground(String... params) {//后台
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
            tvResult.setText(result);
        }
    }

}
