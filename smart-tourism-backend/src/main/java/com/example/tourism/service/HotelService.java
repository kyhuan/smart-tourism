package com.example.tourism.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tourism.entity.HotelInfo;

public interface HotelService extends IService<HotelInfo> {
    /**
     * 分页查询酒店列表
     */
    IPage<HotelInfo> getHotelList(Integer pageNum, Integer pageSize, String keyword);

    /**
     * 获取酒店详情
     */
    HotelInfo getHotelDetail(String id);

    /**
     * 添加酒店
     */
    boolean addHotel(HotelInfo hotelInfo);

    /**
     * 更新酒店信息
     */
    boolean updateHotel(HotelInfo hotelInfo);

    /**
     * 删除酒店
     */
    boolean deleteHotel(String id);
} 