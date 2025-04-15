package com.example.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tourism.entity.ScenicSpot;
import com.example.tourism.mapper.ScenicSpotMapper;
import com.example.tourism.service.ScenicSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 景点服务实现类
 */
@Service
public class ScenicSpotServiceImpl extends ServiceImpl<ScenicSpotMapper, ScenicSpot> implements ScenicSpotService {

    @Autowired
    private ScenicSpotMapper scenicSpotMapper;

    @Override
    public IPage<ScenicSpot> getScenicList(Integer pageNum, Integer pageSize, String keyword, String category) {
        Page<ScenicSpot> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<ScenicSpot> queryWrapper = new LambdaQueryWrapper<>();
        
        // 只查询状态为启用的景点
        queryWrapper.eq(ScenicSpot::getStatus, 1);
        
        // 关键词搜索
        if (keyword != null && !keyword.isEmpty()) {
            queryWrapper.and(wrapper -> wrapper
                    .like(ScenicSpot::getName, keyword)
                    .or()
                    .like(ScenicSpot::getDescription, keyword)
                    .or()
                    .like(ScenicSpot::getLocation, keyword)
            );
        }
        
        // 分类搜索
        if (category != null && !category.isEmpty()) {
            // 这里假设景点分类存储在数据库中的字段名为category
            // 如果实际上分类是存储在其他表中，可能需要调整查询方式
            queryWrapper.like(ScenicSpot::getCategory, category);
        }
        
        // 根据创建时间降序排序
        queryWrapper.orderByDesc(ScenicSpot::getCreateTime);
        
        return page(page, queryWrapper);
    }

    @Override
    public ScenicSpot getScenicDetail(String id) {
        return getById(id);
    }

    @Override
    @Transactional
    public boolean addScenic(ScenicSpot scenicSpot) {
        // 设置创建时间和更新时间
        LocalDateTime now = LocalDateTime.now();
        if (scenicSpot.getCreateTime() == null) {
            scenicSpot.setCreateTime(now);
        }
        if (scenicSpot.getUpdateTime() == null) {
            scenicSpot.setUpdateTime(now);
        }
        
        // 默认状态为启用
        if (scenicSpot.getStatus() == null) {
            scenicSpot.setStatus(1);
        }
        
        // 生成唯一ID
        if (scenicSpot.getScenicId() == null || scenicSpot.getScenicId().isEmpty()) {
            // 生成一个UUID作为景点ID
            String uuid = java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 20);
            scenicSpot.setScenicId(uuid);
        }
        
        return save(scenicSpot);
    }

    @Override
    @Transactional
    public boolean updateScenic(ScenicSpot scenicSpot) {
        // 更新更新时间
        scenicSpot.setUpdateTime(LocalDateTime.now());
        return updateById(scenicSpot);
    }

    @Override
    @Transactional
    public boolean deleteScenic(String id) {
        // 软删除，将状态设置为0表示停用
        ScenicSpot scenicSpot = new ScenicSpot();
        scenicSpot.setScenicId(id);
        scenicSpot.setStatus(0);
        scenicSpot.setUpdateTime(LocalDateTime.now());
        return updateById(scenicSpot);
    }
} 