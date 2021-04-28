package com.dut.education.repositorys;

import com.dut.education.entitys.UserRole;
import java.util.List;


public interface UserRoleRepository  {
    public void save(UserRole userRole);
    public List<UserRole> findAll();
}
