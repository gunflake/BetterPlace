package com.dongisarang.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecurityCustomerService securityCustomerService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    //    log.info("security config....");

        //게스트 모든권한 해제, Home, Login, SignUp, Reservation
        http.authorizeRequests().antMatchers("/**").permitAll();
        //로그인한 Customer만 권한 해제
        http.authorizeRequests().antMatchers("/customer/**").hasRole("CUSTOMER");

        // Spring Boot 에서 제공하는 기본 로그인 페이지를 제외한
        // form 태그를 사용하는 로그인 페이지 지정
        http.formLogin().loginPage("/login");

        //세션 무효화, logoutUrl은 세션 무효화를 수행하는 페이지 주체
        //http.logout().logoutUrl("/customer/reservation").invalidateHttpSession(true).logoutSuccessUrl("/customer/reservation");
    }

    // 비밀번호 암호화, BCryptPasswordEncoder, SCryptPasswordEncoder 등  (PasswordEncoder => interface)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
    //    log.info("build Auth global......");
        auth.userDetailsService(securityCustomerService).passwordEncoder(passwordEncoder());
    }

}
