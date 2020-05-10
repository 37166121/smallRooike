package cn.edu.cqcet.yd1701.team11.smallrooike.beans;

/**
 * Created by Intellij IDEA.
 *
 * @author 22510
 * @create 2020/4/30 20:10 星期四
 */
public class CallBean {
    private String name;
    private String url;
    private String tel;

    @Override
    public String toString() {
        return "name:"+name+
                "\nurl:"+url+
                "\ntel:"+tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
