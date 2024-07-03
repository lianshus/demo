package com.student.system.controller;

import com.student.common.config.BootConfig;
import com.student.common.controller.BaseController;
import com.student.common.domain.Tree;
import com.student.common.utils.MD5Utils;
import com.student.common.utils.R;
import com.student.common.utils.ShiroUtils;
import com.student.system.domain.MenuDO;
import com.student.system.domain.UserDO;
import com.student.system.service.MenuService;
import com.student.system.service.UserService;
import com.student.system.service.WebChainCodeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private WebChainCodeService chaincodeService;
    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;
    @Autowired
    BootConfig bootConfig;

    @GetMapping({"/", ""})
    String welcome(Model model) {

        return "redirect:/index";
    }

    @GetMapping({"/index"})
    String index(Model model) {
        List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("name", getUser().getName());
        model.addAttribute("picUrl", "/img/photo_s.jpg");
        model.addAttribute("username", getUser().getUsername());
        return "index_v1";
    }

    @GetMapping("/login")
    String login(Model model) {
        return "login";
    }
    @GetMapping("/registers")
    String registers(Model model) {
        return "registers";
    }
    @PostMapping("/checkExit")
    @ResponseBody
    boolean exit(@RequestParam Map<String, Object> params) {
        // 存在，不通过，false
        return !userService.exit(params);
    }
    @PostMapping("/registersave")
    @ResponseBody
    R registersave(UserDO user) {
        user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
        user.setStatus(0);
        if (userService.save(user) > 0) {
            return R.ok();
        }
        return R.error();
    }
    @PostMapping("/login")
    @ResponseBody
    R ajaxLogin(String username, String password,String verify,HttpServletRequest request) {
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            try{
                Thread t = new Thread(new Runnable(){
                    public void run(){
                        chaincodeService.queryChainCode("key",0);
                        chaincodeService.invokeChaincode("key");
                    }});
                t.start();
            }catch(Exception e){
                e.printStackTrace();
            }
            return R.ok();
        } catch (AuthenticationException e) {
            return R.error("用户或密码错误");
        }
    }

    @GetMapping("/logout")
    String logout() {
        ShiroUtils.logout();
        return "redirect:/login";
    }

    @GetMapping("/main")
    String main() {
        return "main";
    }
}