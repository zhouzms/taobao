package zms.pojo;

import java.util.List;

/**
 * @author 19448
 */
public class Category {
    /**
     * 商品种类id
     */
    private int id;
    /**
     * 商品种类名字
     */
    private String name;

    /**
     * 一种类有多个产品
     */
    private List<Product> arrproduct;

    public List<Product> getArrproduct() {
        return arrproduct;
    }

    public void setArrproduct(List<Product> arrproduct) {
        this.arrproduct = arrproduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
