package com.ai_education.service;

import com.ai_education.pojo.Teacher;

public interface TeacherService {
    //注册(手机号，密码，身份[0学生，1老师])
    Boolean register(String phone, String password, Integer identity);

    //根据手机号查找
    Boolean selectByPhone(String phone);

    //登录
    Teacher login(String phone, String password);

    //根据id查询
    Teacher getTeacherById(int teacherId);

    //更新信息
    boolean updateTeacher(Teacher teacher);
}
