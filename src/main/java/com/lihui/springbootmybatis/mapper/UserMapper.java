package com.lihui.springbootmybatis.mapper;

import com.lihui.springbootmybatis.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LiHui
 * @create 2019-06-18-13:45
 */
public interface UserMapper {
    @Insert("INSERT INTO vx_user( id,NAME,phone,sex,city,createTime)" +
            "VALUES(#{id},#{name},#{phone},#{sex},#{city},#{createTime})")
    void save(User user);

    @Select("select * from vx_user where id = #{id}")
    User getUser(@Param("id") String id);

    @Select("select * from vx_user")
    List<User> findAllUser();
}
