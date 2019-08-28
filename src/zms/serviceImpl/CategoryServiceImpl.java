package zms.serviceImpl;

import zms.dao.LoginDao;
import zms.daoImpl.LoginDaoImpl;
import zms.pojo.Category;
import zms.service.CategoryService;
import zms.util.JDBCUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 19448
 */
public class CategoryServiceImpl implements CategoryService {
    /**
     * 调用dao层方法查询数据库
     */
    LoginDao categoryDao=new LoginDaoImpl();
    ArrayList<Category> categories;
    @Override
    public List<Category> getCategory() {
        String sql="select * from category";
        ResultSet query = categoryDao.query(sql, null);
        categories=new ArrayList<>();
        try{
            while (query.next()){
                Category category=new Category();
                category.setId(query.getInt("id"));
                category.setName(query.getString("name"));
                categories.add(category);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        JDBCUtil.getclose();
        return categories;
    }

    @Override
    public Category getCategoryItem(int id) {
        String sql="select * from category where id=?";
        ResultSet query = categoryDao.query(sql, id);
        Category category=null;
        try {
           while (query.next()){
               category =new Category();
               category.setId(query.getInt("id"));
               category.setName(query.getString("name"));
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        JDBCUtil.getclose();
        return category;
    }
}
