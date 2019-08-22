package zms.service;

import zms.pojo.User;

public interface UserService {
    /**
     * 登陆验证 帐号密码登陆
     * @param username 前端的用户名
     * @param password 前端的密码
     * @return true?false
     */
    public Boolean login(String username, String password);
    /**
     * 用户手机注册
     */
     Boolean phoneRegister(String phonenum, String password, String code, String getrandom);
    /**
     * 用户邮箱注册
     */
    Boolean emailRegister(String emailnum, String password, String emailcode, String getrandomemail);
}
