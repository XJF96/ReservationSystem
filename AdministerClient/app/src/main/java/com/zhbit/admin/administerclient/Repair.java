package com.zhbit.admin.administerclient;

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
 *
 * @author Administrator
 * @date 2018/5/17 0017
 */

public class Repair extends AppCompatActivity {
    private TextView repairtv;
    private EditText repairet;
    private Button pushrepair;
    private TextView repairresulttv;

    private String repairS_Num;
    private String result;
    private String repairmsg;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_repair);

        initTv();

        Bundle bundle=this.getIntent().getExtras();
        repairS_Num=bundle.getString("repairS_Num");

        repairtv.setText("当前登录账号："+repairS_Num);

        pushrepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repairmsg=repairet.getText().toString().trim();

                if("".equals(repairmsg)){
                    repairet.setError("您输入的报修信息为空");
                    repairet.requestFocus();//获取焦点，就是输入光标
                    repairet.setText("");
                    return;
                }

                Repair.QueryAddressTask queryAddressTask = new Repair.QueryAddressTask();
                //启动后台任务
                queryAddressTask.execute(repairS_Num,repairmsg);

            }
        });
    }

    private void initTv(){
        repairtv=(TextView)findViewById(R.id.repairtv);
        repairet=(EditText)findViewById(R.id.repairet);
        pushrepair=(Button)findViewById(R.id.pushrepair);
        repairresulttv=(TextView)findViewById(R.id.repairresulttv);
    }

    public String getRemoteInfo(String phoneSec,String rp) throws Exception{
        //String WSDL_URI = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?WSDL";//wsdl 的uri
        String WSDL_URI = "http://192.168.191.1:8086/Service1.asmx?WSDL";

        String namespace = "http://tempuri.org/";
        //要调用的方法名称selectAdminPassword
        String methodName = "addRepair";

        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId

        request.addProperty("S_Num", phoneSec);
        request.addProperty("Repair", rp);

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
        /**此方法可以在主线程改变UI**/
        protected void onPostExecute(String result) {
            // 将WebService返回的结果显示在TextView中
            repairresulttv.setText("提交状态码："+result);
        }
    }
}
