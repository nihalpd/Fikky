package com.fikky.controllers;

import com.fikky.commands.UserCreateForm;
import com.fikky.models.User;
import com.fikky.service.UserService;
import com.fikky.validators.UserCreateFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class UserController {

    private UserService userService;
    private Validator userCreateFormValidator;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserFormValidator(UserCreateFormValidator userCreateFormValidator) {
        this.userCreateFormValidator = userCreateFormValidator;
    }

    @RequestMapping("/")
    public String register(Model model) {
        UserCreateForm userCreateForm = new UserCreateForm();
        model.addAttribute("userCreateForm", userCreateForm);
        return "user/register/userform";
    }

    @RequestMapping(value = "/do", method = RequestMethod.POST)
    public String saveOrUpdate(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        userCreateFormValidator.validate(userCreateForm, bindingResult);
        if (bindingResult.hasErrors()) return "user/register/userform";
        User savedUser = userService.saveOrUpdateUserForm(userCreateForm);
        return "user/register/success";
    }
}
