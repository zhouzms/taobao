package zms.util;

import zms.pojo.Product;

import java.util.*;

/**
 * 购物车
 * @author 19448
 */
public class CertShop {
    /**
     * 商品集合
     */
    private HashMap<Product,Integer> cert;

    public HashMap<Product, Integer> getCert() {
        return cert;
    }
    public void setCert(HashMap<Product, Integer> cert) {
        this.cert = cert;
    }

    /**
     * 商品总的价格
     */
    private double totalPrice;
    /**
     * 初始化购物车
     */
    public CertShop(){
        this.cert=new HashMap<>();
        this.totalPrice=0;
    }
    /**
     * 增加商品
     */
    public void addProduct(Product pro,int count){
        //遍历购物车
        Set<Product> products = cert.keySet();
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()){
            Product next = iterator.next();
            if(next.getName().equals(pro.getName())){
                Integer integer = cert.get(next);
                iterator.remove();
                count=integer+count;
            }
        }
        cert.put(pro,count);
        getFlushPrice();
        //刷新总的价格
    }

    /**
     * 删除商品
     */
    public void removeProduct(Product pro){
        cert.remove(pro);
        getFlushPrice();
        //刷新总的价格
    }

    /**
     * 计算价格
     * @return 总价
     */
    private double getFlushPrice(){
        double sum=0;
        Set<Map.Entry<Product, Integer>> entries = cert.entrySet();
        Iterator<Map.Entry<Product, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<Product, Integer> next = iterator.next();
            Product key = next.getKey();
            Integer num = next.getValue();
            sum=sum+key.getPromotePrice()*num;
        }
        this.setTotalPrice(sum);
        return this.getTotalPrice();
    }
    public double getProPrice(Product pro){
        double s=0;
        if(cert.containsKey(pro)){
            Integer num = cert.get(pro);
            Set<Product> products = cert.keySet();
           for(Product p:products){
               if(p.equals(pro)){
                  s=p.getPromotePrice()*num;
               }
           }
        }
        return s;
    }

    /**
     * @return 购物车中商品个数
     */
    public int getSize(){
        return cert.size();
    }

    /**
     * 清空购物车
     */
    private void getClear(){
         cert.clear();
    }

    /**
     * 总价set end get
     */
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
