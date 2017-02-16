package com.fikky.controllers;

import com.fikky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/admin/user/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.listAll());
        return "admin/user/list";
    }

    @RequestMapping("/admin/user/show/{id}")
    public String showUser(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "admin/user/show";
    }

}
