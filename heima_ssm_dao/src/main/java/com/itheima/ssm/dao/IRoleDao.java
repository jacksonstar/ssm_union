package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in (select roleid from users_role where userid = #{userId})")
    @Results({
            @Result(id = true, column = "id" , property = "id"),
            @Result(column = "roleName" , property = "roleName"),
            @Result(column = "roleDesc" , property = "roleDesc"),
            @Result(column = "id" , property = "permissions" ,javaType = java.util.List.class,
                many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findPermissionsByRoleId")
            )
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    //查询所有
    @Select("select * from role")
    public List<Role> findAll();

    //添加角色
    @Insert("insert into role(ROLENAME,ROLEDESC) values(#{roleName},#{roleDesc})")
    void save(Role role)throws Exception;

    @Select("select * from role where id = #{id}")
    Role findById(String id);

    //查询该角色没有的授权
    @Select("select * from permission where id not in (select permissionid from role_permission where roleid = #{id})")
    List<Permission> findOtherPermissionByRoleId(@Param("id") String id);

    //权限添加进角色中
    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);

}
