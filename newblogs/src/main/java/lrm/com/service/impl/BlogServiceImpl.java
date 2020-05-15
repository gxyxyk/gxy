package lrm.com.service.impl;

import com.github.pagehelper.PageHelper;
import lrm.com.NotFoundException;
import lrm.com.entity.Blog;
import lrm.com.entity.Tag;
import lrm.com.mapper.IBlogsMapper;
import lrm.com.service.IBlogService;
import lrm.com.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements IBlogService {
    @Autowired
    IBlogsMapper blogsMapper;

    /**
     * blog添加方法
     * @param blog
     * @return
     */
    @Override
    public Integer add(Blog blog) {
        return blogsMapper.add(blog);
    }

    /**
     * 查询全部方法
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Blog> listblog(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return blogsMapper.listsBlog();
    }

    /**
     * 查询单个blog对象
     * @param id
     * @return
     */
    @Override
    public Blog blog(Integer id) {
        Blog blog = blogsMapper.blog(id);
        if (blog==null){
            throw  new NotFoundException("该博客不存在！");
        }
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        return blog;
    }

    /**
     * 修改的单个对象
     * @param id
     * @return
     */
    @Override
    public Blog blogUpdate(Integer id) {
        try {
            return blogsMapper.blog(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer blogUpdate_list(Blog blog) {
        return blogsMapper.update(blog);
    }

    /**
     * 3条件模糊查询处理
     * @param typeId
     * @param title
     * @param recommend
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Blog> listsDimBlog(Integer typeId, String title, Boolean recommend,Integer page, Integer size) {
        if (title!=null&&title!=""){
            if (typeId!=null){
                if(recommend!=false){
                    //模糊查询三种条件
                    PageHelper.startPage(page,size);
                    return blogsMapper.liststypeBlog(typeId,title,true);
                }else{
                    //模糊查询title加上recommend
                    PageHelper.startPage(page,size);
                    return blogsMapper.liststypeBlog(typeId,title,false);
                }
            }else{
                if (recommend!=false){
                    //模糊查询title+推荐
                    PageHelper.startPage(page,size);
                    return blogsMapper.listsnotypeBlog(title,true);
                }else{
                    //模糊查询title
                    PageHelper.startPage(page,size);
                    return blogsMapper.listsnotypeBlog(title,false);
                }
            }
        }else if (typeId!=null){
            if (recommend!=false){
                PageHelper.startPage(page,size);
                return  blogsMapper.liststypeBlog(typeId,"",true);
            }else{
                PageHelper.startPage(page,size);
                return  blogsMapper.liststypeBlog(typeId,"",false);
            }
        }else if (recommend!=false){
            PageHelper.startPage(page,size);
            return blogsMapper.listsnotypeBlog("",true);
        }else{
            PageHelper.startPage(page,size);
            return blogsMapper.listsBlog();
        }
    }

    @Override
    public List<Blog> listType(Integer id, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return blogsMapper.typeBlog(id);
    }

    @Override
    public List<Blog> listTag(Integer id, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return blogsMapper.tagBlog(id);
    }

    @Override
    public List<Blog> blogRecommend() {
        return blogsMapper.blogRecommend();
    }

    @Override
    public List<Blog> listsIndexBlog(String title, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return blogsMapper.listsIndexBlog(title);
    }

    @Override
    public List<Blog> blogNum(String title) {
        return blogsMapper.listsIndexBlog(title);
    }

    @Override
    public void logViews(Integer id) {
        blogsMapper.blogViews(id);
    }
}
