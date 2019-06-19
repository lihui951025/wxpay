package com.lihui.springbootmybatis.service.impl;

import com.lihui.springbootmybatis.entity.Commodity;
import com.lihui.springbootmybatis.mapper.CommodityMapper;
import com.lihui.springbootmybatis.service.CommodityService;
import com.lihui.springbootmybatis.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author LiHui
 * @create 2019-06-18-14:47
 */
@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    CommodityMapper commodityMapper;
    @Override
    public void save(Commodity commodity) {
        commodity.setId(WXPayUtil.generateUUID());
        commodity.setCreateTime(new Date());
        commodityMapper.save(commodity);
    }

    @Override
    public Commodity getCommodity(String id) {
        return commodityMapper.getCommodity(id);
    }

    @Override
    public List<Commodity> getAllCommodity() {
        return commodityMapper.getAllCommodity();
    }
}
