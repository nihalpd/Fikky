package com.fikky.service.repo;

import com.fikky.models.User;
import com.fikky.repositories.UserRepository;
import com.fikky.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("repo")
public class UserServiceRepoImpl implements UserService {

  private UserRepository userRepository;

  @Autowired
  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
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
    return userRepository.save(domainObject);
  }

  @Override
  public void delete(Integer id) {
    userRepository.delete(id);
  }

  @Override
  public User findByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }
}
