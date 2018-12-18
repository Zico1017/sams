package com.zico.sams.interfaces;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zico
 * @version v1.0
 * @title DataNullOrBlankValidate
 * @package com.zico.sams.interfaces
 * @since 2018-12-17
 * description
 **/
public interface DataNullOrBlankValidate extends DataValidate {

    /**
     * String类型参数校验方法
     *
     * @param paramName     参数名称
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    void validateString(String paramName, Map<String, Object> paramValueMap, AtomicBoolean flg);

    /**
     * Integer类型参数校验方法
     *
     * @param paramName     参数名称
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    void validateInteger(String paramName, Map<String, Object> paramValueMap, AtomicBoolean flg);

    /**
     * Double类型参数校验方法
     *
     * @param paramName     参数名称
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    void validateDouble(String paramName, Map<String, Object> paramValueMap, AtomicBoolean flg);

    /**
     * Float类型参数校验方法
     *
     * @param paramName     参数名称
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    void validateFloat(String paramName, Map<String, Object> paramValueMap, AtomicBoolean flg);

    /**
     * Long类型参数校验方法
     *
     * @param paramName     参数名称
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    void validateLong(String paramName, Map<String, Object> paramValueMap, AtomicBoolean flg);

    /**
     * Boolean类型参数校验方法
     *
     * @param paramName     参数名称
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    void validateBoolean(String paramName, Map<String, Object> paramValueMap, AtomicBoolean flg);

    /**
     * List类型参数校验方法
     *
     * @param paramName     参数名称
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    void validateList(String paramName, Map<String, Object> paramValueMap, AtomicBoolean flg);

    /**
     * Map类型参数校验方法
     *
     * @param paramName     参数名称
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    void validateMap(String paramName, Map<String, Object> paramValueMap, AtomicBoolean flg);

    /**
     * Object类型参数校验方法
     *
     * @param paramName     参数名称
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    void validateObject(String paramName, Map<String, Object> paramValueMap, AtomicBoolean flg);
}
