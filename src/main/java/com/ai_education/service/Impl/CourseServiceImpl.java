package com.ai_education.service.Impl;

import com.ai_education.context.BaseContext;
import com.ai_education.mapper.CourseMapper;
import com.ai_education.mapper.StudentCourseMapper;
import com.ai_education.mapper.TeacherMapper;
import com.ai_education.pojo.Course;
import com.ai_education.pojo.StudentCourse;
import com.ai_education.result.DTO.CourseInfo;
import com.ai_education.service.CourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    //根据学生id查询课程列表
    @Override
    public List<CourseInfo> getCoursesByStudentId(int studentId) {
        return courseMapper.findCoursesByStudentId(studentId);
    }

    //根据老师id查询课程列表
    @Override
    public List<CourseInfo> getCoursesByTeacherId(int teacherId) {
        return courseMapper.getCoursesByTeacherId(teacherId);
    }

    //根据邀请码查找课程
    @Override
    public Course findByInvitationCode(String invitationCode) {
        return courseMapper.findByInvitationCode(invitationCode);
    }

    //根据课程id,学生id查找选课情况
    @Override
    public boolean isStudentInCourse(String studentId, int courseId) {
        return courseMapper.isStudentInCourse(Integer.parseInt(studentId), courseId) > 0;
    }

    //根据学生id，根据课程id来添加课程
    @Override
    public boolean addStudentToCourse(String studentId, int courseId) {
        if (!isStudentInCourse(studentId, courseId)) {
            StudentCourse studentCourse = new StudentCourse();
            studentCourse.setStudentId(Integer.parseInt(studentId));
            studentCourse.setCourseId(courseId);
            studentCourseMapper.insert(studentCourse);
            return true;
        }
        return false;
    }

    @Override
    public String addCourse(Course course) {
        course.setTeacherId(Integer.parseInt(BaseContext.getCurrentId()));

        // 检查课程名是否唯一
        if (!isCourseNameUniqueForTeacher(course.getCourseName(), String.valueOf(course.getTeacherId()))) {
            throw new IllegalArgumentException("您已经添加了这个课程！！！");
        }
        // 从教师表中获取教师名称
        String teacherName = teacherMapper.selectById(course.getTeacherId()).getName();
        if (teacherName == null) {
            throw new IllegalArgumentException("添加课程中：Invalid teacher ID");
        }

        course.setTeacherName(teacherName);

        // 生成唯一的邀请码
        String invitationCode;
        do {
            invitationCode = generateInvitationCode();
            System.out.println(invitationCode);
        } while (isInvitationCodeExists(invitationCode));

        course.setInvitationCode(invitationCode);

        if(courseMapper.insert(course) > 0){
            return invitationCode;
        }

        return null;
    }

    //查找该课程是否存在
    @Override
    public boolean isCourseNameUniqueForTeacher(String courseName, String teacherId) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_name", courseName).eq("teacher_id", teacherId);
        return courseMapper.selectCount(queryWrapper) == 0;
    }

    @Override
    public Course getCourseById(int courseId) {
        return courseMapper.selectById(courseId);
    }

    @Override
    public boolean updateTeacherName(int teacherId, String teacherName) {
        return courseMapper.updateTeacherName(teacherId,teacherName);
    }

    //生成uuid方法
    private String generateInvitationCode() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }

    //查找生成的邀请码是否唯一
    private boolean isInvitationCodeExists(String invitationCode) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("invitation_code", invitationCode);
        return courseMapper.selectCount(queryWrapper) > 0;
    }
}
