package com.lihui.springbootmybatis.config;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import static com.lihui.springbootmybatis.utils.WXPayConstants.DOMAIN_API;
import static com.lihui.springbootmybatis.utils.WXPayConstants.UNIFIEDORDER_URL_SUFFIX;

/**
 * @author LiHui
 * @create 2019-06-18-10:13
 * 微信支付所使用的数据
 */
@Configuration
@PropertySource(value="classpath:application.properties")
@Data
@Setter
@Getter
@ToString
public class WeChatConfig {
    //公众号APPID
    @Value("${wxpay.appid}")
    private String appId;

    //微信公众号密钥
    @Value("${wxpay.appsecret}")
    private String appsecret;

//    //回调url
//    @Value("${wxpay.redirect_url}")
//    private String redirect_url;

    //商户号Id
    @Value("${wxpay.mer_id}")
    private String mchID;

    //支付key
    @Value("${wxpay.key}")
    private String key;

    //微信回调
    @Value("${wxpay.callback}")
    private String payCallbackUrl;

    //统一下单Url
    public static final String UNIFIED_ORDER_URL = "https://" + DOMAIN_API + UNIFIEDORDER_URL_SUFFIX;
}


