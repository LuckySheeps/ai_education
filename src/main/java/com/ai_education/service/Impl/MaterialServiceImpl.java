package com.ai_education.service.Impl;

import com.ai_education.context.BaseContext;
import com.ai_education.mapper.MaterialMapper;
import com.ai_education.pojo.Material;
import com.ai_education.service.MaterialService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public List<Material> getMaterialsByCourseId(int courseId) {
        return materialMapper.findByCourseId(courseId);
    }

    @Override
    public boolean saveMaterial(Material material) {
        material.setTeacherId(Integer.parseInt(BaseContext.getCurrentId()));
        //生成创建时间
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String createDate = now.format(formatter);
        material.setCreateDate(createDate);

        return materialMapper.insert(material) > 0;
    }

    @Override
    public boolean deleteMaterial(int courseId, String materialId) {
        // 通过课程，老师，资料id删除
        QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId)
                .eq("material_id", materialId)
                .eq("teacher_id",BaseContext.getCurrentId());

        int deletedRows = materialMapper.delete(queryWrapper);
        return deletedRows > 0;
    }

    @Override
    public void save(Material material) {
        materialMapper.insert(material);
    }
}
