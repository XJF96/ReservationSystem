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

public class MainActivity extends AppCompatActivity  {
    private EditText etProductName;
    private TextView tvResult;
    private Button btnSearch;
    public String str;
    public String result;


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
            public void onClick(View v) {
                String phoneSec=etProductName.getText().toString().trim();
                if("".equals(phoneSec)){//||phoneSec.length()<7
                    etProductName.setError("您输入的查询有误！");
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

    }
    public String getRemoteInfo(String phoneSec) throws Exception{

        //String WSDL_URI = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?WSDL";//wsdl 的uri
        String WSDL_URI = "http://192.168.191.1:8086/Service1.asmx?WSDL";

        //String namespace = "http://WebXml.com.cn/";//namespace
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
        result = "管理员"+phoneSec+"密码:"+object.getProperty(0).toString();
        Log.d("debug",result);
        return result;
    }

    class QueryAddressTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
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








//    class WSAsyncTask extends AsyncTask {
//        String result = "";
//        @Override
//        protected Object doInBackground(Object... params)//不固定参数个数
//        {
//            String M_Num="M_Num";
//            String M_Permitted="M_Permitted";
//            String M_Pwd="M_Pwd";
//
//            try
//            {
//               // String serviceUrl = "http://192.168.191.1:8086/Service1.asmx?wsdl";  SelectAdmin
//                String methodName = "SelectAdmin";
//                String soap_action="http://tempuri.org/SelectAdmin";
//
//                SoapObject request = new SoapObject(namespace, methodName);
//                System.out.println("第一步：WebService的命名空间和调用的方法名导入成功");
//
//                request.addProperty("mgNO",str);
//                //request.addProperty("mgId",str);//顺序第二个参数名，与wsdl文档完全一致
//                System.out.println("第二步：传入参数mgNo:"+str);
//
//                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//                System.out.println("第三步：创建SoapSerializationEnvelope对象，并指定WebService的版本");
//
//                envelope.bodyOut = request;//发送请求badyout
//                envelope.dotNet=true;  //.net的webservice，否则得不到传入参数
//
//                //调用webservice
//                HttpTransportSE ht = new HttpTransportSE(serviceUrl);
//                try {
//                    //call函数参数soapAction在wsdl文档中可以看到
//                    //这个操作会产生异常需要对异常进行处理
//                    ht.call(soap_action, envelope);
//                    //ht.debug=true;
//                    System.out.println("call完了");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (XmlPullParserException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("第四步：创建HttpTransportSE对象，并指定WSDL文档的URL");
//
//                if (envelope.getResponse() != null)//三个参数返回？？？
//                {// 判断一下返回的信息是否到达本地,如果到达，则从信封里面取出结果
//                    System.out.println("envelope.getResponse()不为空");
//
//                    System.out.println("结果："+envelope.getResponse().toString());//ArrayOfString is null
//
//                    //获得返回信息并取得结果
//                    SoapObject object = (SoapObject) envelope.bodyIn;
//                    result=object.getProperty(0).toString();
//
//                    //如果webserver返回ArrayOfString 、  要想得到String[]类型、 只需.ToArray()就行了
////                    SoapObject soapObject = (SoapObject) envelope.getResponse();//获取回应
////                    result=soapObject.toString();
//
////                    result = "管理员编号：" + soapObject.getProperty(M_Num).toString() + "\n";
////                    result += "管理员权限：" + soapObject.getProperty(M_Permitted).toString()
////                            + "\n";
////                    result += "管理员密码：" + soapObject.getProperty(M_Pwd);
//
//                }
//                else
//                {
//                    result = "查无此人.envelope.getResponse()为空";
//                }
//            }
//            catch (Exception e)
//            {
//                e.getMessage();
//                result = "调用WebService发生异常:"+e.getMessage();
//            }
//
//            // 必须使用post方法更新UI组件
//            tvResult.post(new Runnable()
//            {
//                @Override
//                public void run()
//                {
//                    tvResult.setText(result);
//
//                }
//            });
//            return null;
//        }
//    }

//    public void onClick(View view)
//    {
//        // 异步执行调用WebService的任务
//        str=etProductName.getText().toString();
//        new WSAsyncTask().execute();
//
//    }

}
