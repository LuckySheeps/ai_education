package com.ai_education.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("comment")
public class Comment {
    @TableId(value = "comment_id",type = IdType.AUTO)
    private int commentId;
    @TableField("discussion_id")
    private int discussionId;
    @TableField("user_id")
    private int userId;
    @TableField("user_type")
    private int userType;
    @TableField("comment_title")
    private String commentTitle;
    @TableField("comment_content")
    private String commentContent;
    @TableField("comment_time")
    private String commentTime;
}
