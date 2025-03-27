package com.ai_education.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("student_course_tasks")
public class StudentCourseTasks {
    @TableId(value = "student_course_task_id",type = IdType.AUTO)
    private int studentCourseTaskId;
    @TableField("student_id")
    private int studentId;
    @TableField("course_id")
    private int courseId;
    @TableField("task_id")
    private int taskId;
    @TableField("status")
    private int status;
}
