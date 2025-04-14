package com.example.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tourism.entity.HotelInfo;
import com.example.tourism.mapper.HotelInfoMapper;
import com.example.tourism.service.HotelService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class HotelServiceImpl extends ServiceImpl<HotelInfoMapper, HotelInfo> implements HotelService {

    @Override
    public IPage<HotelInfo> getHotelList(Integer pageNum, Integer pageSize, String keyword) {
        LambdaQueryWrapper<HotelInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HotelInfo::getStatus, 1); // 只查询启用的酒店
        
        if (keyword != null && !keyword.isEmpty()) {
            wrapper.like(HotelInfo::getHotelName, keyword)
                  .or()
                  .like(HotelInfo::getHotelAddress, keyword);
        }
        
        wrapper.orderByDesc(HotelInfo::getHotelCreateTime);
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public HotelInfo getHotelDetail(String id) {
        return getById(id);
    }

    @Override
    public boolean addHotel(HotelInfo hotelInfo) {
        // 生成酒店ID
        hotelInfo.setPortalHotelId(UUID.randomUUID().toString().replace("-", "").substring(0, 20));
        hotelInfo.setHotelCreateTime(LocalDateTime.now());
        hotelInfo.setStatus(1); // 默认启用
        return save(hotelInfo);
    }

    @Override
    public boolean updateHotel(HotelInfo hotelInfo) {
        hotelInfo.setHotelUpdateTime(LocalDateTime.now());
        return updateById(hotelInfo);
    }

    @Override
    public boolean deleteHotel(String id) {
        HotelInfo hotelInfo = getById(id);
        if (hotelInfo == null) {
            return false;
        }
        hotelInfo.setStatus(0); // 标记为删除
        hotelInfo.setHotelUpdateTime(LocalDateTime.now());
        return updateById(hotelInfo);
    }
} 