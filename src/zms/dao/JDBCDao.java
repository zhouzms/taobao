package zms.dao;

import java.sql.ResultSet;

/**
 * 提供jdbc底层操作
 * @param <T> 任意对象
 */
public interface JDBCDao<T> {
    /**
     * 增删改方法
     */
    public Boolean insDelUpd(String sql, Object... args);
    /**
     * 返回resultset对象
     */
    public ResultSet query(String sql, Object... args);
}
