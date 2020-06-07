package com.songbo.filecoincloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author songbo
 * @since 2020-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("商品实体")
public class FccGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "fcc_goods_id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer fccGoodsId;
    @ApiModelProperty("商品标题1")
    private String fccGoodsName;
    @ApiModelProperty("商品标题2")
    private String fccGoodsTitle;
    @ApiModelProperty("商品详细json")
    private String fccGoodsContent;
    @ApiModelProperty("商品价格")
    private Double fccGoodsPrice;
    @ApiModelProperty("商品库存")
    private Integer fccGoodsNum;
    @ApiModelProperty(hidden = true)
    private Integer fccGoodsStatus;

    public FccGoods() {
    }

    public FccGoods(Integer fccGoodsId, String fccGoodsName, String fccGoodsTitle, String fccGoodsContent, Double fccGoodsPrice, Integer fccGoodsNum, Integer fccGoodsStatus) {
        this.fccGoodsId = fccGoodsId;
        this.fccGoodsName = fccGoodsName;
        this.fccGoodsTitle = fccGoodsTitle;
        this.fccGoodsContent = fccGoodsContent;
        this.fccGoodsPrice = fccGoodsPrice;
        this.fccGoodsNum = fccGoodsNum;
        this.fccGoodsStatus = fccGoodsStatus;
    }
}
