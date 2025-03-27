package com.ai_education.mapper;

import com.ai_education.pojo.StudyRecords;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface StudentRecordMapper extends BaseMapper<StudyRecords> {
    @Select("select * from study_records where student_id = #{id} and course_id=#{courseId} and time >DATE_SUB(CURDATE(), INTERVAL 7 DAY) order by time")
    List<StudyRecords> selectBystudentId(int id, String courseId);

    @Select("SELECT * FROM study_records \n" +
            "WHERE \n" +
            "student_id = #{sid}\n" +
            "and \n" +
            "course_id = #{courseId}\n" +
            "and \n" +
            "time BETWEEN DATE_SUB(NOW(), INTERVAL 7 DAY) AND NOW();\n")
    List<StudyRecords> selectBySidAndCid(@Param("sid") int sid, @Param("courseId") String courseId);
}
