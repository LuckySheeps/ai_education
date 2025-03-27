package com.ai_education.result.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseInfo {
    private int courseId;
    private String invitationCode;
    private String courseName;
    private String courseImage;
    private String teacherName;
}
