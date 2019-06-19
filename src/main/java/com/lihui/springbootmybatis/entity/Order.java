package com.lihui.springbootmybatis.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LiHui
 * @create 2019-06-18-11:49
 */
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    private String id;
    private String outTradeNo;
    private Integer state;
    private Date createTime;
    private Date notifyTime;
    private Integer totalFee;
    private String nickName;
    private String videoId;
    private String videoTitle;
    private String userId;
    private String ip;
    private Integer del;
}
