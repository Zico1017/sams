package com.zico.sams.interfaces;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zico
 * @version v1.0
 * @title DataValidate
 * @package com.zico.sams.interfaces
 * @since 2018-12-17
 * description
 **/
public interface DataValidate {

    /**
     * 校验方法对外方法
     *
     * @param paramName     参数名称
     * @param paramClass    参数类型
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    void dataValidate(String paramName, Class paramClass, Map<String, Object> paramValueMap, AtomicBoolean flg);
}
