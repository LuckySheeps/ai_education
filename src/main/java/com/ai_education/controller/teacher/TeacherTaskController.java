package com.ai_education.controller.teacher;


import com.ai_education.context.BaseContext;
import com.ai_education.pojo.Task;
import com.ai_education.pojo.TaskRecords;
import com.ai_education.pojo.Topic;
import com.ai_education.result.DTO.TopicS;
import com.ai_education.result.Result;
import com.ai_education.result.VO.StuTaskInfoVO;
import com.ai_education.service.Impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aiEducation/teacher")
@CrossOrigin
public class TeacherTaskController {

    @Autowired
    TaskServiceImpl taskService;

    // 创建任务
    @PostMapping("/addTask")
    Result creatTask(@RequestBody Task task) {
        return taskService.creatTask(task);
    }

    // 创建任务后提交的题目集
    @PostMapping("/addTopics")
    Result addTopic(@RequestBody TopicS topicS) {
        Result result = taskService.addTopics(topicS);
        return result;
    }

    // 查看课程对应的所有任务
    @GetMapping("/courseTasks/{courseId}")
    public Result showCourseTasks(@PathVariable Integer courseId) {
        return Result.success("任务展示", taskService.getTasksByCourseId(courseId));
    }

    // 删除课程对应的任务，根据id
    @DeleteMapping("/deleteTask/{taskId}")
    public Result deleteTask(@PathVariable Integer taskId) {
        Integer i = taskService.deleteTask(taskId);
        if (i != 0) {
            return Result.success("任务删除成功");
        } else {
            return Result.error("任务删除失败");
        }
    }

    // 查看课程对应的所有学生任务状态
    @GetMapping("/courseTasks/getOne/{taskId}")
    public Result showAllStuCourseTasks(@PathVariable Integer taskId) {
        List<StuTaskInfoVO> stuTaskInfoVOS = taskService.showAllStuCourseTasks(taskId);
        if (stuTaskInfoVOS==null){
            return Result.success("没有学生");
        }
        return Result.success("任务展示", stuTaskInfoVOS);
    }

    // 查看学生提交的题目
    @GetMapping("/courseTasks/getOne/{taskId}/getStudentAllTopic/{stuId}")
    public Result getStudentAllTopic(@PathVariable("taskId") Integer taskId,
                                     @PathVariable("stuId") Integer stuId) {

        return taskService.getStudentAllTopic(taskId, stuId);
    }

    // 批改题目
    @PostMapping("/courseTasks/getOne/{taskId}/getStudentAllTopic/{stuId}/topics")
    public Result updateTopics(@PathVariable("taskId") Integer taskId,
                               @PathVariable("stuId") Integer stuId,
                               @RequestBody List<TaskRecords> taskRecords) {
        return taskService.updateTopics(taskId,stuId,taskRecords);
    }

}
