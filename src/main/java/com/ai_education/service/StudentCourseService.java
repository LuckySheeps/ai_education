package com.ai_education.service;

import com.ai_education.pojo.Student;

import java.util.List;

public interface StudentCourseService {
    boolean removeStudentFromCourse(String courseId);

    List<Student> selectByCid(int courseId);
}
