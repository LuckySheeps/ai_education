package com.ai_education.result.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherTaskVO {
    // 任务id
    private int taskId;
    // 课程id
    private int courseId;
    // 任务名字
    private String taskName;
    // 截至时间
    private String deadline;
}
