package com.ai_education.mapper;


import com.ai_education.pojo.StudentCourseTasks;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StudentCourseTasksMapper extends BaseMapper<StudentCourseTasks> {
    @Select("select * from student_course_tasks where student_id = #{student_id} and course_id=#{course_id}")
    List<StudentCourseTasks> selectBystudentId(@Param("student_id") int student_id, @Param("course_id") String course_id);
    @Select("select student_id from student_course_tasks where task_id=#{task_id}")
    List<Integer> selectByTaskId(int task_id);
    @Select("select status from student_course_tasks where task_id=#{taskId} AND student_id = #{stuId}")
    int getStatus(@Param("taskId") Integer taskId, @Param("stuId") Integer stuId );
}
