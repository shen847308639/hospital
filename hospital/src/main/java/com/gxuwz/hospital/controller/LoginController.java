package com.gxuwz.hospital.controller;

import com.gxuwz.hospital.entity.User;
import com.gxuwz.hospital.repository.PatientRepository;
import com.gxuwz.hospital.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

/*               用户登录*/
    @GetMapping("/login")
    public String loginPage() {
        return "/pages/userPages/login";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
            return "redirect:/index";
        } catch (AuthenticationException e) {
            return "redirect:/login";
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setRoleId(2);
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "/pages/userPages/Index";
    }

    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }

/*               管理员登录*/
    @GetMapping("/toAdminLogin")
    public String toAdminLogin(){
        SecurityUtils.getSubject().logout();
        return "/pages/adminPages/adminLogin";
    }

    @PostMapping("/adminLogin")
    public String doLogin(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return "/pages/adminPages/adminHome";
        } catch (AuthenticationException e) {
            return "redirect:/toAdminLogin";
        }
    }

}
