package com.songbo.filecoincloud.controller;


import com.songbo.filecoincloud.entity.FccGoods;
import com.songbo.filecoincloud.mapper.FccGoodsMapper;
import com.songbo.filecoincloud.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author songbo
 * @since 2020-05-23
 */
@RestController
@RequestMapping("/goods")
@Api(value ="前台商品接口", tags = {"前台商品接口"})
public class FccGoodsController {

    @Resource
    private FccGoodsMapper goodsMapper;


    @GetMapping("/getGoodsList")
    @ApiOperation(value = "获取商品列表")
    public ResultUtil getGoodsList() {
        try {
            List<FccGoods> goods = goodsMapper.selectList(null);
            return ResultUtil.result200("获取成功", goods);
        } catch (Exception e) {
            return ResultUtil.result500("获取失败", e.getMessage());
        }
    }
}
