package com.ai_education.result.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskInfo {
    private int taskId;
    private String courseName;
    private int status;
    private String taskName;
    private String deadline;
}
