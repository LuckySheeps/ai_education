package com.ai_education.controller.wordGpt;

import com.ai_education.result.Result;
import com.ai_education.utils.xfyun.example.dto.UploadResp;
import com.ai_education.utils.xfyun.example.util.ChatDocUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/aiEducation/wordGpt")
@CrossOrigin
public class WordGptController {
    private static final String uploadUrl = "https://chatdoc.xfyun.cn/openapi/v1/file/upload";
    private static final String chatUrl = "wss://chatdoc.xfyun.cn/openapi/chat";
    private static final String appId = "b0dcf49b";
    private static final String secret = "NDA3OTAyNWFlNGExZGU3NDRjZmE5OTk5";

    /*// 存储文件 ID 的内存数据库
    private final ConcurrentHashMap<String, String> fileStore = new ConcurrentHashMap<>();
*/
    @PostMapping("/upload")
    public Result uploadFile(@RequestParam("file") MultipartFile file) {
        ChatDocUtil chatDocUtil = new ChatDocUtil();
        UploadResp uploadResp;
        try {
            uploadResp = chatDocUtil.upload(file, uploadUrl, appId, secret);
            String fileId = uploadResp.getData().getFileId();

            /*// 将文件 ID 存储到内存中
            fileStore.put("lastUploadedFileId", fileId);*/

            return Result.success("文件上传成功，文件ID：" , fileId);
        } catch (IOException e) {
            return Result.error("文件上传失败：" + e.getMessage());
        }
    }

    @PostMapping("/ask")
    public Result askQuestion(@RequestParam("fileId")String fileId, @RequestParam("question") String question) {
        ChatDocUtil chatDocUtil = new ChatDocUtil();
//        String fileId = fileStore.get("lastUploadedFileId");

        if (fileId == null) {
            return Result.error("没有已上传的文件，请先上传文件。");
        }

        try {
            String response = chatDocUtil.chat(chatUrl, fileId, question, appId, secret);
            return Result.success("回答内容",response);
        } catch (Exception e) {
            return Result.error("问答失败：" + e.getMessage());
        }
    }
}
