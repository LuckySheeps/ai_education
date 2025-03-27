package com.ai_education.controller.course;

import com.ai_education.context.BaseContext;
import com.ai_education.pojo.Course;
import com.ai_education.pojo.Material;
import com.ai_education.pojo.Teacher;
import com.ai_education.result.Result;
import com.ai_education.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aiEducation/course")
@CrossOrigin
public class CourseController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private RecordService recordService;

    //课程首页
    @GetMapping("/{courseId}")
    public Result getCourseDetails(@PathVariable Integer courseId) {
        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            return Result.error("课程不存在");
        }

        Teacher teacher = teacherService.getTeacherById(course.getTeacherId());
        if (teacher == null) {
            return Result.error("老师不存在");
        }

        // 学生学习记录加一次
        recordService.addRecord(BaseContext.getCurrentId(),courseId);

        Map<String, Object> response = new HashMap<>();
        // ToDo  aiCourseDetail:"智能ai对于这门课的介绍和评价和学习建议（5百字以内）"
        String aiCourseDetail = null;
        Map<String, Object> courseDetails = new HashMap<>();
        courseDetails.put("courseId", course.getCourseId());
        courseDetails.put("invitationCode", course.getInvitationCode());
        courseDetails.put("courseName", course.getCourseName());
        courseDetails.put("courseImage", course.getCourseImage());
        courseDetails.put("courseIntroduce", course.getCourseIntroduce());
        courseDetails.put("courseDemand", course.getCourseDemand());
        courseDetails.put("courseTarget", course.getCourseTarget());

        Map<String, Object> teacherDetails = new HashMap<>();
        teacherDetails.put("teacherId", teacher.getTeacherId());
        teacherDetails.put("name", teacher.getName());
        teacherDetails.put("phone", teacher.getPhone());
        teacherDetails.put("position", teacher.getPosition());
        teacherDetails.put("introduction", teacher.getIntroduction());

        response.put("course", courseDetails);
        response.put("teacher", teacherDetails);
        response.put("aiCourseDetail",aiCourseDetail);

        return Result.success("课程查询成功", response);
    }

    //学生课程任务展示
    @GetMapping("/courseTasks/{courseId}")
    public Result showCourseTasks(@PathVariable Integer courseId){
        return Result.success("任务展示",taskService.getTasksByStudentIdAndCourseId(Integer.parseInt(BaseContext.getCurrentId()), courseId));
    }

    //课程资料展示
    @GetMapping("/material/{courseId}")
    public Result getMaterialsByCourseId(@PathVariable int courseId) {
        List<Material> materials = materialService.getMaterialsByCourseId(courseId);

        if (materials.isEmpty()) {
            return Result.error("这个课程没有资料可查");
        }

        List<Map<String, Object>> materialList = materials.stream().map(material -> {
            Teacher teacher = teacherService.getTeacherById(material.getTeacherId());

            Map<String, Object> materialMap = new HashMap<>();
            materialMap.put("materialId", material.getMaterialId());
            materialMap.put("name", material.getMaterialName());
            materialMap.put("content", material.getMaterialContent());
            materialMap.put("type", material.getMaterialType());
            materialMap.put("creator", teacher != null ? teacher.getName() : "Unknown");
            materialMap.put("createDate", material.getCreateDate());

            return materialMap;
        }).collect(Collectors.toList());

        return Result.success("资料查询成功", materialList);
    }
}
