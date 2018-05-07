package com.zhbit.admin.studentclient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Administrator on 2018/5/5 0005.
 */

public class Opinion extends AppCompatActivity{
    private TextView opiniontv;
    private EditText opinionet;
    private Button pushopinion;
    private TextView opinionresulttv;

    private String opinionS_Num;
    private String result;
    private String opinionmsg;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_opinion);

        initTv();

        Bundle bundle=this.getIntent().getExtras();
        opinionS_Num=bundle.getString("opinionS_Num");

        opiniontv.setText("当前登录账号："+opinionS_Num);

        pushopinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opinionmsg=opinionet.getText().toString().trim();

                if("".equals(opinionmsg)){
                    opinionet.setError("您输入的报修信息为空");
                    opinionet.requestFocus();//获取焦点，就是输入光标
                    opinionet.setText("");
                    return;
                }

                Opinion.QueryAddressTask queryAddressTask = new Opinion.QueryAddressTask();
                //启动后台任务
                queryAddressTask.execute(opinionS_Num,opinionmsg);
            }
        });
    }

    private void initTv(){
        opiniontv=(TextView)findViewById(R.id.opiniontv);
        opinionet=(EditText)findViewById(R.id.opinionet);
        pushopinion=(Button)findViewById(R.id.pushopinion);
        opinionresulttv=(TextView)findViewById(R.id.opinionresulttv);
    }

    public String getRemoteInfo(String phoneSec,String op) throws Exception{
        //String WSDL_URI = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?WSDL";//wsdl 的uri
        String WSDL_URI = "http://192.168.191.1:8086/Service1.asmx?WSDL";

        String namespace = "http://tempuri.org/";//namespace
        String methodName = "addOpinion";//要调用的方法名称selectAdminPassword

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId

        request.addProperty("S_Num", phoneSec);//mobileCode  mgNo
        request.addProperty("Opinion", op);

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
                result = getRemoteInfo(params[0],params[1]);
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
            opinionresulttv.setText("提交状态码："+result);
        }
    }
}
