package com.ai_education.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//老师表
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("teacher")
public class Teacher {
    @TableId(value = "teacher_id",type = IdType.AUTO)
    private int teacherId;
    @TableField("name")
    private String name;
    @TableField("position")
    private String position;
    @TableField("identity")
    private int identity;
    @TableField("phone")
    private String phone;
    @TableField("password")
    private String password;
    @TableField("teacher_image")
    private String teacherImage;
    @TableField("sex")
    private int sex;
    @TableField("introduction")
    private String introduction;
}
