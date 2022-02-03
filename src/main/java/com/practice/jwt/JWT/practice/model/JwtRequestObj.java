package com.practice.jwt.JWT.practice.model;


public class JwtRequestObj {
    private String userName;
    private String password;

    public JwtRequestObj(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public JwtRequestObj() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
