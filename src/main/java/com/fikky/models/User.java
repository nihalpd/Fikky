package com.fikky.models;

import com.fikky.models.security.Role;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class User extends AbstractDomainClass {

  @Length(min = 2, max = 10)
  private String username;

  @Transient
  private String password;

  private String encryptedPassword;
  private Boolean enabled;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable
  private List<Role> roles;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEncryptedPassword() {
    return encryptedPassword;
  }

  public void setEncryptedPassword(String encryptedPassword) {
    this.encryptedPassword = encryptedPassword;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public void addRole(Role role) {
    if (!this.roles.contains(role)) {
      this.roles.add(role);
    }

    if(!role.getUsers().contains(this)) {
      role.getUsers().add(this);
    }
  }

  public void removeRole(Role role) {
    this.roles.remove(role);
    role.getUsers().remove(this);
  }
}
