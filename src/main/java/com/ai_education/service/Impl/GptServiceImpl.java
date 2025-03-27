package com.ai_education.service.Impl;

import com.ai_education.service.GptService;
import com.ai_education.utils.BigModelNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
@Service
public class GptServiceImpl implements GptService {
    @Autowired
    BigModelNew bigModelNew;
    @Override
    public String getanswer(String question) {
        try {
            CompletableFuture<String> futureResult = bigModelNew.performTaskAsync(question);
            String s = futureResult.get();
            System.out.println(s);
            return s; // 阻塞直到任务完成并返回结果
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred";
        }
    }
}
