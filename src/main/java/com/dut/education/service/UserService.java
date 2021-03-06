package com.dut.education.service;

import com.dut.education.entitys.UserDetailsImp;
import com.dut.education.entitys.UserFromDB;
import com.dut.education.entitys.UserInfo;
import com.dut.education.entitys.UserRole;
import com.dut.education.repositorys.UserFromDBRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service("userService")
public class UserService implements UserDetailsService {

    private UserFromDBRepository userRepository;
    private List<UserRole> roleList;

    public UserService(UserFromDBRepository userRepository,RoleService roleService) {
        this.userRepository = userRepository;
        this.roleList = roleService.getAllRole();
    }


    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserFromDB user = userRepository.findUserFromDBByUsername(s);
        return new UserDetailsImp(user);
    }

    @Transactional
    public boolean findEmail(String email){
        return userRepository.findUserFromDBByEmail(email)!=null;
    }
    @Transactional
    public boolean findUsername(String name){
        return userRepository.findUserFromDBByUsername(name)!=null;
    }
    @Transactional
    public UserFromDB loadUserFromDB(String name){
        UserFromDB user = userRepository.findUserFromDBByUsername(name);
        user.getFavoriteCity().size();
        return user;
    }
    @Transactional
    public boolean saveUser(UserInfo userInfo){
        if (!findUsername(userInfo.getUsername())&&!findEmail(userInfo.getEmail())){
            try {
                UserFromDB user = new UserFromDB(userInfo);
                user.setRoles(Collections.singletonList(roleList.get(0)));
                userRepository.save(user);
            }catch (Exception e){
                return false;
            }
        }else return false;

        return true;
    }
    @Transactional
    public boolean updateCityList(String username,int id){
        UserFromDB user = loadUserFromDB(username);
        List<Integer> city = user.getFavoriteCity();
        //if (city.size()=3) return false;
        city.add(id);
        if (city.size()>3) user.getFavoriteCity().remove(0);
        userRepository.save(user);
        return true;
    }
    @Transactional
    public boolean deleteCity(String username,int id){
        UserFromDB user = loadUserFromDB(username);
        user.getFavoriteCity().remove((Integer) id);
        userRepository.save(user);
        return true;
    }
    @Transactional
    public boolean deleteUser(UserFromDB user){
        userRepository.delete(user);
        return userRepository.existsById(user.getId());
    }

}
