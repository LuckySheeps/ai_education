package com.ai_education.mapper;

import com.ai_education.pojo.Student;
import com.ai_education.pojo.StudentCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentCourseMapper extends BaseMapper<StudentCourse> {

    @Select("SELECT s.student_id AS studentId, " +
            "s.student_phone AS studentPhone, " +
            "s.student_image AS studentImage, " +
            "s.name AS name, " +
            "s.age AS age, " +
            "s.sex AS sex, " +
            "s.address AS address, " +
            "s.school_name AS schoolName, " +
            "s.educational_background AS educationalBackground " +
            "FROM sc " +
            "JOIN student s ON sc.student_id = s.student_id " +
            "WHERE sc.course_id = #{courseId}")
    List<Student> findStudentsByCourseId(@Param("courseId") int courseId);
}
