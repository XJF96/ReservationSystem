package com.zhbit.admin.administer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Vector;
/**
 * Created by Administrator on 2017/12/24 0024.
 */

public class DBUtil {
    static boolean feeflag=false;
    ArrayList<String> arrayList=new ArrayList<String>();
    ArrayList<String> brrayList=new ArrayList<String>();
    ArrayList<String> crrayList=new ArrayList<String>();
    HttpConnSoap Soaptest=new HttpConnSoap();
    public static Connection getConnection()
    {
        Connection con=null;
        try
        {
            System.out.println("111");
            Class.forName("org.gjt.mm.mysql.Driver");
            System.out.println("222");
            con=DriverManager.getConnection("jdbc:mysql://192.168.0.100:3306/test?useUnicode=true&characterEncoding=UTF-8","root","123456");
            con= DriverManager.getConnection("");
            System.out.println("333");
        }
        catch(Exception e)
        {
            System.out.println("444");
            e.printStackTrace();
        }
        return con;
    }

    public  void delete(String sql)
    {
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("sql");
        brrayList.add(sql);
        crrayList=Soaptest.GetWebServre("delete", arrayList, brrayList);

    }
    //登录验证
    public  String selectADPwd(String mgNo)
    {
        String result=null;
        arrayList.clear();
        brrayList.clear();
        arrayList.add("mgNo");
        brrayList.add(mgNo);
        crrayList=Soaptest.GetWebServre("selectADPwd", arrayList, brrayList);
        result= crrayList.get(0);
        return result;

    }
    //权限验证
    public  int CheckPermitted(String mgNO)
    {
        int permitted=0;
        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("mgNO");
        brrayList.add(mgNO);
        crrayList=Soaptest.GetWebServre("CheckPermitted", arrayList, brrayList);
        result= crrayList.get(0);
        permitted=Integer.parseInt(result);

        return permitted;
    }
    //查询管理员
    public  String[] SelectAdmin(String mgNO)
    {
        String sa[]=new String[3];
        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("mgNO");
        brrayList.add(mgNO);
        crrayList=Soaptest.GetWebServre("SelectAdmin", arrayList, brrayList);
        sa[0]=crrayList.get(0);
        sa[1]=crrayList.get(1);
        sa[2]=crrayList.get(2);
        //System.out.println("ffff");
        //System.out.println("退出");
        return sa;
    }
    //添加管理员
    public  Boolean insertManager(String mgNO,String permitted,String password)
    {
        Boolean falg=false;
        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("mgNO");
        brrayList.add(mgNO);
        arrayList.add("permitted");
        brrayList.add(permitted);
        arrayList.add("password");
        brrayList.add(password);
        crrayList=Soaptest.GetWebServre("insertManager", arrayList, brrayList);
        result=crrayList.get(0);
        if (result.equalsIgnoreCase("true"))
        {
            falg=true;
        }
        return falg;

    }


    //没有开始做
    //删除管理员
    public  void deleteManager(String mgNO)
    {
        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("mgNO");
        brrayList.add(mgNO);
        Soaptest.GetWebServre("deleteManager", arrayList, brrayList);
    }
    //查询管理员密码
    public  String selectAdminPassword(String mgNo)
    {
        String pwd=null;
        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("mgNo");
        brrayList.add(mgNo);
        //System.out.println("KKK"+mgNo);
        crrayList=Soaptest.GetWebServre("selectAdminPassword", arrayList, brrayList);
        //System.out.println("查询结ffff");
        pwd=crrayList.get(0).trim();
        //System.out.println("查询结土");
        return pwd;

    }

    //修改管理员密码

    public  void updateManager(String mgNo,String password)
    {
        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("mgNo");
        brrayList.add(mgNo);
        arrayList.add("password");
        brrayList.add(password);
        crrayList=Soaptest.GetWebServre("updateManager", arrayList, brrayList);
    }

    //图书入库
    public void insertBook(String isbn,String BookNo,String BookName,String Author,String Publishment,String BuyTime,String Borrowed,String Ordered,String instroduction)
    {
        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("isbn");
        arrayList.add("BookNo");
        arrayList.add("BookName");
        arrayList.add("Author");
        arrayList.add("Publishment");
        arrayList.add("BuyTime");
        arrayList.add("Borrowed");
        arrayList.add("Ordered");
        arrayList.add("instroduction");

        brrayList.add(isbn);
        brrayList.add(BookNo);
        brrayList.add(BookName);
        brrayList.add(Author);
        brrayList.add(Publishment);
        brrayList.add(BuyTime);
        brrayList.add(Borrowed);
        brrayList.add(Ordered);
        brrayList.add(instroduction);
        crrayList=Soaptest.GetWebServre("insertBook", arrayList, brrayList);
    }
    //删除图书信息
    public  void deleteBook(String bookNO)
    {
        String pwd=null;
        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("bookN");
        brrayList.add(bookNO);
        crrayList=Soaptest.GetWebServre("deleteBook", arrayList, brrayList);
    }
    //修改图书信息
    public  void updateBook(String BookNo,String BookName,String Author,String Publishment,String BuyTime,String Borrowed,String Ordered,String Introduction)
    {
        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("BookNo");
        arrayList.add("BookName");
        arrayList.add("Author");
        arrayList.add("Publishment");
        arrayList.add("BuyTime");
        arrayList.add("Borrowed");
        arrayList.add("Ordered");
        arrayList.add("Introduction");


        brrayList.add(BookNo);
        brrayList.add(BookName);
        brrayList.add(Author);
        brrayList.add(Publishment);
        brrayList.add(BuyTime);
        brrayList.add(Borrowed);
        brrayList.add(Ordered);
        brrayList.add(Introduction);
        crrayList=Soaptest.GetWebServre("updateBook", arrayList, brrayList);
    }
    //添加学生
    public  void addStu(String StuNO,String StuName,String StuAge,String StuSex,String Class,String Department,String Tel,String Permitted,String Password)
    {
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("StuNO");
        arrayList.add("StuName");
        arrayList.add("StuAge");
        arrayList.add("StuSex");
        arrayList.add("Class");
        arrayList.add("Department");
        arrayList.add("Tel");
        arrayList.add("Permitted");
        arrayList.add("Password");

        brrayList.add(StuNO);
        brrayList.add(StuName);
        brrayList.add(StuAge);
        brrayList.add(StuSex);
        brrayList.add(Class);
        brrayList.add(Department);
        brrayList.add(Tel);
        brrayList.add(Permitted);
        brrayList.add(Password);

        crrayList=Soaptest.GetWebServre("addStu", arrayList, brrayList);
    }
    //查询学生信息
    public  String[] selectStu(String StuNO)
    {
        String ss[]=new String[8];
        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("StuNO");
        brrayList.add(StuNO);
        crrayList=Soaptest.GetWebServre("selectStu", arrayList, brrayList);
        ss[0]=crrayList.get(0);
        ss[1]=crrayList.get(1);
        ss[2]=crrayList.get(2);
        ss[3]=crrayList.get(3);
        ss[4]=crrayList.get(4);
        ss[5]=crrayList.get(5);
        ss[6]=crrayList.get(6);
        ss[7]=crrayList.get(7);
        return ss;

    }
    //删除学生信息
    public  void delectStu(String Sno)
    {

        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("Sno");
        brrayList.add(Sno);
        crrayList=Soaptest.GetWebServre("delectStu", arrayList, brrayList);
    }
    //修改学生信息
    public  void updateStu(String StuNO,String StuName,String StuAge,String StuSex,String Class,String Department,String Tel,String Permitted,String Password)
    {
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("StuNO");
        arrayList.add("StuName");
        arrayList.add("StuAge");
        arrayList.add("StuSex");
        arrayList.add("Class");
        arrayList.add("Department");
        arrayList.add("Tel");
        arrayList.add("Permitted");
        arrayList.add("Password");

        brrayList.add(StuNO);
        brrayList.add(StuName);
        brrayList.add(StuAge);
        brrayList.add(StuSex);
        brrayList.add(Class);
        brrayList.add(Department);
        brrayList.add(Tel);
        brrayList.add(Permitted);
        brrayList.add(Password);

        crrayList=Soaptest.GetWebServre("updateStu", arrayList, brrayList);
    }
    //检查是否超期
    public  int checktime(String sno,String bno)
    {//-1代表超期没交罚款  0代表当天借的书   1代表正常还的书  -2表示超期交罚款
        int flag;
        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("sno");
        brrayList.add(sno);
        arrayList.add("bno");
        brrayList.add(bno);
        arrayList.add("feeflag");
        if  (feeflag)
        {
            brrayList.add("true");
        }
        else
        {
            brrayList.add("false");
        }
        crrayList=Soaptest.GetWebServre("checktime", arrayList, brrayList);
        result=crrayList.get(0);
        flag=Integer.parseInt(result);
        return flag;

    }
    //查看超期天数信息
    public  int selectfee(String StuNO)
    {
        int day=0;
        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("StuNO");
        brrayList.add(StuNO);
        crrayList=Soaptest.GetWebServre("selectfee", arrayList, brrayList);
        result=crrayList.get(0);
        day=Integer.parseInt(result);
        return day;
    }

    //交费
    //这里需要处理FEEFALG
    public  void fee(String StuNo,String fee)
    {


        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("StuNo");
        brrayList.add(StuNo);
        arrayList.add("fee");
        brrayList.add(fee);
        arrayList.add("feeflag");
        if (feeflag)
        {
            brrayList.add("true");
        }
        else
        {
            brrayList.add("false");
        }
        crrayList=Soaptest.GetWebServre("fee", arrayList, brrayList);
        result=crrayList.get(0);
        if (result.equalsIgnoreCase("true"))
        {
            feeflag=true;
        }
        else
        {
            feeflag=false;
        }

    }
    //查询借阅或预约图书
    public String borrowororderbook(String bookNo)
    {
        String s=null;
        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("bookNo");
        brrayList.add(bookNo);
        crrayList=Soaptest.GetWebServre("borrowororderbook", arrayList, brrayList);
        s=crrayList.get(0);

        return s;
    }
    //预约图书
    public  void orderbook(String bookNo,String StuNo)
    {
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("bookNo");
        brrayList.add(bookNo);
        arrayList.add("StuNo");
        brrayList.add(StuNo);
        System.out.println("111");
        crrayList=Soaptest.GetWebServre("orderbook", arrayList, brrayList);
        System.out.println("1113");

    }
    //借阅图书
    public  void borrowbook(String bookNo,String StuNo)
    {
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("bookNo");
        brrayList.add(bookNo);
        arrayList.add("StuNo");
        brrayList.add(StuNo);
        crrayList=Soaptest.GetWebServre("borrowbook", arrayList, brrayList);
    }

    public  Vector<String> selectbookfromISBN(String ISBN)
    {

        Vector<String> v =new Vector<String>();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("ISBN");
        brrayList.add(ISBN);
        crrayList=Soaptest.GetWebServre("selectbookfromISBN", arrayList, brrayList);
        for(String s:crrayList)
        {
            v.add(s);
        }

        return v;
    }

    public  Vector<String> selectfeeinformation(String StuNO)
    {
        Vector<String> v =new Vector<String>();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("StuNO");
        brrayList.add(StuNO);
        crrayList=Soaptest.GetWebServre("selectfeeinformation", arrayList, brrayList);
        for(String s:crrayList)
        {
            v.add(s);
        }
        return v;

    }

    //得到挂失图书的信息表中的记录的数量
    public  int getMaxGSBH()
    {
        int result=0;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("VVV");
        brrayList.add("VVV");
        crrayList=Soaptest.GetWebServre("getMaxGSBH", arrayList, brrayList);
        //result=crrayList.get(0);
        result=Integer.parseInt(crrayList.get(0));
        return result;

    }
    //执行没有返回值的插入语句的方法
    public  void update(String sql)
    {
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("sql");
        brrayList.add(sql);
        crrayList=Soaptest.GetWebServre("update", arrayList, brrayList);
    }
    //已知书名，得到这个书籍的基本信息
    public  Vector<String> selectAllfrombook(String BookName)
    {
        Vector<String> v =new Vector<String>();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("BookName");
        brrayList.add(BookName);
        crrayList=Soaptest.GetWebServre("selectAllfrombook", arrayList, brrayList);
        for(String s:crrayList)
        {
            v.add(s);
        }

        return v;
    }
    //通过书号得到书的基本信息
    public  String[] selectbookinformationfrombookno(String bookno)
    {
        String info[]=new String[6];
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("bookno");
        brrayList.add(bookno);
        crrayList=Soaptest.GetWebServre("selectbookinformationfrombookno", arrayList, brrayList);
        info[0]=crrayList.get(0);
        info[1]=crrayList.get(1);
        info[2]=crrayList.get(2);
        info[3]=crrayList.get(3);
        info[4]=crrayList.get(4);
        info[5]=crrayList.get(5);
        return info;
    }
    //通过学号查询借阅数量
    public  int selectcount(String StuNO)
    {
        int a=0;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("StuNO");
        brrayList.add(StuNO);
        crrayList=Soaptest.GetWebServre("selectcount", arrayList, brrayList);
        //result=crrayList.get(0);
        a=Integer.parseInt(crrayList.get(0));

        return a;
    }

    //得到同种ISBN的书籍的数量
    public  int getNumfrombdetailedInfo(String ISBN)
    {
        int num=0;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("ISBN");
        brrayList.add(ISBN);
        crrayList=Soaptest.GetWebServre("selectcount", arrayList, brrayList);
        num=Integer.parseInt(crrayList.get(0));
        return num;

    }

    //一个ISBN号得到同种号下的这样的书的基本信息
    public  Vector<String> selectISBNALlfromdetailInfo(String ISBN)
    {
        Vector<String> v =new Vector<String>();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("ISBN");
        brrayList.add(ISBN);
        crrayList=Soaptest.GetWebServre("selectISBNALlfromdetailInfo", arrayList, brrayList);
        for(String s:crrayList)
        {
            v.add(s);
        }
        return v;
    }


    //根据书号得到作者名
    public  String getAuthor(String BookNO)
    {
        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("BookNO");
        brrayList.add(BookNO);
        crrayList=Soaptest.GetWebServre("getAuthor", arrayList, brrayList);
        result=crrayList.get(0);
        return result;

    }

    //根据学生ID得到学生的班级和姓名
    public  Vector<String> getClassAndsname(String StuNO)
    {
        Vector<String> result =new Vector<String>();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("StuNO");
        brrayList.add(StuNO);
        crrayList=Soaptest.GetWebServre("getClassAndsname", arrayList, brrayList);
        for(String s:crrayList)
        {
            result.add(s);
        }
        return result;


    }

    //通过输入图书的作者得到图书的基本信息
    public  Vector<String> getAuthorAllfromBook(String Author)
    {
        Vector<String> v =new Vector<String>();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("Author");
        brrayList.add(Author);
        crrayList=Soaptest.GetWebServre("getAuthorAllfromBook", arrayList, brrayList);
        for(String s:crrayList)
        {
            v.add(s);
        }
        return v;
    }


    //通过出版社得到图书的基本信息
    public  Vector<String> getPubAllfrombook(String Publishment)
    {
        Vector<String> v =new Vector<String>();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("Publishment");
        brrayList.add(Publishment);
        crrayList=Soaptest.GetWebServre("getPubAllfrombook", arrayList, brrayList);
        for(String s:crrayList)
        {
            v.add(s);
        }
        return v;

    }


    //通过书名和作者得到图书的基本信息
    public  Vector<String> getBnAuAllfrombook(String BookName,String Author)
    {
        Vector<String> v =new Vector<String>();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("BookName");
        brrayList.add(BookName);
        arrayList.add("Author");
        brrayList.add(Author);
        crrayList=Soaptest.GetWebServre("getBnAuAllfrombook", arrayList, brrayList);
        for(String s:crrayList)
        {
            v.add(s);
        }
        return v;
    }

    //通过书名和出版社得到图书的基本信息
    public  Vector<String> getBnCbAllfrombook(String BookName,String Publishment)
    {
        Vector<String> v =new Vector<String>();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("BookName");
        brrayList.add(BookName);
        arrayList.add("Publishment");
        brrayList.add(Publishment);
        crrayList=Soaptest.GetWebServre("getBnCbAllfrombook", arrayList, brrayList);
        for(String s:crrayList)
        {
            v.add(s);
        }
        return v;
    }

    //通过作者和出版社
    public  Vector<String> getAuCbAllfrombook(String Author,String Publishment)
    {
        Vector<String> v =new Vector<String>();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("Author");
        brrayList.add(Author);
        arrayList.add("Publishment");
        brrayList.add(Publishment);
        crrayList=Soaptest.GetWebServre("getAuCbAllfrombook", arrayList, brrayList);
        for(String s:crrayList)
        {
            v.add(s);
        }
        return v;
    }

    //通过作者 书名和出版社进行查询
    public  Vector<String> getBnAuCbAllfrombook(String BookName,String Author,String Publishment)
    {
        Vector<String> v =new Vector<String>();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("BookName");
        brrayList.add(BookName);
        arrayList.add("Author");
        brrayList.add(Author);
        arrayList.add("Publishment");
        brrayList.add(Publishment);
        crrayList=Soaptest.GetWebServre("getBnAuCbAllfrombook", arrayList, brrayList);
        for(String s:crrayList)
        {
            v.add(s);
        }
        return v;
    }

    //通过书号对ISBN和图书简介的查询
    public  Vector<String> getISinfromdetails(String BookNo)
    {
        Vector<String> v =new Vector<String>();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("BookNo");
        brrayList.add(BookNo);
        crrayList=Soaptest.GetWebServre("getISinfromdetails", arrayList, brrayList);
        for(String s:crrayList)
        {
            v.add(s);
        }
        return v;
    }

    //通过ISBN对同一种ISBN号下的基本信息
    public  Vector<String> getISfrombook(String isbn)
    {
        Vector<String> v =new Vector<String>();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("isbn");
        brrayList.add(isbn);

        crrayList=Soaptest.GetWebServre("getISfrombook", arrayList, brrayList);
        for(String s:crrayList)
        {
            v.add(s);
        }
        return v;
    }

    //根据学生的ID得到他预约图书的基本信息
    public  Vector<String> getBNofromOrder(String stuNo)
    {
        Vector<String> v =new Vector<String>();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("stuNo");
        brrayList.add(stuNo);
        crrayList=Soaptest.GetWebServre("getISfrombook", arrayList, brrayList);
        for(String s:crrayList)
        {
            v.add(s);
        }
        return v;
    }
    //根据预约图书信息表得到某同学的预约图书信息
    public int getNumfromborderreport(String stuno)
    {
        int num=0;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("stuNo");
        brrayList.add(stuno);
        crrayList=Soaptest.GetWebServre("getNumfromborderreport", arrayList, brrayList);
        num=Integer.parseInt(crrayList.get(0));
        return num;

    }

    //根据学生的学号得到图书的ISBN，BookNO,BookName,Author,Publishment,借阅时间，归还时间
    public  Vector<String> getSomeInfo(String stuno)
    {
        Vector<String> result=new Vector<String>();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("stuno");
        brrayList.add(stuno);
        crrayList=Soaptest.GetWebServre("getSomeInfo", arrayList, brrayList);
        for(String s:crrayList)
        {
            result.add(s);
        }
        return result;

    }



    //根据图书的书号得到图书的基本信息
    public  Vector<String> getBNSomeInfo(String BookNO)
    {
        Vector<String> result=new Vector<String>();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("BookNO");
        brrayList.add(BookNO);
        crrayList=Soaptest.GetWebServre("getBNSomeInfo", arrayList, brrayList);
        for(String s:crrayList)
        {
            result.add(s);
        }
        return result;

    }

    //根据预约图书书号得到图书基本信息
    public  Vector<String> getBNSomeINFO(String BookNO)
    {
        Vector<String> result=new Vector<String>();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("BookNO");
        brrayList.add(BookNO);
        crrayList=Soaptest.GetWebServre("getBNSomeINFO", arrayList, brrayList);
        for(String s:crrayList)
        {
            result.add(s);
        }
        return result;

    }

    //通过学生的ID得到学生的班级，姓名，学号
    public  String[] getIDClNO(String stuno)
    {
        String[] result=new String[3];
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("stuno");
        brrayList.add(stuno);
        crrayList=Soaptest.GetWebServre("getIDClNO", arrayList, brrayList);
        result[0]=crrayList.get(0);
        result[1]=crrayList.get(1);
        result[2]=crrayList.get(2);

        return result;

    }

    //通过书号得到归还时间
    public  String gettimefromrecord(String BookNo)
    {
        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("BookNo");
        brrayList.add(BookNo);
        crrayList=Soaptest.GetWebServre("getIDClNO", arrayList, brrayList);
        result=crrayList.get(0);
        return result;

    }


    //通过书号判断时候是再借状态
    public  String getifBorrow(String BookNO)
    {
        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("BookNO");
        brrayList.add(BookNO);
        crrayList=Soaptest.GetWebServre("getifBorrow", arrayList, brrayList);
        result=crrayList.get(0);
        return result;

    }

    //通过书号查询预约人
    public  String getstu(String BookNO)
    {
        String stu=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("BookNO");
        brrayList.add(BookNO);
        crrayList=Soaptest.GetWebServre("getstu", arrayList, brrayList);
        stu=crrayList.get(0);
        return stu;
    }
    //通过isbn获得书号
    public  String getBookNumber()
    {
        String bookno=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("vvv");
        brrayList.add("vvv");
        crrayList=Soaptest.GetWebServre("getBookNumber", arrayList, brrayList);
        bookno=crrayList.get(0);
        return bookno;
    }
}
