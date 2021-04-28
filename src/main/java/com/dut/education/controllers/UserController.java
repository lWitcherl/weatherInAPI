package com.dut.education.controllers;

import com.dut.education.entitys.UserFromDB;
import com.dut.education.entitys.UserInfo;
import com.dut.education.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/check")
    public boolean checkUserDataForRegistration(HttpServletRequest request){
        boolean result = false;
        if(request.getParameter("username")!=null){
            result = userService.findUsername(request.getParameter("username"));
        }else if(request.getParameter("email")!=null){
            result = userService.findEmail(request.getParameter("email"));
        }
        return result;
    }

    @PostMapping("/info")
    public UserInfo getUserInfo(HttpServletRequest request){
        UserFromDB userFromDB = (UserFromDB) userService.loadUserByUsername(request.getParameter("username"));
        return new UserInfo(userFromDB);
    }
    @PostMapping("/auth")
    public UserDetails getAuthData(HttpServletRequest request){
        return userService.loadUserByUsername(request.getParameter("username"));
    }
    @PostMapping("/save")
    public boolean saveUser(@RequestBody UserInfo userInfo){
        return userService.saveUser(userInfo);
    }
}
