package com.ai_education.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//课程表
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("course")
public class Course {
    @TableId(value = "course_id",type = IdType.AUTO)
    private int courseId;
    @TableField("course_name")
    private String courseName;
    @TableField("course_image")
    private String courseImage;
    @TableField("teacher_id")
    private int teacherId;
    @TableField("teacher_name")
    private String teacherName;
    @TableField("invitation_code")
    private String invitationCode;
    @TableField("course_introduce")
    private String courseIntroduce;
    @TableField("course_demand")
    private String courseDemand;
    @TableField("course_target")
    private String courseTarget;
}
