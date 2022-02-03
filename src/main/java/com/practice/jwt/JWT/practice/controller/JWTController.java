package com.practice.jwt.JWT.practice.controller;

import com.practice.jwt.JWT.practice.Util.JwtUtil;
import com.practice.jwt.JWT.practice.model.JwtRequestObj;
import com.practice.jwt.JWT.practice.model.JwtResponse;
import com.practice.jwt.JWT.practice.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JWTController {

//    @Autowired
//    private JwtResponse jwtResponse;
//    @Autowired
//    private JwtRequestObj jwtRequestObj;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/generateToken")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequestObj jwtRequestObj){

        //authenticate the user.
        UsernamePasswordAuthenticationToken credential = new UsernamePasswordAuthenticationToken(jwtRequestObj.getUserName(),jwtRequestObj.getPassword());
        authenticationManager.authenticate(credential);

        UserDetails user = customUserDetailsService.loadUserByUsername(jwtRequestObj.getUserName());
         String jwtToken = jwtUtil.generateToken(user);
        //JwtResponse jwtResponse = new JwtResponse();
        JwtResponse response = new JwtResponse(jwtToken);
        return new ResponseEntity<JwtResponse>(response, HttpStatus.OK);
    }

}
