//package com.estsoft.pilot.global.config.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@RequiredArgsConstructor
//@EnableWebSecurity
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .formLogin()
////                .loginPage("/login")
////                .loginProcessingUrl("/login")
////                .defaultSuccessUrl("/")
////                .usernameParameter("email")
////                .passwordParameter("password")
////                .failureUrl("/login?isError=true")
////                .and()
////                .logout()
////                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////                .logoutSuccessUrl("/");
//
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/**").permitAll()
//                // .antMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated();
//
//    }
//}
//
