package com.fikky.service;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
* IN ORDER TO USE THIS:
* Please make sure you have a "github.properties" file
* in the com.fikky.service package with username and
* password fields.
* */

@Service
public class DumbLogin implements LoginService {

    private GitHubClient client;

    @Override
    public GitHubClient login() {
        Properties properties = loadCredentials();
        if (client == null) {
            client = new GitHubClient();
            client.setCredentials(properties.getProperty("username"), properties.getProperty("password"));
        }
        return client;
    }

    private Properties loadCredentials() {

        Properties prop = new Properties();
        InputStream input = null;
        String fileName = "/com/fikky/service/github.properties";
        try {
            input = getClass().getResourceAsStream(fileName);
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

    @Override
    public void logout() {
        // TODO
    }

}
