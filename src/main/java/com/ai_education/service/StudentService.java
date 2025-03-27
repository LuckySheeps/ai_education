package com.ai_education.service;


import com.ai_education.pojo.Student;
import com.ai_education.result.Result;

public interface StudentService {
    //登录
    Student login(String studentPhone, String password);

    //注册(手机号，密码，身份[0学生，1老师])
    Boolean register(String phone, String password, Integer identity);

    //根据手机号查找
    Boolean selectByPhone(String phone);

    //查看个人信息
    Student getStudentById(int studentId);

    //修改个人信息
    boolean updateStudent(Student student);
}
