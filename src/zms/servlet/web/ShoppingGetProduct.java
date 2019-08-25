package zms.servlet.web;

import zms.pojo.User;
import zms.service.ProductService;
import zms.serviceImpl.ProductServiceImpl;
import zms.util.CertShop;
import zms.util.Encoding;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author 19448
 */
@WebServlet("/web/shoppingGetProduct")
public class ShoppingGetProduct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Encoding.setEncoding(request,response);
        /**
         * 产品id
         */
        String pid = request.getParameter("pid");
        /**
         * 产品数量
         */
        String pronum=request.getParameter("pronum");
        /**
         * 用户id
         */
        String uid=request.getParameter("uid");
        String value=null;
        //数据已全部传过来了
        Cookie[] cookies = request.getCookies();
        if (cookies.length>0){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("cert")){

                    value = cookie.getValue();
                }
            }
        }
        if(value!=null){
            //有cookie
            String[] split = value.split("#");
            for(String s:split){
                //产品id分离出来了
                String[] split1 = s.split("-");
                for(String sg:split1){

                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
