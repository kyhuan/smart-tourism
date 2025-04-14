package com.example.tourism.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tourism.entity.TravelContent;

import java.util.Map;

/**
 * 旅游内容服务接口
 */
public interface TravelContentService extends IService<TravelContent> {

    /**
     * 获取旅游内容列表（分页）
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @param params   查询参数
     * @return 分页结果
     */
    IPage<TravelContent> getTravelContentList(Integer pageNum, Integer pageSize, Map<String, Object> params);

    /**
     * 获取旅游内容详情
     *
     * @param id 内容ID
     * @return 内容详情
     */
    TravelContent getTravelContentDetail(Long id);

    /**
     * 审核旅游内容
     *
     * @param id     内容ID
     * @param status 审核状态 1-通过 2-拒绝
     * @param remark 审核备注
     * @return 是否成功
     */
    boolean auditTravelContent(Long id, Integer status, String remark);

    /**
     * 删除旅游内容
     *
     * @param id 内容ID
     * @return 是否成功
     */
    boolean deleteTravelContent(Long id);
} 