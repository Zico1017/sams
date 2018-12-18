package com.zico.sams.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;

/**
 * @author zico
 * @version v1.0
 * @title ErrorMessageFileListener
 * @package com.zico.sams.config
 * @since 2018-12-15
 * description 文件监听类
 **/
@Slf4j
public class ErrorMessageFileListener extends FileAlterationListenerAdaptor {

    @Override
    public void onFileChange(File file) {
        log.debug("{}文件更新", file.getName());
        ErrorMessageConfigReader.reload();
    }
}
