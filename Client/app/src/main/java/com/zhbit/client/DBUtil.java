package com.zhbit.client;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Administrator on 2017/12/18 0018.
 */

public class DBUtil {
    ArrayList<String> arrayList=new ArrayList<String>();
    ArrayList<String> brrayList=new ArrayList<String>();
    ArrayList<String> crrayList=new ArrayList<String>();
    HttpConnSoap Soaptest=new HttpConnSoap();
    public static Connection getConnection()
    {
        Connection con=null;
        try
        {
            //Class.forName("org.gjt.mm.mysql.Driver");
            //con=DriverManager.getConnection("jdbc:mysql://192.168.0.106:3306/test?useUnicode=true&characterEncoding=UTF-8","root","initial");
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
        return con;
    }


    //知道学生的学号得到他的密码
    public  String selectPwd(String S_Num)
    {
        String result=null;

        arrayList.clear();
        brrayList.clear();
        arrayList.add("S_Num");
        brrayList.add(S_Num);
        crrayList=Soaptest.GetWebServre("selectPwd", arrayList, brrayList);
        result= crrayList.get(0);
        return result;

    }


    //得到挂失图书的信息表中的记录的数量
    public  int getMaxLBNO()
    {
        int result=0;

        arrayList.clear();
        brrayList.clear();
        arrayList.add("VVV");
        brrayList.add("VVV");
        crrayList=Soaptest.GetWebServre("getMaxLBNO", arrayList, brrayList);
        result=Integer.parseInt(crrayList.get(0));
        return result;

    }
    //执行没有返回值的插入语句的方法
    public  void update(String sql)
    {
        String result=null;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("sql");
        brrayList.add(sql);

        crrayList=Soaptest.GetWebServre("update", arrayList, brrayList);
    }



    //已知书名，得到这个书籍的基本信息
    public Vector<String> selectAllfrombook(String BookName)
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

    //得到同种ISBN的书籍的数量
    public int getNumfrombdetailedInfo(String ISBN)
    {
        int num=0;
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("ISBN");
        brrayList.add(ISBN);
        crrayList=Soaptest.GetWebServre("getNumfrombdetailedInfo", arrayList, brrayList);
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

    	/*try
		{
    	//测试在后台打印
			Connection con=getConnection();
			Statement st=con.createStatement();
			String sql="select ISBN,B_Name,B_Author,B_Publishment from book where B_Name like '%"+BookName+"%' and B_Author like '%"+Author+"%'";
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){//将结果集信息添加到返回向量中
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
			}

			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
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
    public Vector<String> getISinfromdetails(String BookNo)
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
        crrayList=Soaptest.GetWebServre("getBNofromOrder", arrayList, brrayList);
        for(String s:crrayList)
        {
            v.add(s);
        }
        return v;
    }
    //根据预约图书信息表得到某同学的预约图书信息
    public  int getNumfromborderreport(String stuno)
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

    	/*try
		{
    	//测试在后台打印
			Connection con=getConnection();
			Statement st=con.createStatement();
			String sql="select record.B_Num,record.BorrowTime,record.ReturnTime,book.ISBN,book.B_Name,book.B_Author,book.B_Publishment from record,book,bdetailedinformation where record.B_Num=bdetailedinformation.B_Num AND bdetailedinformation.ISBN=book.ISBN And record.B_Num='"+BookNO+"'";
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){//将结果集信息添加到返回向量中
				result.add(rs.getString(1));
				result.add(rs.getString(2));
				result.add(rs.getString(3));
				result.add(rs.getString(4));
				result.add(rs.getString(5));
				result.add(rs.getString(6));
				result.add(rs.getString(7));
			}
			rs.close();
			st.close();
			con.close();
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}*/

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
        crrayList=Soaptest.GetWebServre("getBNSomeInfo", arrayList, brrayList);
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
        crrayList=Soaptest.GetWebServre("gettimefromrecord", arrayList, brrayList);
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
}
