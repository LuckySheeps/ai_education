package com.ai_education.service.Impl;

import com.ai_education.mapper.TeacherMapper;
import com.ai_education.pojo.Teacher;
import com.ai_education.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    //注册(手机号，密码，身份[0学生，1老师])
    @Override
    public Boolean register(String phone, String password, Integer identity) {
        Teacher teacher = new Teacher();
        teacher.setIdentity(identity);
        teacher.setPhone(phone);
        teacher.setPassword(password);

        int flag = teacherMapper.insert(teacher);
        if (flag!=0){
            return true;
        }
        return false;
    }

    //根据手机号查找
    @Override
    public Boolean selectByPhone(String phone) {
        Teacher teacher = teacherMapper.selectByPhone(phone);
        //手机号存在
        if (teacher!=null){
            return true;
        }
        //手机号不存在
        return false;
    }

    //登录
    @Override
    public Teacher login(String phone, String password) {
        Teacher teacher = teacherMapper.selectByPhone(phone);
        if (teacher!=null&&teacher.getPassword().equals(password)){
            return teacher;
        }
        return null;
    }

    @Override
    public Teacher getTeacherById(int teacherId) {
        return teacherMapper.selectById(teacherId);
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        return teacherMapper.updateById(teacher) > 0;
    }
}
