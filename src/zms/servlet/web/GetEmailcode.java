package zms.servlet.web;

import com.alibaba.fastjson.JSONObject;
import zms.daoImpl.LoginDaoImpl;
import zms.pojo.User;
import zms.util.AJaxResult;
import zms.util.Encoding;
import zms.util.RandomWord;
import zms.util.email.GetJavaMail;
import zms.util.message.GetCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 19448
 */
@WebServlet("/web/getemailcode")
public class GetEmailcode extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        Encoding.setEncoding(request,response);
        //接收参数
        String emailenum=request.getParameter("emailenum");
        AJaxResult aJaxResult=new AJaxResult();
        LoginDaoImpl<User> Dao=new LoginDaoImpl<>();
        //检查邮箱号是否注册
        String sql="select * from login where email_l =?";
        ResultSet query = Dao.query(sql, emailenum);
        try {
            if(query.next()==true){
                //邮箱号有被注册
                aJaxResult.setFlag(false);
                aJaxResult.setMsg("邮箱号已经被注册");
            }else {
                //没有注册在发送邮箱验证
                Boolean send = GetJavaMail.sendEmail(emailenum);
                if(send){
                    aJaxResult.setFlag(true);
                    aJaxResult.setMsg("邮箱验证码发送成功");
                }else{
                    aJaxResult.setFlag(false);
                    aJaxResult.setMsg("邮箱验证码发送失败");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //以流的形式传到前台
        String outJosn= JSONObject.toJSONString(aJaxResult);
        response.getWriter().print(outJosn);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
