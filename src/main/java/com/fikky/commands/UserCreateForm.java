package com.fikky.commands;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserCreateForm {

    private Integer id;
    private Integer version;

    @NotEmpty
    @Length(min = 2, max = 10)
    private String username;

    @Length(min = 8, max = 32)
    private String passwordText;

    @Length(min = 8, max = 32)
    private String passwordTextConf;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordText() {
        return passwordText;
    }

    public void setPasswordText(String passwordText) {
        this.passwordText = passwordText;
    }

    public String getPasswordTextConf() {
        return passwordTextConf;
    }

    public void setPasswordTextConf(String passwordTextConf) {
        this.passwordTextConf = passwordTextConf;
    }
}
