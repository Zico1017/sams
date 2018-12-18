package com.zico.sams.handler;

import com.zico.sams.execption.SamsException;
import com.zico.sams.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zico
 * @version v1.0
 * @title ExceptionHandle
 * @package com.zico.sams.handler
 * @since 2018-12-15
 * description 异常捕获类
 **/
@ControllerAdvice
@CrossOrigin
public class ExceptionHandle {
    /**
     * 表明这个handler只处理什么类型的异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof SamsException) {
            SamsException samsException = (SamsException) e;
            return Result.error(samsException.getErrorNo(), samsException.getErrorMessage());
        }
        return Result.error("-1", e.getMessage());
    }
}
