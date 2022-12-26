package com.springlearning.springlearning.SecurityConfiguration;//package com.springlearning.springlearning.SecurityConfiguration;
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
////@Configuration
////public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
////    @Override
////    protected void configure(HttpSecurity httpSecurity) throws Exception {
////        httpSecurity
////                .authorizeRequests().antMatchers("/console/**").permitAll().and()
////                .authorizeRequests().antMatchers("/api/**")
////                .hasRole("ADMIN")
////                .anyRequest()
////                .authenticated()
////                .and()
////                .httpBasic();
////        httpSecurity.csrf().disable();
////        httpSecurity.headers().frameOptions().disable();
////    }
////
////}
//

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //we can use this methode to  allow role based access,without WebSecurityConfigurerAdapter
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/console/**")
//                .permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/students/**")
//                .hasRole("ADMIN")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//        return http.build();
//    }


    @Autowired
    CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests().antMatchers("/students/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

