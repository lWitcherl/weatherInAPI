package com.dut.education.entitys;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "t_user")
public class UserFromDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;

    @ElementCollection(fetch=FetchType.LAZY)
    private List<Integer> favoriteCity;

    @ManyToMany(cascade =  {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    private List<UserRole> roles;

    public UserFromDB() {
    }

    public UserFromDB(UserInfo userInfo) {
        this.username = userInfo.getUsername();
        this.password = userInfo.getPassword();
        this.email = userInfo.getEmail();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getFavoriteCity() {
        return favoriteCity;
    }

    public void setFavoriteCity(List<Integer> favoriteCity) {
        this.favoriteCity = favoriteCity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
