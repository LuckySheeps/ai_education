package com.ai_education.mapper;

import com.ai_education.pojo.Material;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MaterialMapper extends BaseMapper<Material> {
    @Select("SELECT * FROM material WHERE course_id = #{courseId}")
    List<Material> findByCourseId(int courseId);
}
