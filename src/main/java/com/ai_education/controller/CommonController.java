package com.ai_education.controller;

import com.ai_education.context.BaseContext;
import com.ai_education.pojo.Material;
import com.ai_education.result.Result;
import com.ai_education.service.MaterialService;
import com.ai_education.utils.AliOssUtil;
import com.ai_education.utils.xfyun.example.dto.UploadResp;
import com.ai_education.utils.xfyun.example.util.ChatDocUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//通用接口
@RestController
@RequestMapping("/aiEducation/common")
@CrossOrigin
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;
    @Autowired
    private MaterialService materialService;

    //图片上传
    @PostMapping("/upload")
    public Result upload(MultipartFile file){
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newName = UUID.randomUUID().toString() +extension;
            String filePath = aliOssUtil.upload(file.getBytes(),newName);
            return  Result.success("上传成功，图片地址",filePath);
        } catch (IOException e) {
            System.out.println("文件上传失败："+e);
        }
        return null;
    }


    @PostMapping("/teacher/{courseId}/fileUpload")
    public Result teacherFileUpload(@PathVariable("courseId") int courseId, @RequestParam("file") MultipartFile file) {
        return handleFileUpload(courseId, Integer.parseInt(BaseContext.getCurrentId()), file);
    }

    @PostMapping("/student/{courseId}/fileUpload")
    public Result studentFileUpload(@PathVariable("courseId") int courseId, @RequestParam("file") MultipartFile file) {
        return handleFileUpload(courseId, Integer.parseInt(BaseContext.getCurrentId()), file);
    }

    /**
     * 处理文件上传请求
     *
     * @param courseId,id,file 上传的文件
     * @return 包含上传结果的Result对象
     */
    private Result handleFileUpload(int courseId, int id, MultipartFile file) {
        try {
            // 获取文件的原始名称
            String originalFilename = file.getOriginalFilename();

            // 获取文件扩展名
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            // 生成新的文件名，防止重名
            String newName = UUID.randomUUID().toString() + extension;

            // 获取文件大小（字节）
            long fileSize = file.getSize();

            // 将文件上传到阿里云OSS，并获取文件的访问路径
            String filePath = aliOssUtil.upload(file.getBytes(), newName);

            // 获取当前日期
            String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            // 创建 Material 对象并设置属性
            Material material = new Material();
            material.setCourseId(courseId); // 设置课程ID
            material.setTeacherId(id); // 设置ID
            material.setMaterialName(originalFilename); // 设置原始文件名
/*
            material.setMaterialType(extension); // 设置文件类型
*/
            // 将文件大小转换为MB
            String fileSizeInMB = formatFileSize(fileSize);
            material.setMaterialType(String.valueOf(fileSizeInMB)); // 设置文件大小
            material.setMaterialContent(filePath); // 设置文件访问路径
            material.setCreateDate(currentDate); // 设置创建日期

            // TODO 可以的对学生还是老师存进行判断
            // 这里调用服务类将 Material 对象保存到数据库，暂且只有老师
            materialService.save(material);

            // 创建响应数据
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("material", material); // 包含 Material 对象

            // 返回上传成功的结果
            return Result.success("上传成功，资料", responseData);
        } catch (IOException e) {
                    System.out.println("文件上传失败：" + e);
            // 返回上传失败的结果
            return Result.error("文件上传失败");
        }
    }

    // 将文件大小格式化为合适的单位
    private String formatFileSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}
