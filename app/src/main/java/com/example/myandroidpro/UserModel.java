package com.example.myandroidpro;

public class UserModel {
    private String user_name;
    private String email;
    private String password;

    public UserModel(String user_name, String user_email, String password) {
        this.user_name = user_name;
        this.email = user_email;
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return email;
    }

    public void setUser_email(String user_email) {
        this.email = user_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
