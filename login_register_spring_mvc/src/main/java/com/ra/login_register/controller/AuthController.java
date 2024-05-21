package com.ra.login_register.controller;

import com.ra.login_register.dao.IUser;
import com.ra.login_register.dto.request.FormRegister;
import com.ra.login_register.dto.request.FormLogin;
import com.ra.login_register.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    @Autowired
    private IUser iUser;
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("formLogin", new FormLogin());
        return "login";
    }
    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("formLogin") Users formLogin, HttpSession session) {
        if (iUser.login(formLogin)) {
            Users users = iUser.findUserbyUsername(formLogin.getUsername());
            session.setAttribute("user", users);
            session.setMaxInactiveInterval(600);
            return "home";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("formRegister", new FormRegister());
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("formRegister") Users formRegister,Model model) {
        if (iUser.save(formRegister)){
            return "redirect:/";
        } else {
            return "redirect:/register";
        }

    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
