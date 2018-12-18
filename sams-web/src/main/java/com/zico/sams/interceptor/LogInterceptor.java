package com.zico.sams.interceptor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author zico
 * @version v1.0
 * @title LoggingFilter
 * @package com.zico.sams.filter
 * @since 2018-12-17
 * description 请求日志打印类
 **/
@Component
@Aspect
@Slf4j
public class LogInterceptor {

    @Pointcut("execution(* com.zico.sams.controller*..*(..))")
    public void log() {
    }

    @Before("log()")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute("RequestId", UUID.randomUUID().toString().replaceAll("-", ""));
        log.debug("============================request begin============================");
        log.debug("RemoteAddr    : {}", request.getRemoteAddr());
        log.debug("RequestURL    : {}", request.getRequestURL());
        log.debug("Type          : {}", request.getMethod());
        log.debug("Controller    : {}", joinPoint.getTarget().getClass().getName());
        log.debug("Method        : {}", joinPoint.getSignature().getName());
        log.debug("Request body  : {}", JSONObject.toJSONString(request.getParameterMap()));
        log.debug("RequestId     : {}", request.getAttribute("RequestId"));
        log.debug("============================request end==============================");
    }

    @AfterReturning(pointcut = "log()", returning = "returnValue")
    public void after(JoinPoint joinPoint, Object returnValue) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.debug("============================response begin===========================");
        log.debug("Status code   : {}", response.getStatus());
        log.debug("Response body : {}", JSONObject.toJSONString(returnValue));
        log.debug("RequestId     : {}", request.getAttribute("RequestId"));
        log.debug("============================response end=============================");
    }


}
