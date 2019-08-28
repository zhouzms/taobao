package zms.serviceImpl;

import zms.dao.LoginDao;
import zms.daoImpl.LoginDaoImpl;
import zms.pojo.ProductImage;
import zms.service.ProductImageService;
import zms.util.JDBCUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductImageServiceImpl implements ProductImageService {
    LoginDao productImage=new LoginDaoImpl();
    @Override
    public List<ProductImage> getIamges(int proid) {
        ArrayList<ProductImage> productImageArrayList=new ArrayList<>();
        String sql="select * from productimage where pid=?";
        ResultSet query = productImage.query(sql, proid);
        try {
            while (query.next()){
                ProductImage productImag=new ProductImage();
                productImag.setId(query.getInt("id"));
                productImag.setPid(query.getInt("pid"));
                productImag.setType(query.getString("type"));
                productImageArrayList.add(productImag);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        JDBCUtil.getclose();
        return productImageArrayList;
    }
}
