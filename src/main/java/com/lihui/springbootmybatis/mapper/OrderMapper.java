package com.lihui.springbootmybatis.mapper;

import com.lihui.springbootmybatis.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author LiHui
 * @create 2019-06-18-15:01
 */
public interface OrderMapper {
    @Insert("INSERT INTO vx_order(id,outTradeNo,state,createTime,notifyTime,totalFee,nickname,videoId,videoTitle,userId,ip,del)"
            + "VALUES(#{id},#{outTradeNo},#{state},#{createTime},#{notifyTime},#{totalFee},#{nickName},#{videoId},#{videoTitle},#{userId},#{ip},#{del})")
    void save(Order order);

    //根据主键查找订单
    @Select("select * from vx_order where id=#{id} and del=0 ")
    Order getOrder(@Param("id")String id);

    //根据交易订单号获取订单对象
    @Select("select * from vx_order where outTradeNo=#{outTradeNo} and del=0")
    Order getOutTradeNo(@Param("outTradeNo") String outTradeNo);

    //逻辑删除订单
    @Update("update vx_order set del=1 where id=#{id} and userid=#{userid}")
    int delOrder(@Param("id")String id,@Param("userid")String userid);

    //查询我的全部订单

    @Select("select * from vx_order where userid=#{userid}")
    List<Order> findMyOrderList(String userid);

    //根据订单流水号更新
    @Update("UPDATE vx_order SET state=1,notifyTime=NOW() " +
            "WHERE outTradeNo=#{outTradeNo} AND state=0 AND del=0")
    int updateOrderByOutTradeNo(Order order);


}

