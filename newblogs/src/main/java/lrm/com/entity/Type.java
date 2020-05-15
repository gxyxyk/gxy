package lrm.com.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型实体
 */
public class Type {
    //类型id
    private Integer id;
    //类型姓名
    private String name;

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    //一个类型对应多个博客
    private List<Blog> blogs = new ArrayList<Blog>();
    public Type() {
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", tname='" + name + '\'' +
                ", blogs=" + blogs +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
