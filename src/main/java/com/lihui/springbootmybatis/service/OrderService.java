package com.lihui.springbootmybatis.service;

import com.lihui.springbootmybatis.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author LiHui
 * @create 2019-06-18-15:29
 */
public interface OrderService {
    String save(Order order) throws Exception;

    //根据主键查找订单
    Order getOrder(String id);

    //根据交易订单号获取订单对象
    Order getOutTradeNo( String outTradeNo);

    //逻辑删除订单
    int delOrder(String id,String userid);

    //查询我的全部订单

    List<Order> findMyOrderList(String userid);

    //根据订单流水号更新
    int updateOrderByOutTradeNo(Order order);

}
