package com.ai_education.mapper;

import com.ai_education.pojo.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    //根据电话查找
    @Select("select * from teacher where phone = #{phone}")
    Teacher selectByPhone(String phone);
}
