package com.fikky.validators;


import com.fikky.commands.UserCreateForm;
import com.fikky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserCreateFormValidator implements Validator {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return UserCreateForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserCreateForm userCreateForm = (UserCreateForm) o;

        if (userService.findByUsername(userCreateForm.getUsername()) != null) {
            errors.rejectValue("username","UsernameExists.userCreateForm.username", "Username already exists.");
        }

        if (!userCreateForm.getPasswordText().equals(userCreateForm.getPasswordTextConf())) {
            errors.rejectValue("passwordText","PasswordsDontMatch.userCreateForm.passwordText","Passwords don't match.");
            errors.rejectValue("passwordTextConf","PasswordsDontMatch.userCreateForm.passwordTextConf","Passwords don't match.");
        }

    }

}
