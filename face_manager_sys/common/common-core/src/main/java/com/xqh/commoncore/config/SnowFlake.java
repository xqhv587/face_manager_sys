package com.xqh.commoncore.config;

import com.xqh.commoncore.properties.SnowflakeProperties;
import com.xqh.commoncore.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ID生成配置
 *
 * @author tangyi
 * @date 2019/4/26 11:17
 */
@Configuration
public class SnowFlake {

    @Autowired
    private SnowflakeProperties snowflakeProperties;

    @Bean
    public SnowflakeIdWorker initTokenWorker() {
        return new SnowflakeIdWorker(Integer.parseInt(snowflakeProperties.getWorkId()), Integer.parseInt(snowflakeProperties.getDataCenterId()));
    }
}
