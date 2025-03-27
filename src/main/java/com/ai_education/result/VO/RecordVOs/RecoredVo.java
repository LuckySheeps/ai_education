package com.ai_education.result.VO.RecordVOs;

import com.ai_education.pojo.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecoredVo {
    Student student;
    Task task;
    Discuss discuss;
    Object records;
}
