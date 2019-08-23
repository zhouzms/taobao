package zms.serviceImpl;

import zms.dao.LoginDao;
import zms.daoImpl.LoginDaoImpl;
import zms.pojo.Product;
import zms.service.ProductService;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 19448
 */
public class ProductServiceImpl implements ProductService {
    LoginDao productDao=new LoginDaoImpl();
    ArrayList<Product> productsArr=new ArrayList<>();
    @Override
    public List<Product> pros(int categoryId) {
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
               productsArr.add(p);
           }
       }catch(Exception e){
           e.printStackTrace();
       }
        return productsArr;
    }
}
