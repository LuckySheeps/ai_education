package com.ai_education.result.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuTaskInfoVO {
    // 学生ID
    private int stuId;
    // 学生姓名
    private String stuName;
    // 批阅状态
    private int status;
    // 分数
    private int score;
}
