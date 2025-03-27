package com.ai_education.properties.xunFei;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "xufei2")
@Data
public class TranslateApiProperties {
    private String appId;
    private String translateApiSecret;
    private String translateApiKey;
}
