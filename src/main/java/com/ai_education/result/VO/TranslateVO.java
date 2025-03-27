package com.ai_education.result.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslateVO {
    private String fromLanguage;
    private String toLanguage;
    private String content;
}
