package com.practice.jwt.JWT.practice.config;

import com.practice.jwt.JWT.practice.model.JwtResponse;
import com.practice.jwt.JWT.practice.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class JWTConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomUserDetailsService customUserDetailsService;


    // This "configure()" overridden class decides how the authentication will be managed.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }
    //This "configure()" overridden class decides which end points are permitted and which are not based on authentication.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/api/generateToken").permitAll() //only allow this URL without authentication.
                .anyRequest().authenticated() //For any other requests, authentication should be performed.
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Every request should be independent and server does not manage sessions.

    }

    //We need a password encoder. For now, I am using a simple password encoder, but this should not be in applied in industry level.
    @Bean
    public PasswordEncoder PasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


    //If we use AuthenticationManager and Autowired it in any of the class in any package, we need to define a bean of it in the config file.
    //For now AuthenticationManager has been autowired in JWTController.

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
