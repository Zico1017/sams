package com.zico.sams.execption;

import com.zico.sams.config.ErrorMessageConfig;
import com.zico.sams.config.ErrorMessageConfigReader;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zico
 * @version v1.0
 * @title SamsExecption
 * @package com.zico.sams.execption
 * @since 2018-12-15
 * description 自定义异常类
 **/
@Data
public class SamsException extends RuntimeException {

    private String errorNo;

    private String errorMessage;

    public SamsException(String desc) {
        ErrorMessageConfig errorMessageConfig = ErrorMessageConfigReader.getConfig(desc);
        this.errorNo = errorMessageConfig.getErrorNo();
        this.errorMessage = StringUtils.replace(errorMessageConfig.getMessage(), "{0}", errorMessage);
    }

    public SamsException(String desc, String errorMessage) {
        ErrorMessageConfig errorMessageConfig = ErrorMessageConfigReader.getConfig(desc);
        this.errorNo = errorMessageConfig.getErrorNo();
        this.errorMessage = StringUtils.replace(errorMessageConfig.getMessage(), "{0}", errorMessage);
    }

}
