package com.eindproject.eindproject.security.v1.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    //creating users
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("USER","ADMIN")
                ;
    }

    //securing endpoints
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/placeholderAdminLink").hasRole("ADMIN")
                .antMatchers("/placeholderUserLink").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/getMethodForAdminPlaceholder").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/getMethodForUsersPlaceholder").hasRole("USER")

                .and()
//                .anyRequest().permitAll()
//                .and()
                .cors()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ;
    }


}
