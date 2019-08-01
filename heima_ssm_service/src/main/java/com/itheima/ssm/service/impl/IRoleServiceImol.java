package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IRoleServiceImol implements IRoleService {

    @Autowired
    private IRoleDao iRoleDao;

    @Override
    public List<Role> findAll() {
        return  iRoleDao.findAll();
    }

    //添加角色
    @Override
    public void save(Role role) throws Exception {
        iRoleDao.save(role);
    }

    @Override
    public Role findById(String id) {
      return   iRoleDao.findById(id);
    }

    @Override
    public List<Permission> findOtherPermissionByRoleId(String id) throws Exception {

        return  iRoleDao.findOtherPermissionByRoleId(id);
    }

    //权限添加进角色里
    @Override
    public void addPermissionToRole(String roleId, String[] ids) {

        for (String permissionId : ids) {
            iRoleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
