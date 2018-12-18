package com.zico.sams.handler;

import com.zico.sams.annotation.NoNeedLogin;
import com.zico.sams.constant.SystemConstant;
import com.zico.sams.execption.SamsException;
import com.zico.sams.redis.entity.RedisObject;
import com.zico.sams.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author zico
 * @version v1.0
 * @title LoginInterceptor
 * @package com.zico.sams.handler
 * @since 2018-12-15
 * description 登录拦截器
 **/
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final String SWAGGER = "swagger";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 该方法注明NoNeedLogin，跳过验证token
        if (method.getAnnotation(NoNeedLogin.class) != null) {
            return true;
        }

        //swagger资源
        if (StringUtils.contains( request.getServletPath(),SWAGGER)) {
            return true;
        }

        String userToken = request.getHeader(SystemConstant.TOKEN);

        if (StringUtils.isBlank(userToken)) {
            throw new SamsException("ERROR_PARAM_IS_NULL","token");
        }
        RedisObject redisObject = RedisUtils.fetch(userToken);
        if (!redisObject.isOk()) {
            throw new SamsException("LOGIN_TIME_OUT");
        }
        //更新token失效时间
        RedisUtils.update(userToken);

        return true;
    }


}
