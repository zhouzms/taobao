package zms.servlet.web;

import com.alibaba.fastjson.JSON;
import zms.util.AJaxResult;
import zms.util.Encoding;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 19448
 */
@WebServlet("/web/nousershoppingcret")
public class NoUserShoppingCret extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Encoding.setEncoding(request, response);
        //没有用户登录把购物车放入到cookie中
        /**
         * 产品id
         */
        String pid = request.getParameter("pid");
        /**
         * 产品数量
         */
        String pronum = request.getParameter("pronum");
        /**
         * 将pid与pronums放入cookie中存放起来
         */
        Cookie[] cookies = request.getCookies();
        if (cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cert")) {
                    String value = cookie.getValue();
                    String newValue = value + "#" + pid + "-" + pronum;
                    Cookie c=new Cookie("cert",newValue);
                    c.setPath("/");
                    c.setMaxAge(24*60*60);
                    response.addCookie(c);
                    break;
                }
                if(!cookie.getName().equals("cert")){
                    Cookie c=new Cookie("cert",pid+"-"+pronum);
                    c.setPath("/");
                    c.setMaxAge(24*60*60);
                    response.addCookie(c);
                }
            }
        }

        //找不到
            AJaxResult aJaxResult = new AJaxResult();
        aJaxResult.setFlag(true);
        String s = JSON.toJSONString(aJaxResult);
        response.getWriter().print(s);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
