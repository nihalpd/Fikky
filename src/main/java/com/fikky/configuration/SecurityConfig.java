package com.fikky.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan("com.fikky.service")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private AuthenticationProvider authenticationProvider;

  @Autowired
  @Qualifier("daoAuthenticationProvider")
  public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
    this.authenticationProvider = authenticationProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().ignoringAntMatchers("/console").disable()
          .authorizeRequests().antMatchers("/**/favicon.ico") .permitAll()
          .and().authorizeRequests().antMatchers("/webjars/**").permitAll()
          .and().authorizeRequests().antMatchers("/static/css").permitAll()
          .and().authorizeRequests().antMatchers("/js").permitAll()
          .and().formLogin().loginPage("/login").permitAll()
          .and().authorizeRequests().antMatchers("/story/**").authenticated()
          .and().authorizeRequests().antMatchers("/chapter/**").authenticated()
          .and().authorizeRequests().antMatchers("/user/**").authenticated()
          .and().exceptionHandling().accessDeniedPage("/access_denied");
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    return daoAuthenticationProvider;
  }

  @Autowired
  public void configureAuthManager(AuthenticationManagerBuilder authenticationManagerBuilder) {
    authenticationManagerBuilder.authenticationProvider(authenticationProvider);
  }

}
