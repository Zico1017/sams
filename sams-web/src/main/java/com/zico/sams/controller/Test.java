package com.zico.sams.controller;

import com.zico.sams.redis.entity.RedisObject;
import com.zico.sams.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zico
 * @version v1.0
 * @title Test
 * @package com.zico.sams.controller
 * @since 2018-12-17
 * description
 **/
@Api("测试用的Api")
@RestController
public class Test {

    @ApiOperation(value = "测试controller", notes = "测试专用")
    @GetMapping("/test")
    public String test() {
        RedisUtils.store("test", "ashdjkhasjkdhas");
        RedisObject test = RedisUtils.fetch("test");
        if (test.isOk()) {
            return (String) test.getValue();
        }
        return null;
    }
}
