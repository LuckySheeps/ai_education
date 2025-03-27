package com.ai_education.mapper;

import com.ai_education.pojo.Task;
import com.ai_education.result.DTO.CourseTask;
import com.ai_education.result.DTO.TaskInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TaskMapper extends BaseMapper<Task> {
    //根据学生id查找任务id,课程名字,任务状态,任务名字,截至时间
    @Select("SELECT t.task_id AS taskId, \n" +
            "c.course_name AS courseName, \n" +
            "sct.status AS status, \n" +
            "t.task_name AS taskName, \n" +
            "t.deadline AS deadline \n" +
            "FROM student_course_tasks sct \n" +
            "JOIN task t ON sct.task_id = t.task_id \n" +
            "JOIN course c ON t.course_id = c.course_id \n" +
            "WHERE sct.student_id = #{studentId}")
    List<TaskInfo> findTasksByStudentId(@Param("studentId") int studentId);

    //根据学生id，课程id查找课程任务
    @Select("SELECT t.task_id, \n" +
            "t.course_id, \n" +
            "t.task_name, \n" +
            "t.task_content, \n" +
            "t.deadline, \n" +
            "st.status\n" +
            "FROM task t\n" +
            "JOIN student_course_tasks st ON t.task_id = st.task_id\n" +
            "WHERE st.student_id = #{studentId} AND t.course_id = #{courseId}")
    List<CourseTask> getTasksByStudentIdAndCourseId(@Param("studentId") int studentId, @Param("courseId") int courseId);

    //根据学生id，课程id查找课程任务
    @Select("select * from task where course_id = #{courseId}")
    List<Task> getTasksByCourseId(@Param("courseId")int courseId);
}
