package com.zico.sams.controller;

import com.google.common.collect.Maps;
import com.zico.sams.annotation.NoNeedLogin;
import com.zico.sams.service.HealthCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zico
 * @version v1.0
 * @title HealthCheckController
 * @package com.zico.sams.controller
 * @since 2018-12-17
 * description 健康检查
 **/
@Api("健康检查Controller")
@RestController
public class HealthCheckController {

    @Autowired
    private HealthCheckService healthCheckService;

    @ApiOperation(value = "健康检查", notes = "健康检查正常则resultCode返回0")
    @NoNeedLogin
    @GetMapping("/healthCheck")
    public Object healthCheck() {
        Integer result = healthCheckService.healthCheck();
        Map<String, Object> map = Maps.newHashMap();
        map.put("resultCode", result == 1 ? "0" : "1");
        return map;
    }

}
