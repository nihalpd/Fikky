package com.fikky.service;

import org.eclipse.egit.github.core.client.GitHubClient;

public interface LoginService {

    GitHubClient login();
    void logout();



}
