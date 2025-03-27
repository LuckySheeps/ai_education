package com.ai_education.service.Impl;

import com.ai_education.context.BaseContext;
import com.ai_education.mapper.CommentMapper;
import com.ai_education.mapper.DiscussionMapper;
import com.ai_education.mapper.StudentMapper;
import com.ai_education.mapper.TeacherMapper;
import com.ai_education.pojo.Comment;
import com.ai_education.pojo.Discussions;
import com.ai_education.pojo.Student;
import com.ai_education.pojo.Teacher;
import com.ai_education.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private DiscussionMapper discussionMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public boolean addComment(int courseId, int id, String title, String content) {
        // 查找课程对应的讨论
        Discussions discussion = discussionMapper.selectOne(new QueryWrapper<Discussions>().eq("course_id", courseId));
        if (discussion == null) {
            // 如果课程没有讨论板块，则创建一个
            discussion = new Discussions();
            discussion.setCourseId(courseId);
            discussionMapper.insert(discussion);
        }
        System.out.println(discussion.getDiscussionId());

        // 创建评论
        Comment comment = new Comment();
        comment.setDiscussionId(discussion.getDiscussionId());
        comment.setUserId(id);
        comment.setUserType(Integer.parseInt(BaseContext.getCurrentRole()));
        comment.setCommentTitle(title);
        comment.setCommentContent(content);
        // 获取当前日期
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        comment.setCommentTime(currentDate);

        // 插入评论到数据库
        return commentMapper.insert(comment) > 0;
    }

    @Override
    public List<Map<String, Object>> getCommentsByCourseId(int courseId) {
        // 查询课程对应的讨论
        Discussions discussion = discussionMapper.selectOne(new QueryWrapper<Discussions>().eq("course_id", courseId));
        if (discussion == null) {
            return List.of(); // 如果没有讨论返回空列表
        }

        // 查询讨论对应的所有评论
        List<Comment> comments = commentMapper.selectList(new QueryWrapper<Comment>().eq("discussion_id", discussion.getDiscussionId()));

        // 查询所有评论对应的用户信息
        Map<String, Object> userMap = new HashMap<>();
        for (Comment comment : comments) {
            String key = comment.getUserType() + "_" + comment.getUserId(); // 构建唯一键
            if (comment.getUserType() == 0) {
                Student student = studentMapper.selectById(comment.getUserId());
                userMap.put(key, student);
            } else if (comment.getUserType() == 1) {
                Teacher teacher = teacherMapper.selectById(comment.getUserId());
                userMap.put(key, teacher);
            }
        }

        // 组装返回结果
        return comments.stream().map(comment -> {
            Map<String, Object> commentMap = new HashMap<>();
            String key = comment.getUserType() + "_" + comment.getUserId();
            Object user = userMap.get(key);
            if (user instanceof Student) {
                Student student = (Student) user;
                commentMap.put("image", student.getStudentImage());
                commentMap.put("name", student.getName());
            } else if (user instanceof Teacher) {
                Teacher teacher = (Teacher) user;
                commentMap.put("image", teacher.getTeacherImage());
                commentMap.put("name", teacher.getName());
            }
            commentMap.put("commentTime", comment.getCommentTime());
            commentMap.put("title", comment.getCommentTitle());
            commentMap.put("content", comment.getCommentContent());
            return commentMap;
        }).collect(Collectors.toList());
    }

    public List<Map<String, Object>> getUserCommentsByCourseId(int courseId, int userId, int userType) {
        // 查询课程对应的讨论
        Discussions discussion = discussionMapper.selectOne(new QueryWrapper<Discussions>().eq("course_id", courseId));
        if (discussion == null) {
            return List.of(); // 如果没有讨论返回空列表
        }

        // 查询讨论对应的所有评论
        List<Comment> comments = commentMapper.selectList(new QueryWrapper<Comment>()
                .eq("discussion_id", discussion.getDiscussionId())
                .eq("user_id", userId)
                .eq("user_type", userType));

        // 查询用户信息
        Map<String, Object> userMap = new HashMap<>();
        if (userType == 0) {
            Student student = studentMapper.selectById(userId);
            userMap.put("image", student.getStudentImage());
            userMap.put("name", student.getName());
        } else if (userType == 1) {
            Teacher teacher = teacherMapper.selectById(userId);
            userMap.put("image", teacher.getTeacherImage());
            userMap.put("name", teacher.getName());
        }

        // 组装返回结果
        return comments.stream().map(comment -> {
            Map<String, Object> commentMap = new HashMap<>(userMap);
            commentMap.put("commentTime", comment.getCommentTime());
            commentMap.put("title", comment.getCommentTitle());
            commentMap.put("content", comment.getCommentContent());
            return commentMap;
        }).collect(Collectors.toList());
    }

}
