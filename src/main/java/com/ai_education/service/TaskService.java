package com.ai_education.service;


import com.ai_education.pojo.Task;
import com.ai_education.pojo.TaskRecords;
import com.ai_education.pojo.Topic;
import com.ai_education.result.DTO.CourseTask;
import com.ai_education.result.DTO.TaskInfo;
import com.ai_education.result.DTO.TopicS;
import com.ai_education.result.Result;
import com.ai_education.result.VO.StuTaskInfoVO;
import com.ai_education.result.VO.TeacherTaskVO;

import java.util.List;

public interface TaskService {
    // 学生端 根据学生id查找任务id,课程名字,任务状态,任务名字,截至时间
    List<TaskInfo> getTasksByStudentId(int studentId);

    // 学生端 根据学生id，课程id查找课程任务
    List<CourseTask> getTasksByStudentIdAndCourseId(int studentId, int courseId);

    // 学生端 根据任务id获取所有题目
    Result getByTaskId(int taskId);

    // 学生端 提交任务
    Result submitTask(List<TaskRecords> taskRecords);

    // 老师端 根据任务id获取所有学生的完成进度
    List<StuTaskInfoVO> showAllStuCourseTasks(Integer taskId);

    // 老师端 根据id删除对应的任务
    Integer deleteTask(Integer taskId);

    // 老师端 根据学生id和任务id获取学生对应任务下的所有题目
    Result getStudentAllTopic(Integer taskId, Integer stuId);

    // 老师端 创建任务
    Result creatTask(Task task);

    // 老师端 添加题目
    Result addTopics(TopicS topicS);

    // 老师端 获取所有任务
    List<TeacherTaskVO> getTasksByCourseId(int courseId);

    // 老师端 批改学生任务题目
    Result updateTopics(Integer taskId, Integer stuId, List<TaskRecords> taskRecords);

    // 学生端 返回已完成后的任务详情
    Result getTaskTopicDetail(int taskId);
}
