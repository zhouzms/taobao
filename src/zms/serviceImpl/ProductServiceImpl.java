package zms.serviceImpl;

import zms.dao.LoginDao;
import zms.daoImpl.LoginDaoImpl;
import zms.pojo.Product;
import zms.pojo.ProductImage;
import zms.pojo.Review;
import zms.service.ProductImageService;
import zms.service.ProductService;
import zms.util.JDBCUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 19448
 */
public class ProductServiceImpl implements ProductService {
    LoginDao productDao=new LoginDaoImpl();
    @Override
    public List<Product> pros(int categoryId) {
        ArrayList<Product> productsArr=new ArrayList<>();
        String sql="select * from product where cid=?";
        ResultSet query = productDao.query(sql, categoryId);
       try{
           while(query.next()){
               Product p=new Product();
               p.setId(query.getInt("id"));
               p.setName(query.getString("name"));
               p.setSubTitle(query.getString("subTitle"));
               p.setOrignalPrice(query.getFloat("orignalPrice"));
               p.setPromotePrice(query.getFloat("promotePrice"));
               p.setStock(query.getInt("stock"));
               p.setCid(query.getInt("cid"));
               p.setCreateDate(query.getString("createDate"));
               ProductImageService productImageService=new ProductImageServiceImpl();
               /**
                * 封装图片
                */
               List<ProductImage> images = productImageService.getIamges(p.getId());
               p.setImages(images);
               productsArr.add(p);
           }
       }catch(Exception e){
           e.printStackTrace();
       }
        JDBCUtil.getclose();
        return productsArr;
    }

    @Override
    public List<Product> byKeyWordGetProduct(String key) {
        String sql="select * from product where name like '%"+key+"%' ";
        ResultSet query = productDao.query(sql, null);
        ArrayList<Product> productsArr=new ArrayList<>();
        ProductImageService productImageService=new ProductImageServiceImpl();
        ReviewServiceImpl reviewService=new ReviewServiceImpl();
        try{
            while(query.next()){
                Product p=new Product();
                p.setId(query.getInt("id"));
                p.setName(query.getString("name"));
                p.setSubTitle(query.getString("subTitle"));
                p.setOrignalPrice(query.getFloat("orignalPrice"));
                p.setPromotePrice(query.getFloat("promotePrice"));
                p.setStock(query.getInt("stock"));
                p.setCid(query.getInt("cid"));
                p.setCreateDate(query.getString("createDate"));
                /**
                 * 封装图片
                 */
                List<ProductImage> iamges = productImageService.getIamges(p.getId());
                p.setImages(iamges);
                /**
                 * 封装评论
                 */
                List<Review> reviews = reviewService.getReviews(p.getId());
                p.setReviews(reviews);
                /**
                 * 添加所有关键字中的产品
                 */
                productsArr.add(p);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return productsArr;
    }

    @Override
    public Product byPidGetProduct(int pid) {
        String sql="select * from product where id=? ";
        ResultSet query = productDao.query(sql, pid);
        ProductImageService productImageService=new ProductImageServiceImpl();
        ReviewServiceImpl reviewService=new ReviewServiceImpl();
        Product p=new Product();
        try{
            while(query.next()){
                p.setId(query.getInt("id"));
                p.setName(query.getString("name"));
                p.setSubTitle(query.getString("subTitle"));
                p.setOrignalPrice(query.getFloat("orignalPrice"));
                p.setPromotePrice(query.getFloat("promotePrice"));
                p.setStock(query.getInt("stock"));
                p.setCid(query.getInt("cid"));
                p.setCreateDate(query.getString("createDate"));
                /**
                 * 封装图片
                 */
                List<ProductImage> iamges = productImageService.getIamges(p.getId());
                p.setImages(iamges);
                /**
                 * 封装评论
                 */
                List<Review> reviews = reviewService.getReviews(p.getId());
                p.setReviews(reviews);
                /**
                 * 添加所有关键字中的产品
                 */
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        JDBCUtil.getclose();
        return p;
    }
}
