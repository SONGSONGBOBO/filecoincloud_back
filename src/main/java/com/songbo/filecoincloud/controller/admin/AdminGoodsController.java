package com.songbo.filecoincloud.controller.admin;

import com.songbo.filecoincloud.bean.Goods;
import com.songbo.filecoincloud.entity.FccGoods;
import com.songbo.filecoincloud.mapper.FccGoodsMapper;
import com.songbo.filecoincloud.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName AdminGoodsController
 * @Description TODO
 * @Author songbo
 * @Date 2020/5/23 下午2:40
 **/
@RestController
@RequestMapping("/admin/goods")
@Api(value = "后台商品管理接口", tags = {"后台商品管理接口"})
public class AdminGoodsController {

    @Resource
    private FccGoodsMapper goodsMapper;

    @PostMapping("/addGoods")
    @ApiOperation(value = "添加商品")
    public ResultUtil addGoods(@RequestBody FccGoods fccGoods) {
        try {
           goodsMapper.insert(fccGoods);
            return ResultUtil.result200("添加成功", fccGoods);
        } catch (Exception e) {
            return ResultUtil.result500("添加失败", e.getMessage());
        }
    }

    @PostMapping("/deleteGooods")
    @ApiOperation(value = "删除商品")
    public ResultUtil deleteGooods(@RequestParam @ApiParam("商品id") int id) {
        try {
            goodsMapper.deleteById(id);
            return ResultUtil.result200("删除成功", id);
        } catch (Exception e) {
            return ResultUtil.result500("删除失败", e.getMessage());
        }
    }

    @PostMapping("/updateGooods")
    @ApiOperation(value = "更新商品")
    public ResultUtil updateGooods(@RequestBody Goods goods) {
        try {

            goodsMapper.updateById(new FccGoods(
                    goods.getFccGoodsId(), goods.getFccGoodsName(), goods.getFccGoodsTitle(), goods.getFccGoodsContent(), goods.getFccGoodsPrice(), goods.getFccGoodsNum(), null));
            return ResultUtil.result200("更新成功", goods);
        } catch (Exception e) {
            return ResultUtil.result500("更新失败", e.getMessage());
        }
    }
}
