package com.main.producer.config;

 import org.springframework.context.annotation.Configuration;
 import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
 import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

 @Configuration
 @EnableWebSecurity
 public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.inMemoryAuthentication()
                 .passwordEncoder(new BCryptPasswordEncoder())
                 .withUser("user")
                 .password("$2a$10$Gc71mPYMI31REpJxLRDHK.KXo/Hp9BaEgEUYWh90q6gDT67pveXTK")
                 .roles("USER")
                 .and()
                 .withUser("admin")
                 .password("$2a$10$Gc71mPYMI31REpJxLRDHK.KXo/Hp9BaEgEUYWh90q6gDT67pveXTK")
                 .roles("ADMIN")
         ;
     }

     @Override
     protected void configure(HttpSecurity http) throws Exception {
         http.csrf().disable()
                 .authorizeRequests().anyRequest().authenticated()
                 .and().httpBasic();
     }
 }