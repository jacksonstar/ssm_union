package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface IUserDao {

    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true , column = "id" ,property = "id"),
            @Result(column = "username" ,property = "username"),
            @Result(column = "email" ,property = "email"),
            @Result(column = "password" ,property = "password"),
            @Result(column = "phoneNum" ,property = "phoneNum"),
            @Result(column = "status" ,property = "status"),
            @Result(column = "id" ,property = "roles" ,javaType = List.class ,
                    many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername (String username);


    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);



    @Select("select * from users where id = #{userId}")
    @Results({
            @Result(id = true , column = "id" ,property = "id"),
            @Result(column = "username" ,property = "username"),
            @Result(column = "email" ,property = "email"),
            @Result(column = "password" ,property = "password"),
            @Result(column = "phoneNum" ,property = "phoneNum"),
            @Result(column = "status" ,property = "status"),
            @Result(column = "id" ,property = "roles" ,javaType = List.class ,
                    many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByUserId"))

    })
    UserInfo findById(String userId);

    @Select("select * from role where id not in (select roleId from users_role where userid = #{id})")
    List<Role> findOtherRole(String id);

    //用户 和 角色添加
    @Insert("insert into users_role values(#{userId} , #{roleId})")
    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId);
}
