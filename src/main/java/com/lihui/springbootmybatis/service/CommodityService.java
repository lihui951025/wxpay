package com.lihui.springbootmybatis.service;

import com.lihui.springbootmybatis.entity.Commodity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LiHui
 * @create 2019-06-18-14:46
 */
public interface CommodityService {
    void save(Commodity commodity);

    Commodity getCommodity( String id);

    List<Commodity> getAllCommodity();
}
