package zms.test;
import zms.serviceImpl.UserServiceImpl;


public class TestJdbcClass {
    public static void main(String[] args) {
        UserServiceImpl userService=new UserServiceImpl();
        Boolean admin = userService.login("admin", "123456");
        System.out.println(admin);
    }
}
