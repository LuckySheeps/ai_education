package com.ai_education.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("task_records")
public class TaskRecords {
    // 题目id
    @TableId("topic_id")
    private Long topicId;
    // 任务id
    private Long taskId;
    // 学生id
    private Long studentId;
    // 填写答案
    private String answer;
    // 正确答案
    private String rightAnswer;
    // 完成状态
    private String result;
    // 题目类型
    private String questionType;
    // ai判断
    private String aiContent;
    // 分数
    private String getScore;
}
