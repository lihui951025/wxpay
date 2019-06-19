package com.lihui.springbootmybatis.service.impl;

import com.lihui.springbootmybatis.config.WeChatConfig;
import com.lihui.springbootmybatis.entity.Commodity;
import com.lihui.springbootmybatis.entity.Order;
import com.lihui.springbootmybatis.entity.User;
import com.lihui.springbootmybatis.mapper.CommodityMapper;
import com.lihui.springbootmybatis.mapper.OrderMapper;
import com.lihui.springbootmybatis.mapper.UserMapper;
import com.lihui.springbootmybatis.service.OrderService;
import com.lihui.springbootmybatis.utils.HttpUtils;
import com.lihui.springbootmybatis.utils.WXPayReport;
import com.lihui.springbootmybatis.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lihui.springbootmybatis.utils.WXPayConstants.SignType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

import static com.lihui.springbootmybatis.utils.WXPayConstants.MD5;

/**
 * @author LiHui
 * @create 2019-06-18-15:31
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    WeChatConfig weChatConfig;


    @Autowired
    CommodityMapper commodityMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String save(Order order) throws Exception {
        //查找商品信息
        Commodity commodity = commodityMapper.getCommodity(order.getVideoId());

        //查找用户信息
        User user = userMapper.getUser(order.getUserId());

        ///生成订单号
        order.setId(WXPayUtil.generateUUID());
        order.setTotalFee(commodity.getPrice());
        order.setVideoTitle(commodity.getTitle());
        order.setCreateTime(new Date());
        order.setVideoId(commodity.getId());
        order.setState(0);
        order.setUserId(user.getId());
        order.setNickName(user.getName());
        order.setDel(0);
        order.setIp(order.getIp());
        order.setOutTradeNo(WXPayUtil.generateUUID());
        orderMapper.save(order);

        //获取codeUrl
        String codeUrl = unifiedOrder(order);

        return codeUrl;
    }
        //统一下单
    private String unifiedOrder(Order order) throws Exception {
        SortedMap<String,String> params=new TreeMap<>();
        //生成签名
        params.put("appid",weChatConfig.getAppId());
        params.put("mch_id",weChatConfig.getMchID());
        params.put("nonce_str",WXPayUtil.generateUUID());
        params.put("body",order.getVideoTitle());
        params.put("out_trade_no",order.getOutTradeNo());
        params.put("total_fee",order.getTotalFee().toString());
        params.put("spbill_create_ip",order.getIp());
        params.put("notify_url",weChatConfig.getPayCallbackUrl());
        params.put("trade_type","NATIVE");

        //sign签名
        String sign = WXPayUtil.generateSignature(params, weChatConfig.getKey());
        params.put("sign",sign);
        String xml = WXPayUtil.mapToXml(params);
        System.out.println(xml);

        //统一下单
       // String orderStr = WXPayReport.httpRequest(xml, 6000, 8000);
        String orderStr = HttpUtils.doPost(WeChatConfig.UNIFIED_ORDER_URL,xml, 4000);
        if(null == orderStr){
            return null;
        }
        Map<String, String> unifiedOrderMap = WXPayUtil.xmlToMap(orderStr);
        if (unifiedOrderMap != null){
            return unifiedOrderMap.get("code_url");
        }
        return null;

    }

    @Override
    public Order getOrder(String id) {
        return orderMapper.getOrder(id);
    }

    @Override
    public Order getOutTradeNo(String outTradeNo) {
        return orderMapper.getOutTradeNo(outTradeNo);
    }

    @Override
    public int delOrder(String id, String userid) {
        return orderMapper.delOrder(id,userid);
    }

    @Override
    public List<Order> findMyOrderList(String userid) {
        return orderMapper.findMyOrderList(userid);
    }

    @Override
    public int updateOrderByOutTradeNo(Order order) {
        return orderMapper.updateOrderByOutTradeNo(order);
    }
}
