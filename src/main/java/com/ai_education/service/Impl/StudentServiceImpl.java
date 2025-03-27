package com.ai_education.service.Impl;

import com.ai_education.context.BaseContext;
import com.ai_education.mapper.CourseMapper;
import com.ai_education.mapper.StudentMapper;
import com.ai_education.pojo.Course;
import com.ai_education.pojo.Student;
import com.ai_education.result.Result;
import com.ai_education.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CourseMapper courseMapper;

    //登录
    @Override
    public Student login(String studentPhone, String password) {
        Student loginStudent = studentMapper.selectByPhone(studentPhone);
        if (loginStudent!=null&&loginStudent.getPassword().equals(password)){
            return loginStudent;
        }
        return null;
    }

    //注册(手机号，密码，身份[0学生，1老师])
    @Override
    public Boolean register(String phone, String password, Integer identity) {
        Student student = new Student();
        student.setStudentPhone(phone);
        student.setPassword(password);
        student.setIdentity(identity);
        student.setName("12");
        int flag = studentMapper.insert(student);
        if (flag!=0){
            return true;
        }
        return false;
    }

    //根据手机号查找
    @Override
    public Boolean selectByPhone(String phone) {
        Student student = studentMapper.selectByPhone(phone);
        //手机号存在
        if (student!=null){
            return true;
        }
        //手机号不存在
        return false;
    }

    @Override
    public Student getStudentById(int studentId) {
        return studentMapper.selectById(studentId);
    }

    @Override
    public boolean updateStudent(Student student) {
        return studentMapper.updateById(student) > 0;
    }
}
