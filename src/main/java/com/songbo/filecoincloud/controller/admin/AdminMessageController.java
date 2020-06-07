package com.songbo.filecoincloud.controller.admin;

import com.songbo.filecoincloud.bean.Goods;
import com.songbo.filecoincloud.entity.FccMessage;
import com.songbo.filecoincloud.mapper.FccMessageMapper;
import com.songbo.filecoincloud.service.IFccMessageService;
import com.songbo.filecoincloud.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName AdminMessageController
 * @Description TODO
 * @Author songbo
 * @Date 2020/6/5 下午1:59
 **/
@RestController
@RequestMapping("/admin/message")
@Api(value = "后台新闻管理接口", tags = {"后台新闻管理接口"})
public class AdminMessageController {
    @Autowired
    private IFccMessageService messageService;
    @Resource
    private FccMessageMapper fccMessage;


    @PostMapping("/addMessage")
    @ApiOperation(value = "添加新闻")
    public ResultUtil addGoods(@RequestBody FccMessage fccMessage) {
        try {
            messageService.save(fccMessage);
            return ResultUtil.result200("添加成功", fccMessage);
        } catch (Exception e) {
            return ResultUtil.result500("添加失败", e.getMessage());
        }
    }

    @PostMapping("/deleteMessage")
    @ApiOperation(value = "删除新闻")
    public ResultUtil deleteGooods(@RequestParam @ApiParam("新闻id") int id) {
        try {
            messageService.removeById(id);
            return ResultUtil.result200("删除成功", id);
        } catch (Exception e) {
            return ResultUtil.result500("删除失败", e.getMessage());
        }
    }

    @PostMapping("/updateMessage/{messageId}")
    @ApiOperation(value = "更新新闻")
    public ResultUtil updateGooods(@RequestBody FccMessage message, @PathVariable int messageId) {
        try {
            message.setFccMessageId(messageId);
            messageService.updateById(message);
            return ResultUtil.result200("更新成功", message);
        } catch (Exception e) {
            return ResultUtil.result500("更新失败", e.getMessage());
        }
    }
}
