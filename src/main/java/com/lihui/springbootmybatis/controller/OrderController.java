package com.lihui.springbootmybatis.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.lihui.springbootmybatis.entity.Order;
import com.lihui.springbootmybatis.service.OrderService;
import lombok.Builder;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author LiHui
 * @create 2019-06-18-16:38
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/save/{videoId}/{userid}")
    public void saveOrder(@PathVariable("videoId") String videoId, @PathVariable("userid")String userid,
                          HttpServletRequest request,
                          HttpServletResponse response, Model model) throws Exception {
        //临时写死
        String ip = "192.168.31.183";
        Order order=new Order();
        order.setIp(ip);
        order.setUserId(userid);
        order.setVideoId(videoId);
        String codeUrl = orderService.save(order);
        if (codeUrl == null){
            throw  new NullPointerException();
        }

        try {
            //生成二维码配置
            HashMap<EncodeHintType, Object> hints = new HashMap<>();

            //设置纠错等级
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            //编码类型
            hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE,400,400,hints);
            ServletOutputStream out = response.getOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix,"png",out);

        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
