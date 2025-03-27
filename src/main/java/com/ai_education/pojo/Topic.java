package com.ai_education.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("topic")
public class Topic {
    @TableId(value = "id", type = IdType.AUTO)
    // 题目id
    private long id;
    // 任务id
    private long taskId;
    // 问题类型
    private String questionType;
    // 问题题目
    private String questionText;
    // 选项
    private String options1;
    private String options2;
    private String options3;
    private String options4;
    // 正确答案
    private String correctAnswer;
    // 创建时间
    private String createdAt;
    // 分数设置
    private int fullscore;
}
