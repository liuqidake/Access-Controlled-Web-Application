package com.qiliu.springcloud.simplesocialmediaapplicationserver.Configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   
	@Autowired
	private BCryptPasswordEncoder bCrytpasswordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	
	private final String USER_QUERY = "select username, password, passwordConfirm, enabled from user r where r.username = ?";
	private final String ROLE_QUERY	= "select * from user u inner join user_role ur on (u.id = ur.user_id) inner join role r on (ur.role_id = r.role_id";
	
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
           .authorizeRequests()
           .antMatchers("/hello").permitAll()
           .antMatchers("/login").permitAll()
           .antMatchers("/registration").permitAll()
           .antMatchers("/home/**").hasAuthority("USER").anyRequest().authenticated().and()
           .formLogin().loginPage("/login").failureUrl("/login?error=true")
           .defaultSuccessUrl("/home")
           .usernameParameter("username")
           .passwordParameter("password").and()
                  .logout()
                  .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                  .logoutSuccessUrl("/");  
        httpSecurity.csrf().disable();
     }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    	auth.jdbcAuthentication().usersByUsernameQuery(USER_QUERY)
    	    .authoritiesByUsernameQuery(ROLE_QUERY).dataSource(dataSource)
    	    .passwordEncoder(bCrytpasswordEncoder);
    }
    
    
      

}
