package zms.servlet.web;

import com.alibaba.fastjson.JSONObject;
import zms.daoImpl.LoginDaoImpl;
import zms.pojo.User;
import zms.util.AJaxResult;
import zms.util.Encoding;
import zms.util.message.GetCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/web/getcode")
public class getcode extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        Encoding.setEncoding(request,response);
        //接收参数
        String phonenum=request.getParameter("phonenum");
        AJaxResult aJaxResult=new AJaxResult();
        LoginDaoImpl<User> Dao=new LoginDaoImpl<>();
        //检查手机号是否已经注册
        //查看手机号是否注册
        String s="select * from login where phone_l=?";
        ResultSet query = Dao.query(s, phonenum);
        try {
            if(query.next()==true){
                //手机号有被注册
                aJaxResult.setFlag(false);
                aJaxResult.setMsg("手机号已经被注册");
            }else {
                //没有注册在发送短信
                Boolean send = GetCode.isSend(phonenum);
                if(send){
                    aJaxResult.setFlag(true);
                    aJaxResult.setMsg("验证码发送成功");
                }else{
                    aJaxResult.setFlag(false);
                    aJaxResult.setMsg("验证码发送失败");
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
