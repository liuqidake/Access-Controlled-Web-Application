package com.qiliu.springcloud.simplesocialmediaapplicationserver.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/hello","/registration")
           .permitAll().and()
           .formLogin().loginPage("/login")
           .permitAll().and()
           .logout().permitAll();
        
        httpSecurity.csrf().disable();
     }

}
