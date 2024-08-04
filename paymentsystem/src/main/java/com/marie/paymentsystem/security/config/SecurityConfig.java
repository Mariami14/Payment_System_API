package com.marie.paymentsystem.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean //registering new bean allows us to configure security chain- and define security rules
    // because by default localhost redirects us to login page and the fields are protected
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/").permitAll() //accessible to any user
                        .antMatchers("/contact").permitAll() //will be public
                        .antMatchers("/store/**").permitAll() //TODO სახელები შესაცვლელია
                        .antMatchers("/register").permitAll()
                        .antMatchers("/login").permitAll()
                        .antMatchers("/logout").permitAll()
                        .antMatchers("/admin/**").hasRole("ADMIN") // admin-only access
                        .anyRequest().authenticated() //any other url requires user authentication
                )
                .formLogin(form -> form //define login and logout functionalities
                        .defaultSuccessUrl("/",true)) // after successful user authentication we
                //will redirect the userr to the /url
                .logout(config -> config.logoutSuccessUrl("/")) //after successful logout user will be redirected to / - this url
                .build();

    }
    @Bean //here we register new bean for password encoder to check if user's password is correct
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

}
