package com.ai_education.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//资料表
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("material")
public class Material {
    @TableId(value = "material_id",type = IdType.AUTO)
    private String materialId;
    @TableField("course_id")
    private int courseId;
    @TableField("teacher_id")
    private int teacherId;
    @TableField("material_name")
    private String materialName;
    @TableField("material_type")
    //    文件大小
    private String materialType;
    @TableField("material_content")
    private String materialContent;
    @TableField("create_date")
    private String createDate;
}
