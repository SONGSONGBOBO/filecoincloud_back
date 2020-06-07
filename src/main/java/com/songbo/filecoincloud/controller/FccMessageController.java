package com.songbo.filecoincloud.controller;


import com.songbo.filecoincloud.entity.FccGoods;
import com.songbo.filecoincloud.entity.FccMessage;
import com.songbo.filecoincloud.mapper.FccMessageMapper;
import com.songbo.filecoincloud.service.IFccMessageService;
import com.songbo.filecoincloud.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2020-06-05
 */
@RestController
@RequestMapping("/message")
@Api(value ="前台新闻接口", tags = {"前台新闻接口"})
public class FccMessageController {

    @Autowired
    private IFccMessageService messageService;
    @Resource
    private FccMessageMapper messageMapper;

    @GetMapping("/getMessageList")
    @ApiOperation(value = "获取新闻列表")
    public ResultUtil getGoodsList() {
        try {
            List<FccMessage> messages = messageMapper.getByTimeDesc();
            return ResultUtil.result200("获取成功", messages);
        } catch (Exception e) {
            return ResultUtil.result500("获取失败", e.getMessage());
        }
    }
}
