package zms.servlet.web;

import zms.pojo.Category;
import zms.pojo.Product;
import zms.service.CategoryService;
import zms.service.ProductService;
import zms.serviceImpl.CategoryServiceImpl;
import zms.serviceImpl.ProductServiceImpl;
import zms.util.Encoding;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 19448
 */
@WebServlet("/web/getproduct")
public class GetProduct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        Encoding.setEncoding(request,response);
        //获取数据库中的商品//先不使用ajax
        /**
         * 获取商品的种类
         */
        CategoryService categoryService=new CategoryServiceImpl();
        List<Category> category = categoryService.getCategory();
        List<Category> categoriesArrayList=new ArrayList<>();
        /**
         * 获取某一类的商品
         */
        ProductService productService=new ProductServiceImpl();
        for(Category cg:category){
            List<Product> pros = productService.pros(cg.getId());
            Category arrproduct=new Category();
            arrproduct.setArrproduct(pros);
            arrproduct.setId(cg.getId());
            arrproduct.setName(cg.getName());
            categoriesArrayList.add(arrproduct);
        }
        request.setAttribute("categoriesArrayList",categoriesArrayList);
        request.setAttribute("category",category);
        request.setAttribute("categorycout",category.size());
        request.getRequestDispatcher("/home.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
