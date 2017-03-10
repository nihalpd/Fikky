package com.fikky.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private AuthenticationProvider authenticationProvider;

  @Autowired
  public SecurityConfig(@Qualifier("daoAuthenticationProvider") AuthenticationProvider authenticationProvider) {
    this.authenticationProvider = authenticationProvider;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/**/favicon.ico") .permitAll()
        .and().authorizeRequests().antMatchers("/webjars/**").permitAll()
        .and().authorizeRequests().antMatchers("/static/**").permitAll()
        .and().authorizeRequests().antMatchers("/register").permitAll()
        .and().authorizeRequests().antMatchers("/js").permitAll()
        .and().formLogin().loginPage("/").loginProcessingUrl("/login")
        .successForwardUrl("/story/list").permitAll()
        .and().logout().logoutSuccessUrl("/")
        .and().authorizeRequests().antMatchers("/story/**").authenticated()
        .and().authorizeRequests().antMatchers("/chapter/**").authenticated()
        .and().authorizeRequests().antMatchers("/user/**").authenticated()
        .and().authorizeRequests().antMatchers("/nav.html").authenticated()
        .and().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
        .and().exceptionHandling().accessDeniedPage("/access_denied");
  }

  @Autowired
  public void configureAuthManager(AuthenticationManagerBuilder authenticationManagerBuilder) {
    authenticationManagerBuilder.authenticationProvider(authenticationProvider);
  }

}
