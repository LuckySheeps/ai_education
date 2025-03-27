package com.ai_education.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//学生表
@Data
@TableName("student")
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @TableId(value = "student_id",type = IdType.AUTO)
    private int studentId;
    @TableField(value = "student_phone")
    private String studentPhone;
    @TableField("student_image")
    private String studentImage;
    @TableField(value = "name")
    private String name;
    @TableField(value = "age")
    private int age;
    @TableField(value = "sex")
    private String sex;
    @TableField(value = "password")
    private String password;
    @TableField(value = "identity")
    private int identity;
    @TableField(value = "address")
    private String address;
    @TableField(value = "school_name")
    private String schoolName;
    @TableField(value = "educational_background")
    private String educationalBackground;
}
