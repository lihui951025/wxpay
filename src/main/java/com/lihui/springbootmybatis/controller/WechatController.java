package com.lihui.springbootmybatis.controller;

import com.lihui.springbootmybatis.config.WeChatConfig;
import com.lihui.springbootmybatis.entity.Order;
import com.lihui.springbootmybatis.service.OrderService;
import com.lihui.springbootmybatis.utils.WXPayConstants;
import com.lihui.springbootmybatis.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

/**
 * @author LiHui
 * @create 2019-06-18-17:22
 */
@RestController
@RequestMapping("/wechat")
//微信支付回调
public class WechatController {
    @Autowired
    WeChatConfig weChatConfig;
    @Autowired
    OrderService orderService;

@RequestMapping("/order/callback")
public void orderCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ServletInputStream inputStream = request.getInputStream();
    BufferedReader in = new BufferedReader(
            new InputStreamReader(inputStream,"UTF-8")
    );
    StringBuffer sb = new StringBuffer();
    String line;
    while ((line=in.readLine())!=null){
        sb.append(line);
    }
    in.close();
    inputStream.close();
    Map<String, String> callBackMap = WXPayUtil.xmlToMap(sb.toString());
    System.out.println(callBackMap);
    //排序
    SortedMap<String, String> sortedMap = WXPayUtil.getSortedMap(callBackMap);
    //判断签名是否正确

    if(WXPayUtil.isSignatureValid(sortedMap, weChatConfig.getKey())){
        if (WXPayConstants.SUCCESS.equals(sortedMap.get("result_code"))){
            String out_trade_no = sortedMap.get("out_trade_no");
            Order outTradeNo = orderService.getOutTradeNo(out_trade_no);
            if (outTradeNo != null && outTradeNo.getState()==0){
                Order order=new Order();
                order.setOutTradeNo(out_trade_no);
                order.setNotifyTime(new Date());
                order.setState(1);
                int rows = orderService.updateOrderByOutTradeNo(order);
                if (rows == 1){
                    response.setContentType("text/xml");
                    response.getWriter().println("success");
                    return;
                }

            }
        }
    }
    //都处理失败
    response.setContentType("text/xml");
    response.getWriter().println("fail");
}

}
