package zms.daoImpl;

import zms.dao.LoginDao;
import zms.util.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public  class LoginDaoImpl<T> implements LoginDao {
    PreparedStatement pst=null;
    ResultSet resultSet= JDBCUtil.set;
    //参数遍历的方法
    private void forInsDelUpd(String sql, Object... args){
        if(args==null){
            return;
        }
        for(int i=0;i<args.length;i++){
            try {
                pst.setString(i+1,args[i].toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //增删改的方法
    @Override
    public Boolean insDelUpd(String sql, Object... args) {
        pst=JDBCUtil.getPst(sql);
        forInsDelUpd(sql,args);
        try {
            int i = pst.executeUpdate();
            if(i<0){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtil.getclose();
        return true;
    }
    @Override
    public ResultSet query(String sql, Object... args) {
        pst=JDBCUtil.getPst(sql);
        forInsDelUpd(sql,args);
        try {
            resultSet = pst.executeQuery();
        } catch (SQLException e) {

        }
        return resultSet;
    }
}
