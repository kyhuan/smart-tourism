package com.example.tourism.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tourism.entity.ScenicSpot;

public interface ScenicSpotService extends IService<ScenicSpot> {
    /**
     * 分页查询景点列表
     */
    IPage<ScenicSpot> getScenicList(Integer pageNum, Integer pageSize, String keyword, String category);

    /**
     * 获取景点详情
     */
    ScenicSpot getScenicDetail(String id);

    /**
     * 添加景点
     */
    boolean addScenic(ScenicSpot scenicSpot);

    /**
     * 更新景点信息
     */
    boolean updateScenic(ScenicSpot scenicSpot);

    /**
     * 删除景点
     */
    boolean deleteScenic(String id);
} 