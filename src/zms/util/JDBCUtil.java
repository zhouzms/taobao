package zms.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    //数据库连接对象
    public static Connection con;
    public static PreparedStatement pst;//欲编译对象
    public static ResultSet set;//结果对象
    /**
     * 获取用户名，密码
     */
    static String username,url,password;
    /**
     * 获取连接对象
     */
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //加载配置文件中的信息
        Properties properties=new Properties();
        InputStream resourceAsStream = JDBCUtil.class.getClassLoader().getResourceAsStream("JDBC.properties");
        try {
            properties.load(resourceAsStream);
            url=properties.getProperty("JDBC_URL");
            username=properties.getProperty("JDBC_USERNAME");
            password=properties.getProperty("JDBC_PASSWORD");
        } catch (IOException e) {
            e.printStackTrace();
        }
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return con;
    }
    /**
     * 查询操作
     */
    public static PreparedStatement getPst(String sql){
        con=getConnection();
        try {
            pst=con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pst;
    }
    /**
     * 关闭连接
     */
    public static void getclose(){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(pst!=null){
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(set!=null){
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
