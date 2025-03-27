package com.ai_education.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//任务表
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("task")
public class Task {
    @TableId(value = "task_id",type = IdType.AUTO)
    private int taskId;
    @TableField("course_id")
    private int courseId;
    @TableField("task_name")
    private String taskName;
    @TableField("task_content")
    private String taskContent;
    @TableField("deadline")
    private String deadline;
}
