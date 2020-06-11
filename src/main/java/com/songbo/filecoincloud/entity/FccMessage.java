package com.songbo.filecoincloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@ApiModel("新闻实体")
public class FccMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "fcc_message_id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer fccMessageId;
    @ApiModelProperty("新闻标题")
    private String fccMessageTitle;
    @ApiModelProperty("新闻内容")
    private String fccMessageContent;
    @ApiModelProperty("新闻图片列表")
    private String fccMessageImgs;
    @ApiModelProperty("时间")
    private Long fccMessageTime;
    @ApiModelProperty("新闻种类，1为中文，2为英文")
    private Integer fccMessageStatus;

    public FccMessage() {
    }

    public FccMessage(String fccMessageTitle, String fccMessageContent, String fccMessageImgs, Long fccMessageTime) {
        this.fccMessageTitle = fccMessageTitle;
        this.fccMessageContent = fccMessageContent;
        this.fccMessageImgs = fccMessageImgs;
        this.fccMessageTime = fccMessageTime;
    }
}
