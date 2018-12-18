package com.zico.sams.mapper;

/**
 * @author zico
 * @version v1.0
 * @title HealthCheckMapper
 * @package com.zico.sams.mapper
 * @since 2018-12-17
 * description 健康检查mapper
 **/
public interface HealthCheckMapper {

    /**
     * 健康检查
     *
     * @return 正常返回1
     */
    Integer healthCheck();
}
