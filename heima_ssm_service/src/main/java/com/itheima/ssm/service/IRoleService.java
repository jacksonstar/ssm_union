package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> findAll();

    void save(Role role) throws Exception;

    Role findById(String id);

    List<Permission> findOtherPermissionByRoleId(String id) throws Exception;

    void addPermissionToRole(String roleId, String[] ids);

}
