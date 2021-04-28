package com.dut.education.service;

import com.dut.education.entitys.UserRole;
import com.dut.education.repositorys.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleService {
    private UserRoleRepository userRoleRepository;


    public RoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Transactional
    public List<UserRole> getAllRole(){
        return userRoleRepository.findAll();
    }

}
