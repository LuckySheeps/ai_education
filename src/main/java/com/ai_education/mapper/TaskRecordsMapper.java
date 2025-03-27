package com.ai_education.mapper;

import com.ai_education.pojo.TaskRecords;
import com.ai_education.result.VO.TaskTopicDetailVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface TaskRecordsMapper extends BaseMapper<TaskRecords> {
    // 根据学生id和任务id获取分数之和
    @Select("SELECT SUM(get_score) FROM task_records WHERE student_id = #{studentId} AND task_id = #{taskId}")
    Integer getTotalScore(@Param("studentId") int studentId, @Param("taskId") int taskId);

    @Select("SELECT * FROM task_records WHERE student_id = #{studentId} AND task_id = #{taskId}")
    List<TaskRecords> getTaskRecordsBySIdAndTId(@Param("studentId") int studentId, @Param("taskId") int taskId);

    // 查询指定任务和学生的所有题目和任务记录
    @Select("SELECT tr.topic_id, tr.task_id, tr.answer, tr.right_answer, tr.result, tr.question_type, tr.ai_content, tr.get_score, " +
            "t.question_text, t.options1, t.options2, t.options3, t.options4, t.created_at, t.fullscore " +
            "FROM task_records tr " +
            "LEFT JOIN topic t ON tr.topic_id = t.id " +
            "WHERE tr.task_id = #{taskId} AND tr.student_id = #{studentId}")
    List<TaskTopicDetailVO> getTaskTopicDetailByStudentId(@Param("taskId") int taskId, @Param("studentId") int studentId);

}
