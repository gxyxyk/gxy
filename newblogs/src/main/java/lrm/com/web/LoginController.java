package lrm.com.web;

import lrm.com.entity.User;
import lrm.com.service.IUserService;
import lrm.com.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class LoginController {
    @Autowired
    private IUserService userService;
    /**
     * 登录跳转功能
     * @return
     */
    @GetMapping("")
    public String loginPage(ModelMap modelMap,@ModelAttribute("messges") String messges,String title){
        modelMap.addAttribute("messges",messges);
        return "/admin/login";
    }
    @GetMapping("login")
    public String login(ModelMap modelMap,@ModelAttribute("messges") String messges,String title){
        modelMap.addAttribute("messges",messges);
        return "/admin/login";
    }
    /**
     * 登录功能
     * @param username
     * @param password
     * @param session
     * @param attributes
     * @param modelMap
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes,ModelMap modelMap){
        User user = userService.loginUser(username, MD5Util.code(password));
        if(user!=null){
            user.setPassword(null);
            modelMap.addAttribute("user",user);
            session.setAttribute("user",user);
            return "redirect:/admin/blogs-index";
        }else{
            attributes.addFlashAttribute("messges","对不起您的账号或者密码错误");
            return "redirect:/admin/";
        }
    }
    /**
     * 注销功能
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin/";
    }
}
