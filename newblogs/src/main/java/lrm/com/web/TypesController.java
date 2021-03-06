package lrm.com.web;

import com.github.pagehelper.PageInfo;
import lrm.com.entity.Type;
import lrm.com.entity.User;
import lrm.com.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
public class TypesController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    ITypeService typeService;

    /**
     * type列表
     * @param modelMap
     * @param page
     * @param size
     * @return
     */
    @GetMapping("types")
    public String typesPage(ModelMap modelMap,@RequestParam(name="page",required = true,defaultValue = "1") Integer page,
                            @RequestParam(name = "size",required = true,defaultValue = "6") Integer size ){
        PageInfo pageInfo;
        List<Integer> pages = new ArrayList<Integer>();
        pageInfo = new PageInfo(typeService.listTypes(page,size));
        modelMap.addAttribute("types",pageInfo);
        modelMap.addAttribute("user",userSession());
        return "admin/types";
    }

    /**
     * type修改方法
     * @param modelMap
     * @param attributes
     * @return
     */
    @PostMapping("typeupdate")
    public String typeupdate(ModelMap modelMap, @RequestParam String name,@RequestParam Integer id,RedirectAttributes attributes){
        modelMap.addAttribute("user",userSession());
        Type type = new Type();
        type.setId(id);
        type.setName(name);
        if (typeService.updateType(type)!=1){
            attributes.addFlashAttribute("messges","修改失败！请检查类型是否重名");
            attributes.addFlashAttribute("id",id);
            return "redirect:/admin/type-update";
        }else{
            return "redirect:/admin/types";
        }
    }

    /**
     * 通过id获取单个type对象跳转修改页
     * @param modelMap
     * @param id
     * @return
     */
    @RequestMapping("type-update")
    public String typeOne(ModelMap modelMap,@ModelAttribute("messges") String messges,@ModelAttribute("id")Integer id){
        modelMap.addAttribute("user",userSession());
        modelMap.addAttribute("messges",messges);
        modelMap.addAttribute("type",typeService.Onetype(id));
        return "admin/type-update";
    }
    /**
     * type添加跳转
     * @param modelMap
     * @param messges
     * @return
     */
    @GetMapping("types-input")
    public String typesInputPage(ModelMap modelMap,@ModelAttribute("messges") String messges){
        modelMap.addAttribute("user",userSession());
        modelMap.addAttribute("messges",messges);
        return "admin/types-input";
    }

    /**
     * 删除指定的类型
     * @param id
     * @return
     */
    @RequestMapping("type-delete")
    public String typeDelet(@RequestParam Integer id,RedirectAttributes attributes){
        if (typeService.deleteType(id)!=0){
            attributes.addFlashAttribute("messges","删除成功！");
            return "redirect:/admin/types";
        }else{
            attributes.addFlashAttribute("messges","对不起删除失败！");
            return "redirect:/admin/types";
        }
    }
    /**
     * type添加
     * @param name
     * @param attributes
     * @return
     */
    @PostMapping("/typeadd")
    public String typesadd(@RequestParam String name,RedirectAttributes attributes){
        if (typeService.addtype(name)!=1){
            attributes.addFlashAttribute("messges","对不起您添加的标签已经出现了！");
            return "redirect:/admin/types-input";
        }else{
            attributes.addFlashAttribute("messges","成功添加标签"+name+"!");
            return "redirect:/admin/types";
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
