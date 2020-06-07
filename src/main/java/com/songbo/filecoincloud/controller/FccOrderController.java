package com.songbo.filecoincloud.controller;


import com.songbo.filecoincloud.bean.coinpayments.CreateRequest;
import com.songbo.filecoincloud.entity.FccGoods;
import com.songbo.filecoincloud.entity.FccOrder;
import com.songbo.filecoincloud.entity.FccUser;
import com.songbo.filecoincloud.mapper.FccOrderMapper;
import com.songbo.filecoincloud.mapper.FccUserMapper;
import com.songbo.filecoincloud.service.CoinpaymentsService;
import com.songbo.filecoincloud.service.IFccGoodsService;
import com.songbo.filecoincloud.service.IFccOrderService;
import com.songbo.filecoincloud.service.IFccUserService;
import com.songbo.filecoincloud.utils.CommonUtil;
import com.songbo.filecoincloud.utils.ResultUtil;
import com.songbo.filecoincloud.utils.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.brunocvcunha.coinpayments.model.CreateTransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/order")
@Api(value ="前台订单接口", tags = {"前台订单接口"})
public class FccOrderController {

    @Autowired
    private IFccOrderService orderService;
    @Resource
    private FccOrderMapper orderMapper;
    @Resource
    private IFccGoodsService goodsService;
    @Autowired
    private CoinpaymentsService coinpaymentsService;
    @Autowired
    private IFccUserService userService;
    @Resource
    private FccUserMapper userMapper;


    @GetMapping("/makeOrder")
    @ApiOperation(value = "创建订单,一种商品")
    public ResultUtil makeOrder(@RequestParam @ApiParam("商品id") int goodsId,
                                @RequestParam @ApiParam("商品数量") int num,
                                @RequestHeader("userId") @ApiParam("请求头中userId") int userId) {
        try {
            long now = TimeUtil.getInstance().getNowLong().get();
            FccGoods goods = goodsService.getById(goodsId);
            FccUser user = userService.getById(userId);
            double price = goods.getFccGoodsPrice() * num;
            CreateTransactionResponse transaction = coinpaymentsService.createTransaction(new CreateRequest(CommonUtil.COINPAYMENTS_CREATE_TRANSACTION,
                    price, "USDT.ERC20", "USDT.ERC20", user.getFccUserMail() == null ? CommonUtil.COINPAYMENTS_MAIL : user.getFccUserMail(),
                    CommonUtil.COINPAYMENTS_ADDRESS, user.getFccUserName()));

            orderService.save(new FccOrder(transaction.getTransactionId(), goodsId, userId, transaction.getAmount(), 1, now, now));
            return ResultUtil.result200("创建订单成功", goods);
        } catch (Exception e) {
            return ResultUtil.result500("创建订单失败", e.getMessage());
        }
    }
}
