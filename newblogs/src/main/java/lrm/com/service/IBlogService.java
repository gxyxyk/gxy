package lrm.com.service;

import lrm.com.entity.Blog;

import java.util.List;

public interface IBlogService {
    Integer add(Blog blog);
    List<Blog> listblog(Integer page,Integer size);
    Blog blog(Integer id);
    Blog blogUpdate(Integer id);
    Integer blogUpdate_list(Blog blog);
    List<Blog> listsDimBlog(Integer typeId,String title,Boolean recommend,Integer page, Integer size);
    List<Blog> listType(Integer id,Integer page, Integer size);
    List<Blog> listTag(Integer id, Integer page, Integer size);
    List<Blog> blogRecommend();
    List<Blog> listsIndexBlog(String title,Integer page,Integer size);
    List<Blog> blogNum(String title);
    void logViews(Integer id);
}
