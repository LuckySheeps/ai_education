package com.ai_education.service.Impl;

import com.ai_education.mapper.*;
import com.ai_education.pojo.*;
import com.ai_education.result.Result;
import com.ai_education.result.VO.RecordVOs.Discuss;
import com.ai_education.result.VO.RecordVOs.RecoredVo;
import com.ai_education.result.VO.RecordVOs.Task;
import com.ai_education.service.RecordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    StudentCourseTasksMapper studentCourseTasksMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    StudentRecordMapper studentRecordMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    DiscussionMapper discussionMapper;

    @Override
    public Result get() {
        //获得当前学生信息


        return null;
    }

    @Override
    public Result get(int sid, String courseId) {
        //获得当前学生信息

        RecoredVo recoredVo = new RecoredVo();
        //  Student student = studentMapper.selectById(BaseContext.getCurrentId());
        Student student = studentMapper.selectById(sid);
        recoredVo.setStudent(student);

        //获取当前学生的课程任务总数和完成数
        //List<StudentCourseTasks> studentCourseTasks = studentCourseTasksMapper.selectBystudentId(BaseContext.getCurrentId());
        List<StudentCourseTasks> studentCourseTasks = studentCourseTasksMapper.selectBystudentId(sid, courseId);
        int count = 0;
        for (StudentCourseTasks studentCourseTask : studentCourseTasks) {
            if (studentCourseTask.getStatus() != 0) count++;
        }
        Task task = new Task(studentCourseTasks.size(), count);
        recoredVo.setTask(task);

//获得讨论信息
        Discussions discussions = discussionMapper.selectOne(new QueryWrapper<Discussions>().eq("course_id", courseId));
        System.out.println(discussions);
        if (discussions == null) {
            recoredVo.setDiscuss(new Discuss(0, 0));
        } else {
            List<Comment> comments = commentMapper.selectList(new QueryWrapper<Comment>().eq("discussion_id", discussions.getDiscussionId()));
            System.out.println(comments);
            int count1 = 0;
            for (Comment comment : comments) {
                if (comment.getUserId() == sid) count1++;
            }
            recoredVo.setDiscuss(new Discuss(comments.size(), count1));
        }

        //获取学习记录信息
        // List<StudentRecordMapper> studentRecordMappers = studentRecordMapper.selectBystudentId(BaseContext.getCurrentId());
        List<StudyRecords> studyRecords = studentRecordMapper.selectBystudentId(sid, courseId);

        recoredVo.setRecords(studyRecords);

        System.out.println("成功");
        return Result.success("yes", recoredVo);
    }

    @Override
    public void addRecord(String sid, Integer courseId) {
        // 创建一个当前时间的LocalDateTime对象
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 定义时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 将LocalDateTime对象格式化为字符串
        String formattedDateTime = currentDateTime.format(formatter);
        // 输出格式化后的时间字符串
        System.out.println(formattedDateTime);
        StudyRecords studyRecords = new StudyRecords();
        studyRecords.setCourseId(courseId);
        studyRecords.setStudentId(Integer.parseInt(sid));
        studyRecords.setTime(formattedDateTime);
        studentRecordMapper.insert(studyRecords);
    }

    @Override
    public Result get2(int sid, String courseId) {
        List<StudyRecords> records = studentRecordMapper.selectBySidAndCid(sid, courseId);
        return Result.success("七天学习记录", records);
    }
}
