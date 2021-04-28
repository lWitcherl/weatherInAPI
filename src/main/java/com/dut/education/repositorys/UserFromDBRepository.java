package com.dut.education.repositorys;
import com.dut.education.entitys.UserFromDB;

public interface UserFromDBRepository{
    public UserFromDB findUserFromDBByUsername(String username);
    public UserFromDB findUserFromDBByEmail(String email);
    public void save(UserFromDB user);
    public void delete(UserFromDB user);
    public boolean existsById(int id);
}
