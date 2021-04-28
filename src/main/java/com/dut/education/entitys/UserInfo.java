package com.dut.education.entitys;

import java.util.List;

public class UserInfo {

    private String username;
    private String email;
    private List<Integer> favoriteCity;
    private String password=null;

    public UserInfo(UserFromDB user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.favoriteCity = user.getFavoriteCity();
    }

    public UserInfo() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getFavoriteCity() {
        return favoriteCity;
    }

    public void setFavoriteCity(List<Integer> favoriteCity) {
        this.favoriteCity = favoriteCity;
    }
}
