package zms.service;

/**
 * @author 19448
 */
public interface CertService {
    /**
     将数据插入到购物车表中
     * @param uid 用户id
     * @param pid 产品id
     * @param num 产品数量
     * @param createTime 创建时间
     * @return 是否插入
     */
     Boolean insertCert(int uid,int pid,int num,String createTime);

    /**
     * 查看是否有相同的产品
     * 有则数量加一
     * @param pid
     * @param num
     * @return
     */
     Boolean updateCert(int pid,int num);

    /**
     * 获取某个产品的数量
     * @param pid
     * @return
     */
     int getNum(int pid);

}
