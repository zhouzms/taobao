package zms.pojo;

public class Review {
    /**
     * 评论id
     */
    private int id;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 用户id
     */
    private int id_l;

    public int getId_l() {
        return id_l;
    }

    public void setId_l(int id_l) {
        this.id_l = id_l;
    }

    /**
     * 产品id
     */
    private int pid;
    /**
     * 评论时间
     */
    private String createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
