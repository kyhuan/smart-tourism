package com.example.tourism.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.tourism.common.Result;
import com.example.tourism.entity.HotelInfo;
import com.example.tourism.service.HotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotel")
@Api(tags = "酒店管理")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/list")
    @ApiOperation("分页查询酒店列表")
    public Result<IPage<HotelInfo>> getHotelList(
            @RequestParam(defaultValue = "1") Integer pageNum, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyword) {
        return Result.success(hotelService.getHotelList(pageNum, pageSize, keyword));
    }

    @GetMapping("/detail/{id}")
    @ApiOperation("获取酒店详情")
    public Result<HotelInfo> getHotelDetail(@PathVariable String id) {
        return Result.success(hotelService.getHotelDetail(id));
    }

    @PostMapping("/add")
    @ApiOperation("添加酒店")
    public Result<Boolean> addHotel(@RequestBody HotelInfo hotelInfo) {
        return Result.success(hotelService.addHotel(hotelInfo));
    }

    @PutMapping("/update")
    @ApiOperation("更新酒店信息")
    public Result<Boolean> updateHotel(@RequestBody HotelInfo hotelInfo) {
        return Result.success(hotelService.updateHotel(hotelInfo));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除酒店")
    public Result<Boolean> deleteHotel(@PathVariable String id) {
        return Result.success(hotelService.deleteHotel(id));
    }
} 