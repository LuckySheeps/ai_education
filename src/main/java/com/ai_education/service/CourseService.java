package com.ai_education.service;

import com.ai_education.pojo.Course;
import com.ai_education.result.DTO.CourseInfo;

import java.util.List;

public interface CourseService {
    //根据学生id查询课程列表
    List<CourseInfo> getCoursesByStudentId(int studentId);

    //根据老师id查询课程列表
    List<CourseInfo> getCoursesByTeacherId(int teacherId);

    //根据邀请码查找课程
    Course findByInvitationCode(String invitationCode);

    //根据课程id,学生id查找选课情况
    boolean isStudentInCourse(String studentId, int courseId);

    //根据学生id，根据课程id来添加课程
    boolean addStudentToCourse(String studentId, int courseId);

    //添加课程
    String addCourse(Course course);

    //查找同一个老师添加的课程是否存在
    boolean isCourseNameUniqueForTeacher(String courseName, String teacherId);

    //根据id查询
    Course getCourseById(int courseId);

    //更新老师名字
    boolean updateTeacherName(int teacherId, String name);
}
