package com.ai_education.mapper;

import com.ai_education.pojo.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    //根据电话号码查找
    @Select("select * from student where student_phone = #{studentPhone}")
    Student selectByPhone(String studentPhone);
}
