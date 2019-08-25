package zms.servlet.web;


import zms.pojo.User;
import zms.serviceImpl.UserServiceImpl;
import zms.util.AJaxResult;
import zms.util.Encoding;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/web/WebUserServlet")
public class UserServlet extends javax.servlet.http.HttpServlet {
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //设置编码
        Encoding.setEncoding(request,response);
        //获取参数的值
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        String checkflag=request.getParameter("checkflag");
        UserServiceImpl userService=new UserServiceImpl();
        Boolean loginFlag = userService.login(username, password);
        AJaxResult aJaxResult=userService.getajax();
        User user=userService.getuser();
        //保存密码在cooike中
        Cookie[] cookies = request.getCookies();
        Cookie c1=new Cookie("username",username);
        Cookie c2=new Cookie("password",password);
        if(checkflag==null){
            c1.setMaxAge(0);
            c2.setMaxAge(0);
        }else {
        if(cookies.length>0){
            if(loginFlag){
                c1.setMaxAge(24*24*24);
                c2.setMaxAge(24*24*24);
            }else {
                c1.setMaxAge(0);
                c2.setMaxAge(0);
            }
        }}
        c1.setPath("/");c2.setPath("/");
        c2.setHttpOnly(false);c1.setHttpOnly(false);
        response.addCookie(c1);
        response.addCookie(c2);
        //json数据返回
//        ObjectMapper objectMapper=new ObjectMapper();
//        PrintWriter out=response.getWriter();
//        String Json =objectMapper.writeValueAsString(jaxResult);
//        out.print(Json);
        //判断帐号是否正确
        if(loginFlag){
            //帐号密码正确:将帐号保存在session
            HttpSession httpSession=request.getSession();
            httpSession.setAttribute("user",user);
            //页面跳转->重定向
            response.sendRedirect(request.getContextPath()+"/home.jsp");
        }else {
            request.setAttribute("msg",aJaxResult);
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
            doPost(request,response);
    }
}
