package zms.servlet.web;

import zms.pojo.Product;
import zms.service.ProductImageService;
import zms.service.ProductService;
import zms.serviceImpl.ProductServiceImpl;
import zms.util.Encoding;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 19448
 */
@WebServlet("/web/searchThing")
public class SearchThing extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        Encoding.setEncoding(request,response);
        //接收参数
        /**
         * 搜索关键字
         */
        String kw = request.getParameter("kw");
        if(kw!=null){
        ProductService service=new ProductServiceImpl();
        List<Product> products = service.byKeyWordGetProduct(kw);
        request.setAttribute("products",products);
        request.getRequestDispatcher("/searchResult.jsp").forward(request,response);
        }
        /**
         * 底部的种类的id
         */
        String cid=request.getParameter("cid");
        if(cid!=null){

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
