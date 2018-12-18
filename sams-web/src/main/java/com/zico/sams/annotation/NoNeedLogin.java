package com.zico.sams.annotation;

import java.lang.annotation.*;

/**
 * @author zico
 * @version v1.0
 * @title NoNeedLogin
 * @package com.zico.sams.annotation
 * @since 2018-12-15
 * description 免登陆
 **/

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoNeedLogin {
}
