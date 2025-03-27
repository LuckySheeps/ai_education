package com.ai_education.result.VO;

import com.ai_education.pojo.TaskRecords;
import com.ai_education.pojo.Topic;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskTopicDetailVO {
    // 题目id
    private Long topicId;
    // 任务id
    private Long taskId;
    // 填写答案
    private String answer;
    // 正确答案
    private String rightAnswer;
    // 完成状态
    private String result;
    // 题目类型
    private String questionType;
    // 分数
    private String getScore;
    // 问题题目
    private String questionText;
    // 选项
    private String options1;
    private String options2;
    private String options3;
    private String options4;
    // 创建时间
    private String createdAt;
    // 分数设置
    private int fullscore;
}
