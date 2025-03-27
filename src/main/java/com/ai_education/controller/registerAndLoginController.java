package com.ai_education.controller;

import com.ai_education.pojo.Student;
import com.ai_education.pojo.Teacher;
import com.ai_education.properties.JwtProperties;
import com.ai_education.result.Result;
import com.ai_education.service.CourseService;
import com.ai_education.service.StudentService;
import com.ai_education.service.TeacherService;
import com.ai_education.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/aiEducation")
@CrossOrigin
public class registerAndLoginController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private JwtProperties jwtProperties;

    //注册(手机号，密码，身份[0学生，1老师]),后期可以加入短信验证
    @PostMapping("/register")
    public Result register(@RequestParam("phone") String phone,
                           @RequestParam("password") String password,
                           @RequestParam("identity") Integer identity){
        //学生注册
        if (identity == 0){
            //判断手机号是否重复注册
            Boolean phoneFlag = studentService.selectByPhone(phone);
            if (phoneFlag){
                return Result.error("手机号已存在");
            }else {
                //进行注册
                Boolean registerFlag = studentService.register(phone,password,identity);
                if (registerFlag){
                    return Result.success("学生注册成功！");
                }else{
                    return Result.success("学生注册失败!");
                }
            }
        }
        //老师注册
        else {
            //判断手机号是否重复注册
            Boolean phoneFlag = teacherService.selectByPhone(phone);
            if (phoneFlag){
                return Result.error("手机号已存在");
            }else {
                //进行注册
                Boolean registerFlag = teacherService.register(phone,password,identity);
                if (registerFlag){
                    return Result.success("老师注册成功！");
                }else{
                    return Result.success("老师注册失败!");
                }
            }
        }
    }

    //登录(手机号，密码，身份[0学生，1老师])
    @PostMapping("/login")
    public Result login(@RequestParam("phone") String phone,
                        @RequestParam("password") String password,
                        @RequestParam("identity") Integer identity){
        System.out.println(phone+password+identity);
        //学生登录
        if (identity == 0){
            Student loginStudent = studentService.login(phone, password);
            System.out.println(loginStudent);
            //登录成功后，生成jwt令牌
            if (loginStudent!=null){
                Map<String,Object> claims = new HashMap<>();
                claims.put("identity",identity);
                claims.put("studentId",loginStudent.getStudentId());
                claims.put("studentPhone",loginStudent.getStudentPhone());
                claims.put("studentName",loginStudent.getName());
                //生成token
                String token = JwtUtil.createJWT(
                        jwtProperties.getSecretKey(),
                        jwtProperties.getTime(),
                        claims);
                claims.put("token",token);
                System.out.println("-============================="+token);
                return Result.success("登录成功!",claims);
            }
            return Result.error("登录失败！");
        }
        //老师登录
        else {
            Teacher loginTeacher = teacherService.login(phone, password);
            System.out.println(loginTeacher);
            //登录成功后，生成jwt令牌
            if (loginTeacher!=null){
                Map<String,Object> claims = new HashMap<>();
                claims.put("identity",identity);
                claims.put("teacherId",loginTeacher.getTeacherId());
                claims.put("teacherPhone",loginTeacher.getPhone());
                claims.put("teacherName",loginTeacher.getName());
                //生成token
                String token = JwtUtil.createJWT(
                        jwtProperties.getSecretKey(),
                        jwtProperties.getTime(),
                        claims);
                claims.put("token",token);
                return Result.success("登录成功!",claims);
            }
            return Result.error("登录失败！");
        }
    }

}
