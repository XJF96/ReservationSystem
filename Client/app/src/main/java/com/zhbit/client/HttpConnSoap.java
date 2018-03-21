package com.zhbit.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/18 0018.
 */

public class HttpConnSoap {
    public ArrayList<String> GetWebServre(String methodName, ArrayList<String> Parameters, ArrayList<String>ParValues)
    {
        ArrayList<String> Values=new ArrayList<String>();

        String ServerUrl="http://192.168.191.1:8086/Service1.asmx";
        //String soapAction="http://tempuri.org/LongUserId1";
        String soapAction="http://tempuri.org/"+methodName;
        String data="";
        String soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                +"<soap:Body />";
        String tps,vps,ts;
        String mreakString="";
        mreakString="<"+methodName+" xmlns=\"http://tempuri.org/\">";
        for ( int i = 0; i < Parameters.size(); i++) {
            tps=Parameters.get(i).toString();
            //设置该方法的参数为.net webService中的参数名称
            vps=ParValues.get(i).toString();
            ts="<"+tps+">"+vps+"</"+tps+">";
            mreakString=mreakString+ts;
        }
        mreakString=mreakString+"</"+methodName+">";
	            /*
	            +"<HelloWorld xmlns=\"http://tempuri.org/\">"
	            +"<x>string11661</x>"
	            +"<SF1>string111</SF1>"
	            + "</HelloWorld>"
	            */
        String soap2="</soap:Envelope>";
        String requestData=soap+mreakString+soap2;
        //System.out.println(requestData);

        try{
            URL url =new URL(ServerUrl);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();//利用HttpURLConnection对象连接
            byte[] bytes=requestData.getBytes("utf-8");
            con.setDoInput(true); //设置使用 URL 连接进行输入数据
            con.setDoOutput(true);  //设置使用 URL 连接进行输出数据
            con.setUseCaches(false);   //禁止使用缓存
            con.setConnectTimeout(6000);// 设置超时时间
            con.setRequestMethod("POST");//设置请求方式

            con.setRequestProperty("Content-Type", "text/xml;charset=utf-8");//设置内容类型
            con.setRequestProperty("SOAPAction",soapAction);
            con.setRequestProperty("Content-Length",""+bytes.length);

            con.connect();//连接??

            if (con.getResponseCode() != 200) {//状态码200为OK
                throw new RuntimeException("请求url失败");
            }

            OutputStream outStream=con.getOutputStream();
            outStream.write(bytes);
            outStream.flush();
            outStream.close();
            InputStream inStream=con.getInputStream();

            //data=parser(inStream);
            //System.out.print("11");
            Values= inputStreamtovaluelist(inStream,methodName);
            //System.out.println(Values.size());
            return Values;
        }
        catch(Exception e)
        {
            System.out.print("2221");
            return null;
        }
    }

    public   ArrayList<String>   inputStreamtovaluelist  (InputStream   in,String MonthsName)   throws IOException {
        StringBuffer   out   =   new   StringBuffer();
        String s1="";
        byte[]   b   =   new   byte[4096];
        ArrayList<String> Values=new ArrayList<String>();
        Values.clear();
        for  (int   n;   (n   =   in.read(b))   !=   -1;)   {
            s1=new  String(b,   0,   n);
            out.append(s1);
        }
        System.out.println(out);
        String[] s13=s1.split("><");
        String ifString=MonthsName+"Result";
        String TS="";
        String vs="";

        Boolean getValueBoolean=false;
        for(int i=0;i<s13.length;i++){
            TS=s13[i];
            System.out.println(TS);
            int j,k,l;
            j=TS.indexOf(ifString);
            k=TS.lastIndexOf(ifString);

            if (j>=0)
            {
                System.out.println(j);
                if (getValueBoolean==false)
                {
                    getValueBoolean=true;
                }
                else {

                }

                if ((j>=0)&&(k>j))
                {
                    System.out.println("FFF"+TS.lastIndexOf("/"+ifString));
                    //System.out.println(TS);
                    l=ifString.length()+1;
                    vs=TS.substring(j+l,k-2);
                    //System.out.println("fff"+vs);
                    Values.add(vs);
                    System.out.println("退出"+vs);
                    getValueBoolean=false;
                    return   Values;
                }
            }
            if (TS.lastIndexOf("/"+ifString)>=0)
            {
                getValueBoolean=false;
                return   Values;
            }
            if ((getValueBoolean)&&(TS.lastIndexOf("/"+ifString)<0)&&(j<0))
            {
                k=TS.length();
                //System.out.println(TS);
                vs=TS.substring(7,k-8);
                //System.out.println("f"+vs);
                Values.add(vs);
            }

        }

        return   Values;
    }
}
