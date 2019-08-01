package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    //通过userid,查询该user的其他角色
   List<Role> findOtherRole(String id) throws Exception;


    //根据用户id查询用户所有信息，包括角色和授权
    public UserInfo findById(String userId) throws Exception;

    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo);

    void addRoleToUser(String userId, String[] roleIds);
}
