package com.ai_education.controller.student;

import com.ai_education.pojo.TaskRecords;
import com.ai_education.pojo.Topic;
import com.ai_education.result.Result;
import com.ai_education.service.Impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aiEducation/student")
@CrossOrigin
public class StuTaskController {

    @Autowired
    TaskServiceImpl taskService;

    // 根据任务id查任务的所有题目，一般学生做题时调用
    @GetMapping("/getTopic")
    Result getByid(@RequestParam("taskId") int taskId) {
        Result result = taskService.getByTaskId(taskId);
        return result;
    }

    // 任务提交
    @PostMapping("/submitTask")
    Result submitTask(@RequestBody List<TaskRecords> taskRecords) {
        System.out.println(taskRecords);
        Result result = taskService.submitTask(taskRecords);
        return result;
    }

    // 完成后查看任务详情
    @GetMapping("/getTaskTopicDetail/{taskId}")
    Result getTaskTopicDetail(@PathVariable("taskId") int taskId) {
        Result result = taskService.getTaskTopicDetail(taskId);
        return result;
    }

}
