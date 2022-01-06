//package com.eindproject.eindproject.security.v1.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//@Configuration
//@EnableWebSecurity
//public class ApiSecurity extends WebSecurityConfiguration{
//
//    @Bean
//    public BCryptPasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests().anyRequest().fullyAuthenticated().and()
//                .httpBasic();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr)
//        throws Exception {
//        authenticationMgr.inMemoryAuthentication().withUser("username")
//                .password("$6y$13$9OzrtK7X4mad/te8m7uhysjfaihjsdfZdJ/fDZFaOJjrfFbYzYtzky").roles("ADMIN");
//    }
//}
