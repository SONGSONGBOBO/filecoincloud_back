package com.songbo.filecoincloud.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName Goods
 * @Description TODO
 * @Author songbo
 * @Date 2020/5/23 下午5:19
 **/
@Data
@ApiModel("商品实体")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("商品id")
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

    public Goods() {
    }

    public Goods(Integer fccGoodsId, String fccGoodsName, String fccGoodsTitle, String fccGoodsContent, Double fccGoodsPrice, Integer fccGoodsNum) {
        this.fccGoodsId = fccGoodsId;
        this.fccGoodsName = fccGoodsName;
        this.fccGoodsTitle = fccGoodsTitle;
        this.fccGoodsContent = fccGoodsContent;
        this.fccGoodsPrice = fccGoodsPrice;
        this.fccGoodsNum = fccGoodsNum;
    }
}