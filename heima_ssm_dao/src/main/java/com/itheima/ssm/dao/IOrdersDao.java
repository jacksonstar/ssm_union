package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.domain.Traveller;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {
    @Select("select * from orders")
    @Results({ @Result(id = true , column = "id" , property = "id"),
            @Result( column = "orderNum" , property = "orderNum"),
            @Result(column = "orderTime" , property = "orderTime"),
            @Result(column = "orderTimeStr" , property = "orderTimeStr"),
            @Result(column = "orderStatus" , property = "orderStatus"),
            @Result(column = "peopleCount" , property = "peopleCount"),
            @Result(column = "payType" , property = "payType"),
            @Result(column = "payTypeStr" , property = "payTypeStr"),
            @Result(column = "orderDesc" , property = "orderDesc"),
           @Result(column = "productId" ,property = "product" ,javaType = Product.class ,
                one = @One(select = "com.itheima.ssm.dao.IProductDao.findById")
           )
    })
    public List<Orders> findAll();



    @Select("select * from orders where id = #{ordersId}")
    @Results({ @Result(id = true , column = "id" , property = "id"),
            @Result( column = "orderNum" , property = "orderNum"),
            @Result(column = "orderTime" , property = "orderTime"),
            @Result(column = "orderTimeStr" , property = "orderTimeStr"),
            @Result(column = "orderStatus" , property = "orderStatus"),
            @Result(column = "peopleCount" , property = "peopleCount"),
            @Result(column = "payType" , property = "payType"),
            @Result(column = "payTypeStr" , property = "payTypeStr"),
            @Result(column = "orderDesc" , property = "orderDesc"),
            @Result(column = "productId" ,property = "product" ,javaType = Product.class ,
                    one = @One(select = "com.itheima.ssm.dao.IProductDao.findById")
            ),
            @Result(column = "memberId" ,property = "member",javaType = Member.class,
                    one = @One(select = "com.itheima.ssm.dao.IMemberDao.findById")),
            @Result(column = "id" ,property = "travellers" ,javaType = List.class ,
                    many = @Many(select = "com.itheima.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(String ordersId);
}
