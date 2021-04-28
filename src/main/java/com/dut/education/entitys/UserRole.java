package com.dut.education.entitys;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
@Entity
public class UserRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private List<UserFromDB> userList;

    @Override
    public String getAuthority() {
        return getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserFromDB> getUserList() {
        return userList;
    }

    public void setUserList(List<UserFromDB> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
