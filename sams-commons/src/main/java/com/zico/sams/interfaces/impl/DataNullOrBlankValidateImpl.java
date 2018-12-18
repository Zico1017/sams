package com.zico.sams.interfaces.impl;

import com.google.common.collect.Maps;
import com.zico.sams.interfaces.DataNullOrBlankValidate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zico
 * @version v1.0
 * @title DataNullOrBlankValidateImpl
 * @package com.zico.sams.interfaces.impl
 * @since 2018-12-17
 * description
 **/
@Slf4j
public class DataNullOrBlankValidateImpl implements DataNullOrBlankValidate {

    private static Map<String, Method> methodMap;

    public DataNullOrBlankValidateImpl() {
        //初始化方法，将各种参数类型对应的校验方法加载到内存中
        methodMap = Maps.newHashMap();
        for (Method method : DataNullOrBlankValidate.class.getDeclaredMethods()) {
            //遍历所有私有方法
            //获取方法名称
            String methodName = method.getName();
            //判断方法名称是否有validate前缀
            if (StringUtils.startsWith(methodName, "validate")) {
                //将校验方法存入map中<K,V>=<paramClassName,validateMethod>
                methodMap.put(StringUtils.substringAfter(methodName, "validate"), method);
            }
        }
    }

    /**
     * 校验方法对外方法
     *
     * @param paramName     参数名称
     * @param paramClass    参数类型
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */

    @Override
    public void dataValidate(String paramName, Class paramClass, Map<String, Object> paramValueMap, AtomicBoolean flg) {
        String paramClassName = paramClass.getSimpleName();
        try {
            //根据参数类型获取校验方法
            Method method = methodMap.get(paramClassName);
            //执行校验方法
            method.invoke(this, paramName, paramValueMap, flg);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("dataValidate error", e);
        }
    }

    /**
     * String类型参数校验方法
     *
     * @param paramName     参数名称
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    @Override
    public void validateString(String paramName, Map<String, Object> paramValueMap, AtomicBoolean flg) {
        String value = MapUtils.getString(paramValueMap, paramName);
        flg.set(StringUtils.isBlank(value));
    }

    /**
     * Integer类型参数校验方法
     *
     * @param paramName     参数名称
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    @Override
    public void validateInteger(String paramName, Map<String, Object> paramValueMap, AtomicBoolean flg) {
        Integer value = MapUtils.getInteger(paramValueMap, paramName);
        flg.set(value == null);
    }

    /**
     * Double类型参数校验方法
     *
     * @param paramName     参数名称
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    @Override
    public void validateDouble(String paramName, Map<String, Object> paramValueMap, AtomicBoolean flg) {
        Double value = MapUtils.getDouble(paramValueMap, paramName);
        flg.set(value == null);
    }

    /**
     * Float类型参数校验方法
     *
     * @param paramName     参数名称
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    @Override
    public void validateFloat(String paramName, Map<String, Object> paramValueMap, AtomicBoolean flg) {
        Float value = MapUtils.getFloat(paramValueMap, paramName);
        flg.set(value == null);
    }

    /**
     * Long类型参数校验方法
     *
     * @param paramName     参数名称
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    @Override
    public void validateLong(String paramName, Map<String, Object> paramValueMap, AtomicBoolean flg) {
        Long value = MapUtils.getLong(paramValueMap, paramName);
        flg.set(value == null);
    }

    /**
     * Boolean类型参数校验方法
     *
     * @param paramName     参数名称
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    @Override
    public void validateBoolean(String paramName, Map<String, Object> paramValueMap, AtomicBoolean flg) {
        Boolean value = MapUtils.getBoolean(paramValueMap, paramName);
        flg.set(value == null);
    }

    /**
     * List类型参数校验方法
     *
     * @param paramName     参数名称
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    @Override
    public void validateList(String paramName, Map<String, Object> paramValueMap, AtomicBoolean flg) {
        List value = (List) MapUtils.getObject(paramValueMap, paramName);
        flg.set(CollectionUtils.isEmpty(value));
    }

    /**
     * Map类型参数校验方法
     *
     * @param paramName     参数名称
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    @Override
    public void validateMap(String paramName, Map<String, Object> paramValueMap, AtomicBoolean flg) {
        Map value = MapUtils.getMap(paramValueMap, paramName);
        flg.set(MapUtils.isEmpty(value));
    }

    /**
     * Object类型参数校验方法
     *
     * @param paramName     参数名称
     * @param paramValueMap 参数值map
     * @param flg           校验标记
     */
    @Override
    public void validateObject(String paramName, Map<String, Object> paramValueMap, AtomicBoolean flg) {
        Object value = MapUtils.getObject(paramValueMap, paramName);
        flg.set(value == null);
    }
}
