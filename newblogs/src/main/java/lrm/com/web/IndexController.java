package lrm.com.web;

import com.github.pagehelper.PageInfo;
import lrm.com.entity.Comment;
import lrm.com.entity.PigeonHole;
import lrm.com.entity.Tag;
import lrm.com.entity.Type;
import lrm.com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ITagService tagService;
    @Autowired
    private ITypeService typeService;
    @Autowired
    private IPigeonHoleService pigeonHoleService;
    @Autowired
    private IControllerService controllerService;
    /**
     * 首页列表页
     * @return
     */
    @RequestMapping("")
    public String index(ModelMap modelMap,@RequestParam(name="page",required = true,defaultValue = "1") Integer page,
                        @RequestParam(name = "size",required = true,defaultValue = "7")Integer size){
        modelMap.addAttribute("types",typeService.list());
        modelMap.addAttribute("tags",tagService.list());
        modelMap.addAttribute("recommend",blogService.blogRecommend());
        modelMap.addAttribute("num",num());
        modelMap.addAttribute("blogs",new PageInfo(blogService.listblog(page,size)));
        return "/index";
    }

    /**
     * index列表页
     * @return
     */
    @RequestMapping("index")
    public String indexlist(ModelMap modelMap,@RequestParam(name="page",required = true,defaultValue = "1") Integer page,
                            @RequestParam(name = "size",required = true,defaultValue = "7")Integer size){
        modelMap.addAttribute("types",typeService.list());
        modelMap.addAttribute("tags",tagService.list());
        modelMap.addAttribute("num",num());
        modelMap.addAttribute("recommend",blogService.blogRecommend());
        modelMap.addAttribute("blogs",new PageInfo(blogService.listblog(page,size)));
        return "/index";
    }
    /**
     * index列表页
     * @return
     */
    @RequestMapping("/index/search")
    public String indexlistSearch(ModelMap modelMap,@RequestParam(name="page",required = true,defaultValue = "1") Integer page,
                            @RequestParam(name = "size",required = true,defaultValue = "7")Integer size,String title){
        modelMap.addAttribute("types",typeService.list());
        modelMap.addAttribute("tags",tagService.list());
        modelMap.addAttribute("recommend",blogService.blogRecommend());
        modelMap.addAttribute("blogs",new PageInfo(blogService.listsIndexBlog(title,page,size)));
        if (title==null||title==""){
            modelMap.addAttribute("num",num());
        }else{
            modelMap.addAttribute("num",blogService.blogNum(title).size());
        }
        return "index :: indexList";
    }
    /**
     * index列表页
     * @return
     */
    @RequestMapping("index/sousuo")
    public String indexlistUp(ModelMap modelMap,@RequestParam(name="page",required = true,defaultValue = "1") Integer page,
                                  @RequestParam(name = "size",required = true,defaultValue = "7")Integer size,String title){
        modelMap.addAttribute("types",typeService.list());
        modelMap.addAttribute("tags",tagService.list());
        modelMap.addAttribute("recommend",blogService.blogRecommend());
        modelMap.addAttribute("blogs",new PageInfo(blogService.listsIndexBlog(title,page,size)));
        modelMap.addAttribute("num",blogService.blogNum(title).size());
        modelMap.addAttribute("title",title);
        return "index";
    }
    /**
     * blog页
     * @return
     */
    @RequestMapping("/blog/{id}")
    public String blogList(ModelMap modelMap,@PathVariable Integer id){
        blogService.logViews(id);
        modelMap.addAttribute("blog",blogService.blog(id));
        return "blog";
    }
    @RequestMapping("/blog/search")
    public String blogSearch(ModelMap modelMap, Comment comment,Integer bid){
        if (comment.getRankid()==null||comment.getRankid()==0){
            comment.setNickname("张三");
        }
        controllerService.addComment(comment);
        controllerService.addCb(bid,comment.getId());
        modelMap.addAttribute("blog",blogService.blog(bid));
        return "blog :: commentList";
    }

    /**
     * 标签列表页
     * @param modelMap
     * @return
     */
    @GetMapping("/tags/{id}")
    public String tagslist(ModelMap modelMap,@PathVariable Integer id,@RequestParam(name="page",required = true,defaultValue = "1") Integer page,
                           @RequestParam(name = "size",required = true,defaultValue = "7")Integer size){
        List<Tag> list = tagService.list();
        modelMap.addAttribute("tags",list);
        if(id==-1){
            id=list.get(0).getId();
        }
        modelMap.addAttribute("index",id);
        modelMap.addAttribute("blogs", new PageInfo(blogService.listTag(id,page,size)));
        return "tags";
    }

    /**
     * 类型列表页
     * @param modelMap
     * @return
     */
    @GetMapping("/types/{id}")
    public String typeslist(ModelMap modelMap,@PathVariable Integer id,@RequestParam(name="page",required = true,defaultValue = "1") Integer page,
                            @RequestParam(name = "size",required = true,defaultValue = "7")Integer size){
        List<Type> list = typeService.list();
        modelMap.addAttribute("types",list);
        if(id==-1){
            id=list.get(0).getId();
        }
        modelMap.addAttribute("index",id);
        modelMap.addAttribute("blogs", new PageInfo(blogService.listType(id,page,size)));
        return "types";
    }
    /**
     * 归档列表页
     * @return
     */
    @GetMapping("archives")
    public String archiveslist(ModelMap modelMap){
        List<PigeonHole> pigeonHole = pigeonHoleService.list();
        modelMap.addAttribute("pigeonHole",pigeonHole);
        return "archives";
    }

    /**
     * 开发者介绍页
     * @param modelMap
     * @return
     */
    @GetMapping("about")
    public String about(ModelMap modelMap){
        return "about";
    }
    private Integer num(){
        Integer i = 0;
        for (Type type : typeService.list()) {
            i = i + type.getBlogs().size();
        }
        return i;
    }
}
