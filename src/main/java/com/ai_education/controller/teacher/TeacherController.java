package com.ai_education.controller.teacher;

import com.ai_education.context.BaseContext;
import com.ai_education.pojo.Course;
import com.ai_education.pojo.Material;
import com.ai_education.pojo.Student;
import com.ai_education.pojo.Teacher;
import com.ai_education.result.DTO.CourseInfo;
import com.ai_education.result.Result;
import com.ai_education.service.CourseService;
import com.ai_education.service.MaterialService;
import com.ai_education.service.StudentCourseService;
import com.ai_education.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aiEducation/teacher")
@CrossOrigin
public class TeacherController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentCourseService studentCourseService;

    //个人信息查看
    @GetMapping("/personalInfo")
    public Result getTeacherById() {
        Teacher teacher = teacherService.getTeacherById(Integer.parseInt(BaseContext.getCurrentId()));
        if (teacher!=null){
            return Result.success("查看个人信息",teacher);
        }
        return Result.error("查看失败！");
    }

    //个人信息修改
    @PutMapping("/personalInfo/update")
    public Result updateTeacher(@RequestBody Teacher teacher) {
        teacher.setTeacherId(Integer.parseInt(BaseContext.getCurrentId()));
        if (teacherService.updateTeacher(teacher)&&courseService.updateTeacherName(teacher.getTeacherId(),teacher.getName())){
            return Result.success("修改成功！");
        }
        return Result.error("修改失败！");
    }

    //显示老师对应课程
    @GetMapping("/courses")
    public List<CourseInfo> getCoursesByTeacherId() {
        return courseService.getCoursesByTeacherId(Integer.parseInt(BaseContext.getCurrentId()));
    }

    //添加课程
    @PostMapping("/add")
    public Result addCourse(@RequestBody Course course) {
        String icode =courseService.addCourse(course);
        if (icode!=null) {
            return Result.success("添加成功,邀请码",icode);
        } else {
            return Result.error("添加失败！");
        }
    }

    //删除资料
    @DeleteMapping("/delete")
    public Result deleteMaterial(@RequestParam("courseId") int courseId, @RequestParam("materialId") String materialId) {
        boolean isDeleted = materialService.deleteMaterial(courseId, materialId);
        if (isDeleted) {
            return Result.success("资料删除成功");
        } else {
            return Result.error("资料删除失败");
        }
    }

    //展示课程下的所有学生信息
    @GetMapping("/displayStudent/{courseId}")
    public Result displayAllStudent(@PathVariable("courseId") int courseId){
        List<Student> listStu = studentCourseService.selectByCid(courseId);
        return Result.success("学生信息",listStu);
    }
}
