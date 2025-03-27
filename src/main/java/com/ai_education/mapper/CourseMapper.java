package com.ai_education.mapper;

import com.ai_education.pojo.Course;
import com.ai_education.result.DTO.CourseInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    //根据学生id查找课程id,课程名称,课程图片,老师名
    @Select("SELECT c.course_id AS courseId,\n" +
            "c.course_name AS courseName, \n" +
            "c.course_image AS courseImage,\n" +
            "c.teacher_name AS teacherName\n" +
            "FROM sc\n" +
            "JOIN course c ON sc.course_id = c.course_id\n" +
            "WHERE sc.student_id = #{studentId}")
    List<CourseInfo> findCoursesByStudentId(@Param("studentId") int studentId);

    //根据老师id查找课程
    @Select("SELECT c.course_id, c.course_name, c.course_image,c.invitation_code, t.name  AS teacher_name " +
            "FROM course c " +
            "JOIN teacher t ON c.teacher_id = t.teacher_id " +
            "WHERE t.teacher_id = #{teacherId}")
    List<CourseInfo> getCoursesByTeacherId(@Param("teacherId") int teacherId);

    //根据邀请码查找班级
    @Select("SELECT * FROM course WHERE invitation_code = #{invitationCode}")
    Course findByInvitationCode(@Param("invitationCode") String invitationCode);

    //根据学生id和课程id查找选课
    @Select("SELECT COUNT(*) FROM sc WHERE student_id = #{studentId} AND course_id = #{courseId}")
    int isStudentInCourse(@Param("studentId") int studentId, @Param("courseId") int courseId);

    @Update("UPDATE course set teacher_name = #{teacherName} where teacher_id = #{teacherId}")
    boolean updateTeacherName(@Param("teacherId")int teacherId, @Param("teacherName")String teacherName);
}
