package zms.servlet.web;

import zms.pojo.Product;
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
@WebServlet("/web/productdetil")
public class ProductDetil extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Encoding.setEncoding(request,response);
        ProductService productService=new ProductServiceImpl();
        //种类的id
        String cid = request.getParameter("cid");
        if(cid!=null){
            //跳转页面
        }
        //产品的id
        String pid = request.getParameter("pid");
        if(pid!=null){
            int id=Integer.parseInt(pid);
            Product product = productService.byPidGetProduct(id);
            request.setAttribute("product",product);
            request.getRequestDispatcher("/product.jsp").forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
