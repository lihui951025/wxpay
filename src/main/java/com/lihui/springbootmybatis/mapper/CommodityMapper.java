package com.lihui.springbootmybatis.mapper;

import com.lihui.springbootmybatis.entity.Commodity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LiHui
 * @create 2019-06-18-14:34
 */
public interface CommodityMapper {
    @Insert("INSERT INTO vx_commodity(id,title,summary,price,createTime,online,POINT)"
        + "VALUES(#{id},#{title},#{summary},#{price},#{createTime},#{online},#{point})")
    void save(Commodity commodity);

    @Select("select * from vx_commodity where id=#{id}")
    Commodity getCommodity(@Param("id") String id);

    @Select("select * from vx_commodity")
    List<Commodity> getAllCommodity();
}
