package com.fikky.configuration;

import com.fikky.service.LoginService;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = {"org.eclipse.egit.github.core"})
public class FikkyConfiguration {


    @Bean("GHClient")
    @Scope("prototype")
    @Autowired
    public GitHubClient GitHubclient(LoginService loginService) {
        return loginService.login();
    }

}
