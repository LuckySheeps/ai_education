package com.ai_education.controller.discussion;

import com.ai_education.context.BaseContext;
import com.ai_education.pojo.Discussions;
import com.ai_education.result.Result;
import com.ai_education.result.VO.DiscussionVO;
import com.ai_education.service.CommentService;
import com.ai_education.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/aiEducation/discussions")
@CrossOrigin
public class DiscussionController {

    @Autowired
    private CommentService commentService;

    // 添加评论
    @PostMapping("/{courseId}/add")
    public Result addComment(@PathVariable("courseId") int courseId, @RequestBody DiscussionVO discussionVO) {

        boolean isAdded = commentService.addComment(courseId, Integer.parseInt(BaseContext.getCurrentId()), discussionVO.getTitle(), discussionVO.getContent());
        if (isAdded) {
            return Result.success("评论添加成功");
        } else {
            return Result.error("评论添加失败");
        }
    }

    // 查询所有评论
    @GetMapping("/{courseId}/byCourse")
    public Result getCommentsByCourseId(@PathVariable("courseId") int courseId) {
        List<Map<String, Object>> comments = commentService.getCommentsByCourseId(courseId);
        return Result.success("课程评论", comments);
    }

    // 查询课程下的个人评论
    @GetMapping("/course/{courseId}")
    public Result getUserCommentsByCourseId(@PathVariable int courseId) {
        int userId = Integer.parseInt(BaseContext.getCurrentId()); // 获取当前用户ID
        int userType = Integer.parseInt(BaseContext.getCurrentRole()); // 获取当前用户类型 (0: student, 1: teacher)

        List<Map<String, Object>> comments = commentService.getUserCommentsByCourseId(courseId, userId, userType);
        return Result.success("查询成功", comments);
    }
}
