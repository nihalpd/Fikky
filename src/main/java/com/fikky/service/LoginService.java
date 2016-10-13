package com.fikky.service;

import org.eclipse.egit.github.core.client.GitHubClient;


public class LoginService {
    // TODO: THIS IS WRONG. NEED TO FIGURE OUT HOW TO WRITE THIS TO WORK WITH OAUTH2
    public static void login(String username, String password) {
        GitHubClient client = new GitHubClient();
        client.setCredentials(username, password);
    }

}
