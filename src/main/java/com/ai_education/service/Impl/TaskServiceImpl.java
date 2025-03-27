package com.ai_education.service.Impl;

import com.ai_education.context.BaseContext;
import com.ai_education.mapper.*;
import com.ai_education.pojo.*;
import com.ai_education.result.DTO.CourseTask;
import com.ai_education.result.DTO.TaskInfo;
import com.ai_education.result.DTO.TopicS;
import com.ai_education.result.Result;
import com.ai_education.result.VO.StuTaskInfoVO;
import com.ai_education.result.VO.TaskTopicDetailVO;
import com.ai_education.result.VO.TeacherTaskVO;
import com.ai_education.service.TaskService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    TaskRecordsMapper taskRecordsMapper;
    @Autowired
    StudentCourseTasksMapper studentCourseTasksMapper;
    @Autowired
    private StudentMapper studentMapper;
    //根据学生id查找任务id,课程名字,任务状态,任务名字,截至时间
    @Override
    public List<TaskInfo> getTasksByStudentId(int studentId) {
        return taskMapper.findTasksByStudentId(studentId);
    }

    @Override
    public List<CourseTask> getTasksByStudentIdAndCourseId(int studentId, int courseId) {
        return taskMapper.getTasksByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public List<StuTaskInfoVO> showAllStuCourseTasks(Integer taskId) {
        List<StuTaskInfoVO> stuTaskInfoVOS = new ArrayList<>();
        // 根据taskId获取学生
        List<Integer> stuIds = studentCourseTasksMapper.selectByTaskId(taskId);
        if (stuIds.isEmpty()){
            return null;
        }
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("student_id", stuIds); // 根据 student_id 查询
        List<Student> students = studentMapper.selectList(queryWrapper);

        // 根据taskId获取所有题目分数
        for (Student student : students) {
            StuTaskInfoVO stuTaskInfoVO = new StuTaskInfoVO();
            stuTaskInfoVO.setStuId(student.getStudentId());
            stuTaskInfoVO.setStuName(student.getName());
            // 根据taskId和学生ID获取任务状态
            int status = 0;
            try {
                status = studentCourseTasksMapper.getStatus(taskId,student.getStudentId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            stuTaskInfoVO.setStatus(status);
            if (status == 1) {
                // 如果任务状态为已完成就显示分数
                Integer totalScore = taskRecordsMapper.getTotalScore(student.getStudentId(), taskId);
                stuTaskInfoVO.setScore(totalScore);
            }else {
                stuTaskInfoVO.setScore(-1);
            }
            stuTaskInfoVOS.add(stuTaskInfoVO);
        }
        return stuTaskInfoVOS;
    }

    @Override
    public Integer deleteTask(Integer taskId) {
        // 删除任务
        int i = taskMapper.deleteById(taskId);
        // 删除sct表中的任务 TODO
        // 删除
        return i;
    }

    @Override
    public Result getStudentAllTopic(Integer taskId, Integer stuId) {
        List<TaskTopicDetailVO> taskTopicDetailByStudentId = taskRecordsMapper.getTaskTopicDetailByStudentId(taskId, stuId);
        System.out.println(taskTopicDetailByStudentId);
        // 检查是否有数据，返回合适的响应
        if (taskTopicDetailByStudentId != null && !taskTopicDetailByStudentId.isEmpty()) {
            return Result.success("学生任务详情",taskTopicDetailByStudentId);  // 如果有结果，返回成功和题目列表
        } else {
            return Result.error("没有找到该任务的题目");  // 如果没有找到相关题目，返回失败信息
        }
    }

    @Override
    public Result getTaskTopicDetail(int taskId) {
        List<TaskTopicDetailVO> taskTopicDetailByStudentId = taskRecordsMapper.getTaskTopicDetailByStudentId(taskId, Integer.parseInt(BaseContext.getCurrentId()));
        System.out.println(taskTopicDetailByStudentId);
        // 检查是否有数据，返回合适的响应
        if (taskTopicDetailByStudentId != null && !taskTopicDetailByStudentId.isEmpty()) {
            return Result.success("任务详情",taskTopicDetailByStudentId);  // 如果有结果，返回成功和题目列表
        } else {
            return Result.error("没有找到该任务的题目");  // 如果没有找到相关题目，返回失败信息
        }
    }

    // 根据课程id。
    @Override
    public List<TeacherTaskVO> getTasksByCourseId(int courseId) {
        List<Task> tasksByCourseId = taskMapper.getTasksByCourseId(courseId);
        List<TeacherTaskVO> teacherTaskVOS = new ArrayList<>();
        System.out.println("前============="+teacherTaskVOS);
        for (Task task : tasksByCourseId) {
            TeacherTaskVO teacherTaskVO = new TeacherTaskVO();
            BeanUtils.copyProperties(task,teacherTaskVO);
            teacherTaskVOS.add(teacherTaskVO);
        }
        System.out.println("后============="+teacherTaskVOS);
        return teacherTaskVOS;
    }

    @Override
    public Result updateTopics(Integer taskId, Integer stuId, List<TaskRecords> taskRecords) {
        // 更新学生任务状态为已完成
        // 任务状态的改变
        Map<String, String> map = new HashMap<String, String>();
        map.put("student_id", String.valueOf(stuId));
        map.put("task_id", String.valueOf(taskId));
        int status = studentCourseTasksMapper.update(new UpdateWrapper<StudentCourseTasks>().allEq(map).set("status", 1));
        if(status==1)
        {
            System.out.println("批阅成功");
        }
        else {
            System.out.println("批阅失败");
        }

        // 更新学生答案的分数
        for (TaskRecords record : taskRecords) {
            // 使用 UpdateWrapper 更新每个学生任务记录的分数
            Map<String, Object> updateMap = new HashMap<>();
//            updateMap.put("get_score", record.getGetScore());
            updateMap.put("student_id", stuId);
            updateMap.put("task_id", taskId);
            updateMap.put("topic_id" , record.getTopicId());

            int updateStatus = taskRecordsMapper.update(new UpdateWrapper<TaskRecords>()
                    .allEq(updateMap).set("get_score", record.getGetScore()));  // 更新分数

            if (updateStatus != 0) {
                System.out.println("学生 " + stuId + " 任务 " + taskId + " 的分数更新成功");
            } else {
                System.out.println("学生 " + stuId + " 任务 " + taskId + " 的分数更新失败");
            }
        }

        return Result.success("批阅成功");

    }





    @Override
    public Result creatTask(Task task) {

        //根据课程id查找所有学生id
        List<StudentCourse> studentCourses = studentCourseMapper.selectList(new QueryWrapper<StudentCourse>().eq("course_id", task.getCourseId()));

        try {
            taskMapper.insert(task);

            //将学生的课程任务初始化
            for (StudentCourse studentCours : studentCourses) {
                StudentCourseTasks studentCourseTasks = new StudentCourseTasks();
                studentCourseTasks.setStudentId(studentCours.getStudentId());
                studentCourseTasks.setTaskId(task.getTaskId());
                studentCourseTasks.setCourseId(task.getCourseId());
                studentCourseTasksMapper.insert(studentCourseTasks);
            }

            return Result.success("创建成功",task);

        }
        catch (Exception e){
            return Result.success("创建失败,请检查日期格式",task);
        }

    }

    @Override
    public Result addTopics(TopicS topicS) {
        List<Topic> topics = topicS.getTopics();
        System.out.println(topics);
        try
        {
            for (Topic topic : topics) {
                topic.setTaskId(topicS.getTaskId());
                topicMapper.insert(topic);
            }
            return Result.success("成功");
        }catch (Exception e){
            return Result.error("失败");
        }

    }
    //根据任务id查任务的所有题目，一般学生做题时调用
    @Override
    public Result getByTaskId(int taskId) {
        Task task = taskMapper.selectById(taskId);
        try {
            List<Topic> topics = topicMapper.selectList(new QueryWrapper<Topic>().eq("task_id", taskId));
            return Result.success("成功",topics);
        }catch (Exception e){
            return Result.error("失败");
        }
    }

    // 保存提交的任务
    /*@Override
    public Result submitTask(List<TaskRecords> taskRecords ) {
        System.out.println(taskRecords);

        // 将所写题目进行存入
        try {
            for (TaskRecords taskRecord : taskRecords) {
                taskRecord.setStudentId(Long.parseLong(BaseContext.getCurrentId()));
                taskRecordsMapper.insert(taskRecord);
                //     System.out.println(taskRecord);
            }

            // 任务状态的改变
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("student_id", Integer.parseInt(BaseContext.getCurrentId()));
            map.put("task_id",taskRecords.get(0).getTaskId().toString());
            int status = studentCourseTasksMapper.update(new UpdateWrapper<StudentCourseTasks>().allEq(map).set("status", 2));
            if(status == 1)
            {
                System.out.println("任务提交成功");
            }
            else {
                System.out.println("任务提交失败");

            }
            return Result.success("提交成功");

        }catch (Exception e){
            //
            for (TaskRecords taskRecord : taskRecords) {
                taskRecord.setStudentId(Long.parseLong(BaseContext.getCurrentId()));
                Map<String, String> map = new HashMap<String, String>();
                map.put("student_id", BaseContext.getCurrentId());
                map.put("task_id",taskRecords.get(1).getTaskId().toString());
                map.put("topic_id",taskRecord.getTopicId().toString());
                int update = taskRecordsMapper.update(new UpdateWrapper<TaskRecords>().allEq(map).set("answer",taskRecord.getAnswer()));
                System.out.println(update);
                // System.out.println(taskRecord);
            }

            return Result.success("再次提交");
        }
*/

    @Override
    public Result submitTask(List<TaskRecords> taskRecords) {
        System.out.println(taskRecords);

        Boolean flag = true;

        // 将所写题目进行存入
        try {
            // 目前只打算做一次所以只需要插入即可
            for (TaskRecords taskRecord : taskRecords) {
                taskRecord.setStudentId(Long.parseLong(BaseContext.getCurrentId()));
                taskRecordsMapper.insert(taskRecord);
                if (taskRecord.getQuestionType().equals("应用题")){
                    flag = false;
                }
            }

            // 任务状态的改变
            Map<String, String> map = new HashMap<>();
            map.put("student_id", BaseContext.getCurrentId());
            map.put("task_id", taskRecords.get(0).getTaskId().toString());

            // 打印map，确保值正确
            System.out.println("更新 的 map: " + map);

            // 使用 eq 代替 allEq 以确保条件准确
            UpdateWrapper<StudentCourseTasks> updateWrapper = new UpdateWrapper<>();
            if (flag){
                updateWrapper.eq("student_id", BaseContext.getCurrentId())
                        .eq("task_id", taskRecords.get(0).getTaskId().toString())
                        .set("status", 1);
            }else {
                updateWrapper.eq("student_id", BaseContext.getCurrentId())
                        .eq("task_id", taskRecords.get(0).getTaskId().toString())
                        .set("status", 2);
            }

            int status = studentCourseTasksMapper.update(null, updateWrapper);

            // 打印status，检查更新是否成功
            if (status == 1) {
                System.out.println("任务提交成功");
                return Result.success("提交成功");
            } else {
                System.out.println("任务提交失败，status=" + status);
                return Result.error("任务提交失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("提交失败，请稍后重试");
        }
    }


}

