package zms.servlet.web;

import zms.pojo.Product;
import zms.pojo.User;
import zms.service.ProductService;
import zms.serviceImpl.ProductServiceImpl;
import zms.util.CertShop;
import zms.util.Encoding;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        ProductService service = new ProductServiceImpl();
        CertShop certShop = new CertShop();
        //创建入库时间
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);
        int i=1;
        //遍历cookie中的购物车
        if(value!=null&&i>0){
            i--;
            //有cookie
            String[] split = value.split("#");
            for(String s:split){
                //产品id分离出来了
                String[] split1 = s.split("-");
                Product product = service.byPidGetProduct(Integer.parseInt(split1[0]));
                certShop.addProduct(product, Integer.parseInt(split1[1]));
            }
        }
        //传过来的值放入购物车中
        Product product = service.byPidGetProduct(Integer.parseInt(pid));
        certShop.addProduct(product,Integer.parseInt(pronum));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
