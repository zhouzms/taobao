package zms.servlet.web;

import zms.pojo.Product;
import zms.service.ProductService;
import zms.serviceImpl.ProductServiceImpl;
import zms.util.CertShop;
import zms.util.Encoding;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/web/cookiegetcert")
public class CookieGetCert extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Encoding.setEncoding(request,response);
        //遍历cookie中的值
        String value="";
        //数据已全部传过来了
        Cookie[] cookies = request.getCookies();
        if (cookies.length>0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cert")) {
                    //已经加入了购物车
                    value = cookie.getValue();
                }
            }
            if(!"".equals(value)) {
            String[] split = value.split("#");
                CertShop certShop = new CertShop();
                ProductService service = new ProductServiceImpl();
                for (String s : split) {
                    String[] split1 = s.split("-");
                    //已经解析出产品
                    Product product = service.byPidGetProduct(Integer.parseInt(split1[0]));
                    certShop.addProduct(product, Integer.parseInt(split1[1]));
                }
            request.setAttribute("certShop", certShop);
            }
        }
        request.getRequestDispatcher("/cart.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
