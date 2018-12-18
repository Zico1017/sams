package com.zico.sams.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zico
 * @version v1.0
 * @title ErrorMessageConfig
 * @package com.zico.sams.config
 * @since 2018-12-14
 * description 错误信息类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageConfig {

    private String errorNo;

    private String message;

}
