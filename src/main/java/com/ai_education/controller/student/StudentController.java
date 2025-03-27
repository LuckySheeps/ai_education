package com.ai_education.controller.student;

import com.ai_education.context.BaseContext;
import com.ai_education.pojo.*;
import com.ai_education.result.DTO.CourseInfo;
import com.ai_education.result.Result;
import com.ai_education.result.DTO.TaskInfo;
import com.ai_education.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/aiEducation/student")
@CrossOrigin
public class StudentController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentCourseService studentCourseService;

    //首页
    @GetMapping("/HomePage")
    public Result homePage(){
        //获得当前用户id
        String studentId = BaseContext.getCurrentId();
        System.out.println("===============HomePage:=========="+studentId);

        List<TaskInfo> tasksByStudentId = taskService.getTasksByStudentId(Integer.parseInt(studentId));

        List<CourseInfo> courseAll = courseService.getCoursesByStudentId(Integer.parseInt(studentId));

        //封装数据
        Map<String,Object> data = new HashMap<>();

        data.put("taskAll",tasksByStudentId);
        data.put("courseAll",courseAll);
        System.out.println(data);
        return Result.success("首页数据",data);
    }

    //加入课程
    @PostMapping("/addCourse")
    public Result addCourse(@RequestParam("invitationCode") String invitationCode){
        Course course = courseService.findByInvitationCode(invitationCode);
        if (course == null){
            return Result.error("邀请码失效！");
        }
        if (courseService.isStudentInCourse(BaseContext.getCurrentId(),course.getCourseId())){
            return Result.error("已经加入该课程不能重复加入！");
        }
        if (courseService.addStudentToCourse(BaseContext.getCurrentId(),course.getCourseId())){
            return Result.success("成功加入该课程！");
        }
        return Result.error("加入该课程失败！");
    }

    //个人信息查看
    @GetMapping("/personalInfo")
    public Result getStudentById() {
        Student student = studentService.getStudentById(Integer.parseInt(BaseContext.getCurrentId()));
        if (student!=null){
            return Result.success("查看个人信息",studentService.getStudentById(Integer.parseInt(BaseContext.getCurrentId())));
        }
        return Result.error("查看失败！");
    }
    //个人信息修改
    @PutMapping("/personalInfo/update")
    public Result updateStudent(@RequestBody Student student) {
        student.setStudentId(Integer.parseInt(BaseContext.getCurrentId()));
        if (studentService.updateStudent(student)){
            return Result.success("修改成功！");
        }
        return Result.error("修改失败！");
    }

    //删除课程
    @DeleteMapping("/remove/{courseId}")
    public Result removeStudentFromCourse(@PathVariable String courseId) {
        boolean success = studentCourseService.removeStudentFromCourse(courseId);
        if (success) {
            return Result.success("退课成功！");
        } else {
            return Result.error("退课失败！");
        }
    }

}
