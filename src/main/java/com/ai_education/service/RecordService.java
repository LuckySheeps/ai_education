package com.ai_education.service;


import com.ai_education.result.Result;

public interface RecordService {
    Result get();

    Result get(int i, String courseId);

    void addRecord(String currentId, Integer courseId);

    Result get2(int i, String courseId);
}
