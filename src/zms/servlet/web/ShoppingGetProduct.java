package zms.servlet.web;

import com.alibaba.fastjson.JSON;
import zms.pojo.Product;
import zms.pojo.User;
import zms.service.CertService;
import zms.service.ProductService;
import zms.serviceImpl.CertServiceImpl;
import zms.serviceImpl.ProductServiceImpl;
import zms.util.AJaxResult;
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
        int usid;
        if(uid==null){
           usid=0;
        }else {
            usid=Integer.parseInt(uid);
        }

        ProductService service = new ProductServiceImpl();

        CertService certService = new CertServiceImpl();
        //创建入库时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);
        //session存入购物车
        CertShop c =(CertShop) request.getSession().getAttribute("certShop");
        if(c!=null){
            //已经产生了购物车
            if(pid!=null) {
                AJaxResult aJaxResult = new AJaxResult();
                aJaxResult.setFlag(true);
                String s = JSON.toJSONString(aJaxResult);
                response.getWriter().print(s);
                //传过来的值放入购物车中
                Product product = service.byPidGetProduct(Integer.parseInt(pid));
                c.addProduct(product, Integer.parseInt(pronum));
                //入库检测
                int ns = certService.getNum(Integer.parseInt(pid));
                if (ns == 0) {
                    //没有此产品
                    certService.insertCert(usid, Integer.parseInt(pid), Integer.parseInt(pronum), time);
                }
                if (ns > 0) {
                    //已经存在了此产品
                    int num = ns + Integer.parseInt(pronum);
                    certService.updateCert(Integer.parseInt(pid), num);
                }
                }
        }
        else{
                        //没有购物车//新建购物车
                        CertShop certShop = new CertShop();
                        String value = null;
                        //数据已全部传过来了
                        Cookie[] cookies = request.getCookies();
                        if (cookies.length > 0) {
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals("cert")) {
                                    value = cookie.getValue();
                                }
                            }
                        }
                        //遍历cookie中的购物车
                        if (value != null) {
                            //有cookie
                            String[] split = value.split("#");
                            for (String s : split) {
                                //产品id分离出来了
                                String[] split1 = s.split("-");
                                Product product = service.byPidGetProduct(Integer.parseInt(split1[0]));
                                certShop.addProduct(product, Integer.parseInt(split1[1]));
                                //入库检测
                                int ns = certService.getNum(Integer.parseInt(split1[0]));
                                if (ns == 0) {
                                    //没有此产品
                                    certService.insertCert(0, Integer.parseInt(split1[0]), Integer.parseInt(split1[1]), time);
                                }
                                if (ns > 0) {
                                    //已经存在了此产品
                                    int num = ns + Integer.parseInt(split1[1]);
                                    certService.updateCert(Integer.parseInt(split1[0]), num);
                                }
                            }
                        }
                        //先把cookie放入购物车中
                        request.getSession().setAttribute("certShop",certShop);
        }
                request.setAttribute("cert", c);
                request.getRequestDispatcher("/cart.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
