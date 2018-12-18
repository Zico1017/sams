package com.zico.sams;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zico
 * @version v1.0
 * @title SamsWebApplication
 * @package com.zico.sams
 * @since 2018-12-15
 * description 启动类
 **/

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.zico.sams.mapper")
@EnableCaching
@EnableSwagger2
public class SamsWebApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SamsWebApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SamsWebApplication.class, args);
    }

}

