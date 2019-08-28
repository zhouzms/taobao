package zms.serviceImpl;
import zms.daoImpl.LoginDaoImpl;

import zms.pojo.User;
import zms.service.UserService;
import zms.util.AJaxResult;
import zms.util.JDBCUtil;
import zms.util.ResultException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 19448
 */
public class UserServiceImpl implements UserService {
    AJaxResult aJaxResult=new AJaxResult();
    User user=new User();
    LoginDaoImpl<User> Dao=new LoginDaoImpl<>();
    @Override
    public Boolean login(String username, String password) {
        if(username.equals("")||password.equals("")){
            try {
                throw new ResultException("帐号密码不为空");
            } catch (ResultException e) {
                e.printStackTrace();
            }
            aJaxResult.setFlag(false);
            aJaxResult.setMsg("帐号密码为空");
            return false;
        }else {
            //执行带参查询
           String sql="select * from login where username_l=? and password_l=? or phone_l=? and password_l=? or email_l=? and password_l=? ";
            ResultSet query = Dao.query(sql,username,password,username,password,username,password);
            try {
                    if(query.next()==false){
                        aJaxResult.setFlag(false);
                        aJaxResult.setMsg("帐号密码不正确!");
                        return false;
                    }else {
                    //已经指向了第一条记录
                        user.setId_l(query.getInt("id_l"));
                    user.setUsername_l(query.getString("username_l"));
                    user.setPassword_l(query.getString("password_l"));
                    user.setPhone_l(query.getString("phone_l"));
                    user.setEmail_l(query.getString("email_l"));
                    aJaxResult.setFlag(true);
                    aJaxResult.setMsg("登陆成功");
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        JDBCUtil.getclose();
        return true;
    }

    /**
     * 手机号注册
     * @param phonenum 手机号码
     * @param password 密码
     * @param code 输入的验证码
     * @param getrandom 随机验证码
     * @return 是否插入成功
     */
    @Override
    public Boolean phoneRegister(String phonenum, String password,String code,String getrandom) {
        if(phonenum.equals("")||password.equals("")){
            try {
                throw new ResultException("手机号,密码不为空");
            } catch (ResultException e) {
                e.printStackTrace();
            }
            aJaxResult.setFlag(false);
            aJaxResult.setMsg("帐号密码为空");
            return false;
        }else
        {
           if(!code.equals(getrandom)){
               //验证码不正确
               aJaxResult.setFlag(false);
               aJaxResult.setMsg("输入的验证码不正确");
               return false;
           } else {
               //执行插入操作
               String sql="insert into login(phone_l,password_l) values(?,?)";
               Boolean aBoolean = Dao.insDelUpd(sql, phonenum, password);
               if(aBoolean){
                   return true;
               }else {
                   return false;
               }
           }
        }
    }

    @Override
    public Boolean emailRegister(String emailnum, String password, String emailcode, String getrandomemail) {
        if("".equals(emailnum)||"".equals(password)){
            try {
                throw new ResultException("邮箱号,密码不为空");
            } catch (ResultException e) {
                e.printStackTrace();
            }
            aJaxResult.setFlag(false);
            aJaxResult.setMsg("邮箱号,密码不为空");
            return false;
        }else
        {
            if(!emailcode.equalsIgnoreCase(getrandomemail)){
                //验证码不正确
                aJaxResult.setFlag(false);
                aJaxResult.setMsg("输入邮箱的验证码不正确");
                return false;
            } else {
                //执行插入操作
                String sql="insert into login(email_l,password_l) values(?,?)";
                Boolean aBoolean = Dao.insDelUpd(sql, emailnum, password);
                if(aBoolean){
                    return true;
                }else {
                    return false;
                }
            }
        }
    }

    /**
     * 结果返回
     * @return
     */
    public AJaxResult getajax(){
        return aJaxResult;
    }

    /**
     * 返回user
     * @return
     */
    public User getuser(){
        return user;
    }
}
