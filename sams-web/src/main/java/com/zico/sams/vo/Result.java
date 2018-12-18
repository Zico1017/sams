package com.zico.sams.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author zico
 * @version v1.0
 * @title Result
 * @package com.zico.sams.vo
 * @since 2018-12-15
 * description 前端返回类
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    /**
     * 响应码
     */
    private Integer resultCode;

    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误描述
     */
    private String errorInfo;

    /**
     * 具体的内容.
     */
    private Object result;

    public static Result success() {
        Result result = new Result();
        result.setResultCode(0);
        return result;
    }

    public static Result error(String errorCode, String errorInfo) {
        Result result = new Result();
        result.setResultCode(1);
        result.setErrorCode(errorCode);
        result.setErrorInfo(errorInfo);
        return result;
    }

}
