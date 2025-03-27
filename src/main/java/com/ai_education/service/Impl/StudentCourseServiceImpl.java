package com.ai_education.service.Impl;

import com.ai_education.context.BaseContext;
import com.ai_education.mapper.StudentCourseMapper;
import com.ai_education.mapper.StudentCourseTasksMapper;
import com.ai_education.pojo.Student;
import com.ai_education.pojo.StudentCourse;
import com.ai_education.pojo.StudentCourseTasks;
import com.ai_education.service.StudentCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    private StudentCourseTasksMapper studentCourseTasksMapper;

    //开启事务
    @Transactional
    @Override
    public boolean removeStudentFromCourse(String courseId) {
        // 删除学生课程关系
        QueryWrapper<StudentCourse> studentCourseQuery = new QueryWrapper<>();
        studentCourseQuery.eq("student_id", BaseContext.getCurrentId()).eq("course_id", courseId);
        int deletedCourses = studentCourseMapper.delete(studentCourseQuery);

        // 删除学生任务关系
        QueryWrapper<StudentCourseTasks> studentTaskQuery = new QueryWrapper<>();
        studentTaskQuery.eq("student_id", BaseContext.getCurrentId()).eq("course_id", courseId);
        int deletedTasks = studentCourseTasksMapper.delete(studentTaskQuery);
        System.out.println(deletedTasks);

        return deletedCourses > 0 ;
    }

    @Override
    public List<Student> selectByCid(int courseId) {
        List<Student> studentsByCourseId = studentCourseMapper.findStudentsByCourseId(courseId);
        return studentsByCourseId;
    }
}
