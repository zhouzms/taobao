package zms.service;

import zms.pojo.Review;

import java.util.List;

public interface ReviewService {
    /**
     * @param proid  产品id
     * @return 某一个产品所有评论
     */
    List<Review> getReviews(int proid);
}
