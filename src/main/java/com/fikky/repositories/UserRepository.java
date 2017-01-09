package com.fikky.repositories;


import com.fikky.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
  User findByUsername(String username);
}
