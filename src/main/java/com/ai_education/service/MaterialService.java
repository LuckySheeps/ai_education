package com.ai_education.service;

import com.ai_education.pojo.Material;

import java.util.List;

public interface MaterialService {
    List<Material> getMaterialsByCourseId(int courseId);

    boolean saveMaterial(Material material);

    boolean deleteMaterial(int courseId, String materialId);

    void save(Material material);
}
