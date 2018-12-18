package com.zico.sams.utils;

import com.zico.sams.constant.SystemConstant;
import com.zico.sams.execption.SamsException;
import com.zico.sams.interfaces.DataNullOrBlankValidate;
import com.zico.sams.interfaces.impl.DataNullOrBlankValidateImpl;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zico
 * @version v1.0
 * @title ParamsUtils
 * @package com.iboxpay.mbp.util
 * @since 2018-11-21
 * description 参数校验工具类
 **/
public class ParamsUtils {

    private static DataNullOrBlankValidate dataNullOrBlankValidate = new DataNullOrBlankValidateImpl();

    public static void validateNullOrBlank(Map<String, Class> paramNameMap, Map<String, Object> paramValueMap) {
        AtomicBoolean flg = new AtomicBoolean(false);
        paramNameMap.forEach((paramName, paramClass) -> {
            if (StringUtils.equals(paramName, SystemConstant.TOKEN)) {
                // 校验token是否有效
                String value = MapUtils.getString(paramValueMap, paramName);
                RedisUtils.fetchUserInfo(value);
            } else {
                // 校验参数是否为空
                dataNullOrBlankValidate.dataValidate(paramName, paramClass, paramValueMap, flg);
            }
            if (flg.get()) {
                // 参数为空抛异常
                throw new SamsException("ERROR_PARAM_IS_NULL", paramName);
            }
        });
    }

}
