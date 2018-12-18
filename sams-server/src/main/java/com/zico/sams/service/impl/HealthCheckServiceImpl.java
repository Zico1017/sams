package com.zico.sams.service.impl;

import com.zico.sams.mapper.HealthCheckMapper;
import com.zico.sams.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zico
 * @version v1.0
 * @title HealthCheckServiceImpl
 * @package com.zico.sams.service.impl
 * @since 2018-12-17
 * description 健康检查实现类
 **/
@Service("healthCheckService")
public class HealthCheckServiceImpl implements HealthCheckService {

    @Autowired
    private HealthCheckMapper healthCheckMapper;

    @Override
    public Integer healthCheck() {
        return healthCheckMapper.healthCheck();
    }
}
