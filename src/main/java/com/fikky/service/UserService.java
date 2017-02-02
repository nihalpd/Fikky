package com.fikky.service;

import com.fikky.commands.UserCreateForm;
import com.fikky.models.User;

public interface UserService extends CRUDService<User> {
  User findByUsername(String username);
  User saveOrUpdateUserForm(UserCreateForm userCreateForm);
}
