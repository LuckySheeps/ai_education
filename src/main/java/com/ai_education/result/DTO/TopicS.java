package com.ai_education.result.DTO;

import com.ai_education.pojo.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicS {
    int taskId;
    List<Topic> topics;
}
