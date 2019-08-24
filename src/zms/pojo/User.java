package zms.pojo;

import java.util.List;

public class User {
    /**
     * 用户id
     */
    private int id_l;
    /**
     * 用户帐号
     */
    private String username_l;
    /**
     * 用户密码
     */
    private String password_l;
    /**
     * 用户手机号
     */
    private String phone_l;
    /**
     * 用户邮箱
     */
    private String email_l;
    /**
     * 用户所有评论
     */
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public int getId_l() {
        return id_l;
    }

    public void setId_l(int id_l) {
        this.id_l = id_l;
    }

    public String getUsername_l() {
        return username_l;
    }

    public void setUsername_l(String username_l) {
        this.username_l = username_l;
    }

    public String getPassword_l() {
        return password_l;
    }

    public void setPassword_l(String password_l) {
        this.password_l = password_l;
    }

    public String getPhone_l() {
        return phone_l;
    }

    public void setPhone_l(String phone_l) {
        this.phone_l = phone_l;
    }

    public String getEmail_l() {
        return email_l;
    }

    public void setEmail_l(String email_l) {
        this.email_l = email_l;
    }
}
