package com.songbo.filecoincloud.mapper;

import com.songbo.filecoincloud.entity.FccUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author songbo
 * @since 2020-05-23
 */
public interface FccUserMapper extends BaseMapper<FccUser> {
    FccUser getByName(String name);
}
