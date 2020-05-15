package lrm.com.entity;

import java.util.List;

/**
 * 归档实体
 */
public class PigeonHole {
    String dateTime;
    List<Blog> blogs;

    public PigeonHole() {
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
