package com.ai_education.mapper;

import com.ai_education.pojo.Discussions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DiscussionMapper extends BaseMapper<Discussions> {

}

