package com.sg.zhsd.uav.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * FileProperties
 *
 * @author huangzd
 * @since 2019/4/23
 */
@Component
@ConfigurationProperties(prefix = "file")
@Data
public class FileProperties {
    
    private String rootPath = "/var/docs";

}