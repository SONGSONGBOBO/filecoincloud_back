package com.songbo.filecoincloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.songbo.filecoincloud.entity.FccMessage;
import com.songbo.filecoincloud.mapper.FccMessageMapper;
import com.songbo.filecoincloud.service.IFccMessageService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author songbo
 * @since 2020-06-05
 */
@Service
public class FccMessageServiceImpl extends ServiceImpl<FccMessageMapper, FccMessage> implements IFccMessageService {

}
