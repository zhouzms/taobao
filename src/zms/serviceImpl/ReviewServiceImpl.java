package zms.serviceImpl;

import zms.dao.LoginDao;
import zms.daoImpl.LoginDaoImpl;
import zms.pojo.Review;
import zms.service.ReviewService;
import zms.util.JDBCUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    LoginDao reviewDao=new LoginDaoImpl();
    @Override
    public List<Review> getReviews(int proid) {
        String sql="select * from review where pid='"+proid+"'";
        ResultSet query = reviewDao.query(sql, null);
        ArrayList<Review> reviews=new ArrayList<>();
        try {
            while (query.next()){
                Review review=new Review();
                review.setId(query.getInt("id"));
                review.setContent(query.getString("content"));
                review.setId_l(query.getInt("id_l"));
                review.setPid(query.getInt("pid"));
                review.setCreateDate(query.getString("createDate"));
                reviews.add(review);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        JDBCUtil.getclose();
        return reviews;
    }
}
