package com.songbo.filecoincloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.songbo.filecoincloud.entity.FccOrder;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author songbo
 * @since 2020-06-05
 */
public interface IFccOrderService extends IService<FccOrder> {



    //更新订单状态
    void refreshOrders();
}
