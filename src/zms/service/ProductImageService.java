package zms.service;

import zms.pojo.ProductImage;
import zms.pojo.Review;

import java.util.List;

/**
 * @author 19448
 */
public interface ProductImageService {
    /**
     *
     * @param proid 产品id
     * @return  某一个产品所有图片
     */
     List<ProductImage> getIamges(int proid);
}
