package com.songbo.filecoincloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songbo.filecoincloud.entity.FccOrder;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author songbo
 * @since 2020-06-05
 */
public interface FccOrderMapper extends BaseMapper<FccOrder> {

    //查询
    List<FccOrder> getByStatus(int status);
}
