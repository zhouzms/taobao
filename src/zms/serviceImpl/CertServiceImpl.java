package zms.serviceImpl;

import zms.dao.LoginDao;
import zms.daoImpl.LoginDaoImpl;
import zms.service.CertService;
import zms.util.JDBCUtil;

import java.sql.ResultSet;

/**
 * @author 19448
 */
public class CertServiceImpl implements CertService {
    LoginDao certDao=new LoginDaoImpl();
    @Override
    public Boolean insertCert(int uid, int pid, int num, String createTime) {
        String sql="insert into cert(uid,pid,num,status,createTime) values(?,?,?,0,?)";
        Boolean aBoolean = certDao.insDelUpd(sql, uid, pid, num, createTime);
        if(aBoolean){
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateCert(int pid, int num) {
        String sql="update cert set num=? where pid=?";
        Boolean aBoolean = certDao.insDelUpd(sql, num, pid);
        if(aBoolean){
            return true;
        }
        return false;
    }

    @Override
    public int getNum(int pid) {
        String sql="select num form cert where pid=?";
        int n=0;
        ResultSet query = certDao.query(sql, pid);
        try {
            if(query.next()){
                n = query.getInt("num");
                //不为空
            }else {
                n=0;
            }
        }catch (Exception e){
            n=0;
        }
        JDBCUtil.getclose();
        return n;
    }
}
