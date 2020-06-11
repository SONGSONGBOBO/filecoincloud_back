package com.songbo.filecoincloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.songbo.filecoincloud.bean.coinpayments.GetTxInfoRequest;
import com.songbo.filecoincloud.entity.FccOrder;
import com.songbo.filecoincloud.mapper.FccOrderMapper;
import com.songbo.filecoincloud.service.CoinpaymentsService;
import com.songbo.filecoincloud.service.IFccOrderService;
import com.songbo.filecoincloud.utils.CommonUtil;
import com.songbo.filecoincloud.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.brunocvcunha.coinpayments.model.TransactionDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author songbo
 * @since 2020-06-05
 */
@Service
@Slf4j
public class FccOrderServiceImpl extends ServiceImpl<FccOrderMapper, FccOrder> implements IFccOrderService {

    @Resource
    private FccOrderMapper orderMapper;
    @Autowired
    private CoinpaymentsService coinpaymentsService;
    @Override
    public void refreshOrders() {
        List<FccOrder> orders = orderMapper.getByFromTo(-1, 100);
        long now = TimeUtil.getInstance().getNowLong().get();
        log.warn("refreshOrders start: "+ now);
        for (FccOrder order : orders) {
            try {
                TransactionDetailsResponse txInfo = coinpaymentsService.getTxInfo(new GetTxInfoRequest(CommonUtil.COINPAYMENTS_GET_TX_INFO, order.getFccOrderId()));
                int status = txInfo.getStatus();
                if (status != order.getFccOrderStatus()) {
                    order.setFccOrderStatus(status);
                    order.setFccOrderUpdateTime(now);
                    orderMapper.updateById(order);
                }
            } catch (Exception e) {
                log.error("refreshOrders: "+e.getMessage());
            }
        }
    }
}
