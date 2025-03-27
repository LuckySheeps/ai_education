package com.ai_education.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {
    /**
     * 生成jwt令牌相关配置
     */
    private String secretKey;
    private long time;
    private String tokenName;
}
