package com.zico.sams.config;

import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileFilter;

/**
 * @author zico
 * @version v1.0
 * @title FileListenerRunner
 * @package com.zico.sams.config
 * @since 2018-12-15
 * description springboot启动时执行任务
 **/
@Component
@Slf4j
public class FileListenerRunner implements CommandLineRunner {

    private static final String BIZ_SYSTEM_PROPERTIES = "errorMessage.json";

    @Override
    public void run(String... args) throws Exception {
        FileAlterationMonitor monitor = new FileAlterationMonitor();

        FileFilter fileFilter = FileFilterUtils.nameFileFilter(BIZ_SYSTEM_PROPERTIES);

        String path = FileUtils.toFile(Resources.getResource(BIZ_SYSTEM_PROPERTIES)).getParent();

        FileAlterationObserver observer = new FileAlterationObserver(path, fileFilter);
        observer.addListener(new ErrorMessageFileListener());

        monitor.addObserver(observer);
        //开始监听
        monitor.start();
        log.debug("errorMessage.json文件监听启动……");
    }
}
