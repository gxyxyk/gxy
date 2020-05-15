package lrm.com.web;

import com.github.pagehelper.PageInfo;
import lrm.com.entity.Blog;
import lrm.com.entity.Tag;
import lrm.com.entity.User;
import lrm.com.service.IBlogService;
import lrm.com.service.ITagService;
import lrm.com.service.ITypeService;
import lrm.com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("admin")
public class BlogsController {
    private static final String INPUT="admin/blogs-input";
    private static final String LIST ="admin/blogs";
    private static final String REDIRECT_LIST ="redirect:/admin/blogs";
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ITagService tagService;
    @Autowired
    private ITypeService typeService;
    @Autowired
    private IUserService userService;
    @Autowired
    HttpServletRequest request;
    @RequestMapping("/blogs")
    public String blogsPage(ModelMap modelMap,@ModelAttribute("messges") String messges,@RequestParam(name="page",required = true,defaultValue = "1") Integer page,
                            @RequestParam(name = "size",required = true,defaultValue = "5") Integer size ){
        modelMap.addAttribute("user",userSession());
        modelMap.addAttribute("messges",messges);
        modelMap.addAttribute("typelist",typeService.list());
        PageInfo pageInfo = new PageInfo(blogService.listblog(page,size));
        modelMap.addAttribute("listblog",pageInfo);
        return LIST;
    }
    @RequestMapping("/blogs/search")
    public String blogsPageSearch(ModelMap modelMap,@ModelAttribute("messges") String messges,@RequestParam(name="page",required = true,defaultValue = "1") Integer page,
                            @RequestParam(name = "size",required = true,defaultValue = "5") Integer size,String title,Integer typeId, Boolean recommend){
        modelMap.addAttribute("user",userSession());
        modelMap.addAttribute("messges",messges);
        modelMap.addAttribute("typelist",typeService.list());
        PageInfo pageInfo = new PageInfo(blogService.listsDimBlog(typeId,title,recommend,page,size));
        modelMap.addAttribute("listblog",pageInfo);
        return LIST+" :: blogList";
    }
    @RequestMapping("/blogs-input")
    public String blogsInputPage(ModelMap modelMap){
        modelMap.addAttribute("taglist",tagService.list());
        modelMap.addAttribute("typelist",typeService.list());
        modelMap.addAttribute("user",userSession());
        return "admin/blogs-input";
    }
    @RequestMapping("blogs-index")
    public String blogsIndexPage(ModelMap modelMap){
        modelMap.addAttribute("user",userSession());
        return "admin/index";
    }

    /**
     * 修改跳转
     * @param id
     * @param attributes
     * @param modelMap
     * @return
     */
    @RequestMapping("blogs-update")
    public String blogsUpdate(Integer id, RedirectAttributes attributes,ModelMap modelMap){
        Blog blog = blogService.blogUpdate(id);
        if (blog==null){
            attributes.addFlashAttribute("messges","请检查数据库是否连接！");
            return REDIRECT_LIST ;
        }else{
            modelMap.addAttribute("blog",blog);
            modelMap.addAttribute("taglist",tagService.list());
            modelMap.addAttribute("typelist",typeService.list());
            modelMap.addAttribute("user",userSession());
            return "admin/blogs-update";
        }
    }
    /**
     * blogs-input表单提交
     * @param blog
     * @param typeid
     * @param tag
     * @param attributes
     * @param modelMap
     * @return
     */
    @PostMapping("blogs-add")
    public String Post(Blog blog, @RequestParam Integer typeid, @RequestParam Integer [] tag, RedirectAttributes attributes,ModelMap modelMap){
        modelMap.addAttribute("taglist",tagService.list());
        modelMap.addAttribute("typelist",typeService.list());
        if (blogService.add(blog)!=0){
            //添加类型
            typeService.addType_Blog(blog.getId(),typeid);
            for (Integer integer : tag) {
                tagService.addTag_Blog(blog.getId(),integer);
            }
        }else{
            attributes.addFlashAttribute("messges","对不起发布博客失败！");
        }
        userService.userBlogadd(blog.getId(),((User)request.getSession().getAttribute("user")).getId());
        return REDIRECT_LIST;
    }
    @PostMapping("blogs-update-input")
    public String blos_Update(Blog blog,@RequestParam Integer typeid, @RequestParam Integer [] tag,RedirectAttributes attributes){
        if(blogService.blogUpdate_list(blog)!=null){
            tagService.deleteOnetag(blog.getId());
            typeService.deleteOnetype(blog.getId());
            typeService.addType_Blog(blog.getId(),typeid);
            for (Integer integer : tag) {
                tagService.addTag_Blog(blog.getId(),integer);
            }
            attributes.addFlashAttribute("messges","博客修改成功！");
        }else{
            attributes.addFlashAttribute("messges","对不起博客修改失败！");
        }
        return REDIRECT_LIST;
    }
    /**
     * 获取登录的信息
     * @return
     */
    public User userSession(){
        return  (User)request.getSession().getAttribute("user");
    }
}
