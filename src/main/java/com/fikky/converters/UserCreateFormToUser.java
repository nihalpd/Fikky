package com.fikky.converters;


import com.fikky.commands.UserCreateForm;
import com.fikky.models.User;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
@Component
public class UserCreateFormToUser implements Converter<UserCreateForm,User> {
    @Override
    public User convert(UserCreateForm userCreateForm) {
        User user = new User();
        user.setId(userCreateForm.getId());
        user.setVersion(userCreateForm.getVersion());
        user.setUsername(userCreateForm.getUsername());
        user.setPassword(userCreateForm.getPasswordText());
        user.setEnabled(true);
        return user;
    }
}
