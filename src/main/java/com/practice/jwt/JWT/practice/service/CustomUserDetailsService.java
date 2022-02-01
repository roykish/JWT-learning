package com.practice.jwt.JWT.practice.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    //This method actually does the validation, whether the user exists or not.
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if(userName.equals("admin")){ //Here I can call DB with the help of repository and validate instead of hardcoding here.
            return new User("admin", "admin", new ArrayList<>());
        }
        else{
            throw new UsernameNotFoundException("user does not exist");
        }
    }
}
