package com.lihui.springbootmybatis.controller;

import com.lihui.springbootmybatis.entity.Commodity;
import com.lihui.springbootmybatis.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LiHui
 * @create 2019-06-18-14:49
 */
@RestController
@RequestMapping("/com")
public class CommodityController {
    @Autowired
    CommodityService commodityService;

    @PostMapping("/save")
    public void saveCommodityHandle(Commodity commodity){
        commodityService.save(commodity);
    }

    @GetMapping("/get/{id}")
    public Commodity getCommodityHandle(@PathVariable("id") String id){
        return commodityService.getCommodity(id);
    }

    @GetMapping("/all")
    public List<Commodity> getAllCommodityHandle(){
        return commodityService.getAllCommodity();
    }
}
