package lrm.com.web;

import com.github.pagehelper.PageInfo;
import lrm.com.entity.User;
import lrm.com.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 类型控制层
 */
@Controller
@RequestMapping("admin")
public class TagsController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    ITagService tagService;

    /**
     * tag列表
     * @param modelMap
     * @param page
     * @param size
     * @return
     */
    @GetMapping("tags")
    public String typesPage(ModelMap modelMap,@RequestParam(name="page",required = true,defaultValue = "1") Integer page,
                            @RequestParam(name = "size",required = true,defaultValue = "6") Integer size ){
        PageInfo pageInfo;
        List<Integer> pages = new ArrayList<Integer>();
        pageInfo = new PageInfo(tagService.listTag(page,size));
        modelMap.addAttribute("tags",pageInfo);
        modelMap.addAttribute("user",userSession());
        return "admin/tags";
    }

    /**
     * type修改方法
     * @param modelMap
     * @param attributes
     * @return
     */
    @PostMapping("tagupdate")
    public String tagupdate(ModelMap modelMap, @RequestParam String name,@RequestParam Integer id,RedirectAttributes attributes){
        modelMap.addAttribute("user",userSession());
        if (tagService.updateTag(name,id)!=1){
            attributes.addFlashAttribute("messges","修改失败！请检查类型是否重名");
            attributes.addFlashAttribute("id",id);
            return "redirect:/admin/tag-update";
        }else{
            return "redirect:/admin/tags";
        }
    }

    /**
     * 通过id获取单个tag对象跳转修改页
     * @param modelMap
     * @param id
     * @return
     */
    @RequestMapping("tag-update")
    public String tagOne(ModelMap modelMap,@ModelAttribute("messges") String messges,@ModelAttribute("id")Integer id){
        modelMap.addAttribute("user",userSession());
        modelMap.addAttribute("messges",messges);
        modelMap.addAttribute("tags",tagService.Onetag(id));
        return "admin/tags-update";
    }
    /**
     * tag添加跳转
     * @param modelMap
     * @param messges
     * @return
     */
    @GetMapping("tags-input")
    public String tagInputPage(ModelMap modelMap,@ModelAttribute("messges") String messges){
        modelMap.addAttribute("user",userSession());
        modelMap.addAttribute("messges",messges);
        return "admin/tags-input";
    }

    /**
     * 删除指定的类型
     * @param id
     * @return
     */
    @RequestMapping("tag-delete")
    public String tagDelet(@RequestParam Integer id,RedirectAttributes attributes){
        if (tagService.deleteTag(id)!=0){
            attributes.addFlashAttribute("messges","删除成功！");
            return "redirect:/admin/tags";
        }else{
            attributes.addFlashAttribute("messges","对不起删除失败！");
            return "redirect:/admin/tags";
        }
    }
    /**
     * tag添加
     * @param name
     * @param attributes
     * @return
     */
    @PostMapping("/tagadd")
    public String tagadd(@RequestParam String name,RedirectAttributes attributes){
        if (tagService.addtag(name)!=1){
            attributes.addFlashAttribute("messges","对不起您添加的标签已经出现了！");
            return "redirect:/admin/tags-input";
        }else{
            attributes.addFlashAttribute("messges","成功添加标签"+name+"!");
            return "redirect:/admin/tags";
        }
    }
    /**
     * 获取登录的信息
     * @return
     */
    public User userSession(){
        return  (User)request.getSession().getAttribute("user");
    }
}
