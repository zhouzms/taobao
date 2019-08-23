package zms.service;

import zms.pojo.Category;

import java.util.List;

/**
 * 对商品种类表进行操作
 */
public interface CategoryService {
    /**
     * 获取所有的种类
     * @return 所有种类
     */
     List<Category> getCategory();

    /**
     * 通过id获取商品种类
     * @param id 商品种类id
     * @return  某一个种类
     */
     Category getCategoryItem(int id);
}
