package com.lihui.springbootmybatis.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LiHui
 * @create 2019-06-18-11:47
 */
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Commodity implements Serializable {
    private String id;
    private String title;
    private String summary;
    private Integer price;
    private Date createTime;
    private Integer online;
    private Double point;
}
