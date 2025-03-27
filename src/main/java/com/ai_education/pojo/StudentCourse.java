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
@TableName("sc")
public class StudentCourse {
    @TableId(value = "sc_id",type = IdType.AUTO)
    private int scId;
    @TableField("student_id")
    private int studentId;
    @TableField("course_id")
    private int courseId;
}
