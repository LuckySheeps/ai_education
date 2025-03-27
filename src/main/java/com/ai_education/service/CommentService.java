package com.ai_education.service;

import java.util.List;
import java.util.Map;

public interface CommentService {
    boolean addComment(int courseId, int id, String title, String content);

    List<Map<String, Object>> getCommentsByCourseId(int courseId);

    List<Map<String, Object>> getUserCommentsByCourseId(int courseId, int userId, int userType);
}
