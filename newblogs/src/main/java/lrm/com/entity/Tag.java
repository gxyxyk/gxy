package lrm.com.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *标签实体
 */
public class Tag {
    //标签id
    private Integer id;
    //标签姓名
    private String name;

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    //一个标签对应多个博客
    private List<Blog> blogs = new ArrayList<Blog>()
            ;

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogs=" + blogs +
                '}';
    }

    public Tag() {
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
