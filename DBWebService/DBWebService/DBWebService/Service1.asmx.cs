using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Web.Services.Protocols;
using System.Xml.Linq;
using System.Web.Script.Services;
using System.ComponentModel;

namespace DBWebService
{
    /// <summary>
    /// Service1 的摘要说明
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]//公开 XML Web services 之前，请更改默认命名空间。
    //[WebService(Namespace = "http://webservice.xjf.com")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // 若要允许使用 ASP.NET AJAX 从脚本中调用此 Web 服务，请取消注释以下行。 
    [System.Web.Script.Services.ScriptService]

    public class Service1 : System.Web.Services.WebService
    {
        MySqlDB DB = new MySqlDB();

 //       [WebMethod(Description = "获取所有货物的信息")]//DBWebService.
 //       public MySqlDB.Product[] SelectAdminJson(String mgNO)
//        {
//            return DB.SelectAdminJson(mgNO).ToArray();
//        }

        [WebMethod(Description = "HelloWorld")]
        public String HelloWorld()
        {
            return "Hello World, 測試回调成功";
        }

        [WebMethod(Description = "测试数据库连接")]
        public bool Test()//测试连接数据库
        {
            return DB.test();
        }

        [WebMethod(Description = "所有管理员自制json")]
        public string selectAllCargoInforaaa()
        {
            return DB.selectAllCargoInforaaa();
        }

        [WebMethod(Description = "selectAdminPasswordJson自建格式")]
        public string selectAdminPasswordJson(string mgNo)
        {
            return DB.selectAdminPasswordJson(mgNo);
        }

        [WebMethod(Description = "获取数据库数据测试")]
        public DataSet Testreader()
        {
            //database=数据库名，这里不能用database，要用Initial Catalog=数据库名，不然连不上。
            string connStr = "server = 127.0.0.1;Initial Catalog=Laboratory;uid=sa;pwd=888888"; 
            string sqlStr = "select * from book";

            try
            {       
                DataSet ds = new DataSet();
                SqlConnection sqlcon = new SqlConnection(connStr);

                SqlDataAdapter da = new SqlDataAdapter(sqlStr, sqlcon);
                da.Fill(ds);

                return ds;
            }
            catch (Exception exp)
            {
                return null;
            }
        }

        [WebMethod(Description = "获取所有管理员的信息")]
        public string[] selectAllCargoInfor()
        {
            return DB.selectAllCargoInfor().ToArray();
        }

        [WebMethod(Description = "获取所有学生的信息")]
        public string[] selectAllStudent()
        {
            return DB.selectAllStudent().ToArray();
        }


        [WebMethod(Description = "管理员登录验证")]
        public String selectADPwd(String mgNo)
        {
            return DB.selectADPwd(mgNo);
        }

        [WebMethod(Description = "册除")]
        public String delete(String sql)
        {

            DB.delete(sql);
            return "1";
        }

        [WebMethod(Description = "权限验证")]
        public int CheckPermitted(String mgNO)
        {
            return DB.CheckPermitted(mgNO);
        }


        //查询管理员
        [WebMethod(Description = "查询管理员")]
        public String[] SelectAdmin(String mgNO)
        {
            return DB.SelectAdmin(mgNO);
        }

        //添加管理员
        [WebMethod(Description = "添加管理员")]
        public Boolean insertManager(String mgNO, String permitted, String password)
        {
            return DB.insertManager(mgNO, permitted, password);
        }

        //   删除管理员
        [WebMethod(Description = "删除管理员")]
        public string deleteManager(String mgNO)
        {
            DB.deleteManager(mgNO);
            return "1";
        }

        //查询管理员密码 
        [WebMethod(Description = "查询管理员密码")]
        public String selectAdminPassword(String mgNo)
        {
            //return "123";
            return DB.selectAdminPassword(mgNo);
        }

        //实验室入库
        [WebMethod(Description = "实验室入库")]
        public String insertBook(String isbn, String BookNo, String BookName, String Author, String Publishment, String BuyTime, String Borrowed, String Ordered, String instroduction)
        {
            DB.insertBook(isbn, BookNo, BookName, Author, Publishment, BuyTime, Borrowed, Ordered, instroduction);
            return "1";
        }

        //删除实验室信息
        [WebMethod(Description = "删除实验室信息")]
        public String deleteBook(String bookNO)
        {
            DB.deleteBook(bookNO);
            return "1";
        }

        //修改图书信息
        [WebMethod(Description = "修改图书信息")]
        //updateBook(String BookNo,String BookName,String Author,String Publishment,String BuyTime,String Borrowed,String Ordered,String Introduction)
        public String updateBook(String BookNo, String BookName, String Author, String Publishment, String BuyTime, String Borrowed, String Ordered, String Introduction)
        {
            DB.updateBook(BookNo, BookName, Author, Publishment, BuyTime, Borrowed, Ordered, Introduction);

            String ISBN = "";
            ISBN = DB.GetBookNoISBN(BookNo); //得到ISBN
            String sql = "update book set B_Name='" + BookName + "',B_Author='" + Author + "',B_Publishment='" + Publishment + "',B_BuyTime='" + BuyTime + "' Where  ISBN = '" + ISBN + "'";
            String sql2 = "update bdetailedinformation  set Borrowed='" + Borrowed + "',Ordered='" + Ordered + "',Introduction='" + Introduction + "' Where B_Num = '" + BookNo + "'";

            return sql;
        }

        //修改管理员密码
        [WebMethod(Description = "修改管理员密码")]
        public String updateManager(String mgNo, String password)
        {
            DB.updateManager(mgNo, password);
            String sql = "update manager set M_Pwd  = '" + password + "' where M_Num = '" + mgNo + "'";
            return sql;
            return "O";
        }


        //添加学生
        [WebMethod(Description = "添加学生")]
        public String addStu(String StuNO, String StuName, String StuAge, String StuSex, String Class, String Department, String Tel, String Permitted, String Password)
        {
            DB.addStu(StuNO, StuName, StuAge, StuSex, Class, Department, Tel, Permitted, Password);
            return "1";
        }

        //查询学生信息
        [WebMethod(Description = "查询学生信息")]
        public String[] selectStu(String StuNO)
        {
            return DB.selectStu(StuNO);
        }

        //删除学生信息
        [WebMethod(Description = "删除学生信息")]
        public String delectStu(String Sno)
        {
            DB.delectStu(Sno);
            return "1";
        }

        //修改学生信息
        [WebMethod(Description = "修改学生信息")]
        public String updateStu(String StuNO, String StuName, String StuAge, String StuSex, String Class, String Department, String Tel, String Permitted, String Password)
        {
            DB.updateStu(StuNO, StuName, StuAge, StuSex, Class, Department, Tel, Permitted, Password);
            return "1";
        }

        //检查是否超期
        [WebMethod(Description = "检查是否超期")]
        public int checktime(String sno, String bno, string feeflag)
        {
            if (feeflag.ToLower() == "true")
            {
                return DB.checktime(sno, bno, true);
            }
            else
            {
                return DB.checktime(sno, bno, false);
            }

        }

        //查看超期天数信息
        [WebMethod(Description = "查看超期天数信息")]
        public int selectfee(String StuNO)
        {
            return DB.selectfee(StuNO);
        }

        //交费
        [WebMethod(Description = "交费")]
        public String fee(String StuNo, String fee, string feeflag)
        {
            if (feeflag.ToLower() == "true")
            {
                return DB.fee(StuNo, fee, true);
            }
            else
            {
                return DB.fee(StuNo, fee, false);
            }
        }

        //查询借阅或预约实验室
        [WebMethod(Description = "查询借阅或预约实验室")]
        public String borrowororderbook(String bookNo)
        {
            return DB.borrowororderbook(bookNo);
        }

        //预约实验室
        [WebMethod(Description = "预约实验室")]
        public String orderbook(String bookNo, String StuNo)
        {
            DB.orderbook(bookNo, StuNo);
            return "1";
        }

        //借阅实验室
        [WebMethod(Description = "借阅实验室")]
        public String borrowbook(String bookNo, String StuNo)
        {
            DB.borrowbook(bookNo, StuNo);
            return "1";
        }

        [WebMethod(Description = "查询实验室信息")]
        public String[] selectbookfromISBN(String ISBN)
        {
            return DB.selectbookfromISBN(ISBN).ToArray();
        }
        [WebMethod(Description = "selectfeeinformation")]
        public String[] selectfeeinformation(String StuNO)
        {
            return DB.selectfeeinformation(StuNO).ToArray();
        }

        //得到挂失实验室的信息表中的记录的数量
        [WebMethod(Description = "得到挂失实验室的信息表中的记录的数量")]
        public int getMaxGSBH(String VVV)
        {
            return DB.getMaxGSBH();
        }

        //执行没有返回值的插入语句的方法
        [WebMethod(Description = "执行没有返回值的插入语句的方法")]
        public String update(String sql)
        {
            DB.update(sql);
            return "1";
        }

        //已知实验室名，得到这个实验室的基本信息
        [WebMethod(Description = "已知实验室名，得到这个实验室的基本信息")]
        public String[] selectAllfrombook(String BookName)
        {
            return DB.selectAllfrombook(BookName).ToArray();
        }

        //通过实验室号得到实验室的基本信息
        [WebMethod(Description = "通过实验室号得到实验室的基本信息")]
        public String[] selectbookinformationfrombookno(String bookno)
        {
            return DB.selectbookinformationfrombookno(bookno).ToArray();
        }

        //通过学号查询借阅数量
        [WebMethod(Description = "通过学号查询借阅实验室数量")]
        public int selectcount(String StuNO)
        {
            return DB.selectcount(StuNO);
        }

        //得到同种ISBN的实验室的数量
        [WebMethod(Description = "得到同种ISBN的实验室的数量")]
        public int getNumfrombdetailedInfo(String ISBN)
        {
            return DB.getNumfrombdetailedInfo(ISBN);
        }

        //一个ISBN号得到同种号下的这样的书的基本信息
        [WebMethod(Description = "一个ISBN号得到同种号下的这样的实验室的基本信息")]
        public String[] selectISBNALlfromdetailInfo(String ISBN)
        {
            return DB.selectISBNALlfromdetailInfo(ISBN).ToArray();
        }

        //根据实验室号得到管理者名
        [WebMethod(Description = "根据实验室号得到管理者名")]
        public String getAuthor(String BookNO)
        {
            return DB.getAuthor(BookNO);
        }

        //根据学生ID得到学生的班级和姓名
        [WebMethod(Description = "根据学生ID得到学生的班级和姓名")]
        public String[] getClassAndsname(String StuNO)
        {
            return DB.getClassAndsname(StuNO).ToArray();
        }

        //通过输入实验室的管理者得到实验室的基本信息
        [WebMethod(Description = "通过输入实验室的管理者得到实验室的基本信息")]
        public String[] getAuthorAllfromBook(String Author)
        {
            return DB.getAuthorAllfromBook(Author).ToArray();
        }

        //通过地点得到实验室的基本信息
        [WebMethod(Description = "通过地点得到实验室的基本信息")]
        public String[] getPubAllfrombook(String Publishment)
        {
            return DB.getPubAllfrombook(Publishment).ToArray();
        }

        //通过实验室名和管理者得到实验室的基本信息
        [WebMethod(Description = "通过实验室名和管理者得到实验室的基本信息")]
        public String[] getBnAuAllfrombook(String BookName, String Author)
        {
            return DB.getBnAuAllfrombook(BookName, Author).ToArray();
        }

        //通过实验室名和地点得到实验室书的基本信息
        [WebMethod(Description = "通过实验室名和地点得到实验室的基本信息")]
        public String[] getBnCbAllfrombook(String BookName, String Publishment)
        {
            return DB.getBnCbAllfrombook(BookName, Publishment).ToArray();
        }

        //通过作者和出版社
        [WebMethod(Description = "通过管理者和地点")]
        public String[] getAuCbAllfrombook(String Author, String Publishment)
        {
            return DB.getAuCbAllfrombook(Author, Publishment).ToArray();
        }

        //通过管理者 实验室名和地点进行查询
        [WebMethod(Description = "通过管理者 实验室名和地点进行查询")]
        public String[] getBnAuCbAllfrombook(String BookName, String Author, String Publishment)
        {
            return DB.getBnAuCbAllfrombook(BookName, Author, Publishment).ToArray();
        }

        //通过管理者 实验室名和出版社（地点）进行查询
        [WebMethod(Description = "通过作者 实验室名和地点进行查询")]
        public String[] getISinfromdetails(String BookNo)
        {
            return DB.getISinfromdetails(BookNo).ToArray();
        }

        //以下是后增加的
        //**********************************************************
        //通过ISBN对同一种ISBN号下的基本信息
        [WebMethod(Description = "通过ISBN对同一种ISBN号下的基本信息")]
        public String[] getISfrombook(String isbn)
        {
            return DB.getISfrombook(isbn).ToArray();
        }

        //根据学生的ID得到他预约实验室的基本信息
        [WebMethod(Description = "根据学生的ID得到他预约图书的基本信息")]
        public String[] getBNofromOrder(String stuNo)
        {
            return DB.getBNofromOrder(stuNo).ToArray();
        }
        //根据预约实验室信息表得到某同学的预约实验室信息
        [WebMethod(Description = "根据预约实验室信息表得到某同学的预约实验室信息")]
        public int getNumfromborderreport(String stuno)
        {

            return DB.getNumfromborderreport(stuno);

        }

        //根据学生的学号得到实验室的ISBN，BookNO,BookName,Author,Publishment,借阅时间，归还时间
        [WebMethod(Description = "根据学生的学号得到实验室的ISBN，BookNO,BookName,Author,Publishment,借时间，归还时间")]
        public String[] getSomeInfo(String stuno)
        {

            return DB.getSomeInfo(stuno).ToArray();

        }

        //根据实验室的号得到实验室的基本信息
        [WebMethod(Description = "根据实验室的号得到实验室的基本信息")]
        public String[] getBNSomeInfo(String BookNO)
        {
            return DB.getBNSomeInfo(BookNO).ToArray();
        }

        //根据预约实验室号得到实验室基本信息
        [WebMethod(Description = "根据预约实验室号得到实验室基本信息")]
        public String[] getBNSomeINFO(String BookNO)
        {
            return DB.getBNSomeINFO(BookNO).ToArray();
        }

        //通过学生的ID得到学生的班级，姓名，学号
        [WebMethod(Description = "通过学生的ID得到学生的班级，姓名，学号")]
        public String[] getIDClNO(String stuno)
        {

            return DB.getIDClNO(stuno);

        }

        //通过实验室号得到归还时间
        [WebMethod(Description = "通过实验室号得到归还时间")]
        public String gettimefromrecord(String BookNo)
        {
            return DB.gettimefromrecord(BookNo);

        }


        //通过实验室号判断时候是再借状态
        [WebMethod(Description = "通过实验室号判断时候是再借状态")]
        public String getifBorrow(String BookNO)
        {
            return DB.getifBorrow(BookNO);

        }

        //通过实验室号查询预约人
        [WebMethod(Description = "通过实验室号查询预约人")]
        public String getstu(String BookNO)
        {
            return DB.getstu(BookNO);
        }
        //通过isbn获得实验室号
        [WebMethod(Description = "通过isbn获得实验室号")]
        public String getBookNumber(String vvv)
        {
            return DB.getBookNumber();
        }
        //知道学生的学号得到他的密码
        [WebMethod(Description = "知道学生的学号得到他的密码")]
        public String selectPwd(String S_Num)
        {
            return DB.selectPwd(S_Num);
        }
        //得到挂失实验室的信息表中的记录的数量
        [WebMethod(Description = "得到挂失实验室的信息表中的记录的数量")]
        public int getMaxLBNO()
        {
            return DB.getMaxLBNO();
        }
    }
}
