package zms.pojo;

public class ProductImage {
    /**
     * 图片id
     */
    private int id;
    /**
     * 图片属于那个产品
     */
    private int pid;
    /**
     * 图片描述
     */
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
