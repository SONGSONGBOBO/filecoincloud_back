package com.songbo.filecoincloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songbo.filecoincloud.entity.FccMessage;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author songbo
 * @since 2020-06-05
 */
public interface FccMessageMapper extends BaseMapper<FccMessage> {

    //内容较少不必分页
    List<FccMessage> getByTimeDescAndStatus(int status);

}
