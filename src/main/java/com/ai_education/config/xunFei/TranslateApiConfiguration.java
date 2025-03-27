package com.ai_education.config.xunFei;

import com.ai_education.properties.xunFei.TranslateApiProperties;
import com.ai_education.utils.translateUtil.MachineTranslationUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TranslateApiConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MachineTranslationUtil machineTranslationUtil(TranslateApiProperties translateApiProperties){
        return new MachineTranslationUtil(translateApiProperties.getAppId(),
                translateApiProperties.getTranslateApiSecret(),
                translateApiProperties.getTranslateApiKey());
    }
}
