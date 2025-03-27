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
@TableName("study_records")
public class StudyRecords {
    @TableId(value = "record_id",type = IdType.AUTO)
    private int recordId;
    @TableField("student_id")
    private int studentId;
    @TableField("course_id")
    private int courseId;
    @TableField("time")
    private String time;
}