package com.songbo.filecoincloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author songbo
 * @since 2020-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FccOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "fcc_order_id")
    private String fccOrderId;

    private Integer fccOrderGoodsId;

    private Integer fccOrderUserId;

    private Double fccOrderPrice;

    private Integer fccOrderStatus;

    private Long fccOrderCreateTime;

    private Long fccOrderUpdateTime;

    public FccOrder() {
    }

    public FccOrder(String fccOrderId, Integer fccOrderGoodsId, Integer fccOrderUserId, Double fccOrderPrice, Integer fccOrderStatus, Long fccOrderCreateTime, Long fccOrderUpdateTime) {
        this.fccOrderId = fccOrderId;
        this.fccOrderGoodsId = fccOrderGoodsId;
        this.fccOrderUserId = fccOrderUserId;
        this.fccOrderPrice = fccOrderPrice;
        this.fccOrderStatus = fccOrderStatus;
        this.fccOrderCreateTime = fccOrderCreateTime;
        this.fccOrderUpdateTime = fccOrderUpdateTime;
    }
}
