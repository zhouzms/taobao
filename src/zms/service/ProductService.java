package zms.service;

import zms.pojo.Product;

import java.util.List;

/**
 * 对产品表进行操作
 */
public interface ProductService {
    /**
     * 通过种类的某一个id获取所有的产品
     * @param categoryId 种类id
     * @return 某一类所有产品
     */
    List<Product> pros(int categoryId);
}
