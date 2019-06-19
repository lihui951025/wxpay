package com.lihui.springbootmybatis.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LiHui
 * @create 2019-06-18-11:44
 */
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private String id;
    private String name;
    private String phone;
    private String sex;
    private String city;
    private Date createTime;

}
