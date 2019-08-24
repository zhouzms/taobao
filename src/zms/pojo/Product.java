package zms.pojo;

import java.util.List;

/**
 * @author 19448
 */
public class Product {
    /**
     * 产品id
     */
    private int id;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品描述
     */
    private String subTitle;
    /**
     * 产品原始价格
     */
    private float orignalPrice;
    /**
     * 产品减价后的价格
     */
    private float promotePrice;
    /**
     * 产品库存
     */
    private int stock;
    /**
     * 产品属于哪个类别
     */
    private int cid;
    /**
     * 产品创建时间
     */
    private String createDate;
    /**
     * 一个产品有多张图片
     */
    private List<ProductImage> images;
    /**
     * 一个产品对应多个评论
     */
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public float getOrignalPrice() {
        return orignalPrice;
    }

    public void setOrignalPrice(float orignalPrice) {
        this.orignalPrice = orignalPrice;
    }

    public float getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(float promotePrice) {
        this.promotePrice = promotePrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
