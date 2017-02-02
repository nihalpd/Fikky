package com.fikky.service.repo;

import com.fikky.commands.UserCreateForm;
import com.fikky.converters.UserCreateFormToUser;
import com.fikky.models.User;
import com.fikky.repositories.UserRepository;
import com.fikky.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Profile("repo")
public class UserServiceRepoImpl implements UserService {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private UserCreateFormToUser userCreateFormToUser;

  @Autowired
  private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Autowired
  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Autowired
  public void setUserCreateFormToUser(UserCreateFormToUser userCreateFormToUser) {
    this.userCreateFormToUser = userCreateFormToUser;
  }

  @Override
  public List<?> listAll() {
    List<User> users = new ArrayList<>();
    userRepository.findAll().forEach(users::add);
    return users;
  }

  @Override
  public User getById(Integer id) {
    return userRepository.findOne(id);
  }

  @Override
  public User saveOrUpdate(User domainObject) {
    if (domainObject.getPassword()!=null) {
      domainObject.setEncryptedPassword(passwordEncoder.encode(domainObject.getPassword()));
    }
    return userRepository.save(domainObject);
  }

  @Override
  public void delete(Integer id) {
    userRepository.delete(id);
  }

  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public User saveOrUpdateUserForm(UserCreateForm userCreateForm) {
    return saveOrUpdate(userCreateFormToUser.convert(userCreateForm));
  }
}
