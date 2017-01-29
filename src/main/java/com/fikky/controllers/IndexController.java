package com.fikky.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String getIndex() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
