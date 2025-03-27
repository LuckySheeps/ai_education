package com.ai_education.utils;

import com.ai_education.utils.Pptutils.ApiAuthAlgorithm;
import com.ai_education.utils.Pptutils.ApiClient;
import com.ai_education.utils.Pptutils.CreateResponse;
import com.ai_education.utils.Pptutils.ProgressResponse;
import com.alibaba.fastjson.JSON;

import java.io.IOException;

public class pptUtils {


    public static String getTempLateList(String appId, String secret) throws IOException {
        long timestamp = System.currentTimeMillis()/1000;
        ApiClient client = new ApiClient("https://zwapi.xfyun.cn");
        ApiAuthAlgorithm auth = new ApiAuthAlgorithm();
        String ts = String.valueOf(timestamp);
        String signature = auth.getSignature(appId, secret, timestamp);
        String templateResult = client.getTemplateList(appId, ts, signature);
     return templateResult;

    }
    public static ProgressResponse getPPt(String appId, String secret,String theme,String desc) throws IOException, InterruptedException {
        // 输入个人appId

        long timestamp = System.currentTimeMillis()/1000;
        String ts = String.valueOf(timestamp);
        // 获得鉴权信息
        ApiAuthAlgorithm auth = new ApiAuthAlgorithm();
        String signature = auth.getSignature(appId, secret, timestamp);
        System.out.println(signature);

        // 建立链接
        ApiClient client = new ApiClient("https://zwapi.xfyun.cn");

        // 查询PPT模板信息
//        String templateResult = client.getTemplateList(appId, ts, signature);
//        System.out.println(templateResult);

        // 发送生成PPT请求
        String query =desc;
        String resp = client.createPPT(appId, ts, signature,query,theme);
        System.out.println(resp);
        CreateResponse response = JSON.parseObject(resp, CreateResponse.class);

        // 利用sid查询PPT生成进度
        int progress = 0;
        ProgressResponse progressResponse = null;
        while (progress < 100) {
            String progressResult = client.checkProgress(appId, ts, signature, response.getData().getSid());
            progressResponse = JSON.parseObject(progressResult, ProgressResponse.class);
            progress = progressResponse.getData().getProcess();
            System.out.println(progressResult);
            if (progress < 100) {
                Thread.sleep(5000); // 暂停2秒
            }
        }
return progressResponse;
        // 大纲生成
//        String outlineQuery = "这是一个大纲生成的测试";
//        String outlineResp = client.createOutline(appId, ts, signature,outlineQuery);
//        System.out.println(outlineResp);
//        CreateResponse outlineResponse = JSON.parseObject(outlineResp, CreateResponse.class);
//        System.out.println("生成的大纲如下：");
//        System.out.println(outlineResponse.getData().getOutline());
//
//        // 基于sid和大纲生成ppt
//        String sidResp = client.createPptBySid(appId, ts, signature, outlineResponse.getData().getSid());
//        System.out.println(sidResp);
//        CreateResponse sidResponse = JSON.parseObject(sidResp, CreateResponse.class);
//        sidResp = client.createPptBySid(appId, ts, signature, outlineResponse.getData().getSid());
//        System.out.println(sidResp);
//        sidResponse = JSON.parseObject(sidResp, CreateResponse.class);
//        // 利用sid查询PPT生成进度
//        progress = 0;
//        while (progress < 100) {
//            String progressResult = client.checkProgress(appId, ts, signature, sidResponse.getData().getSid());
//            progressResponse = JSON.parseObject(progressResult, ProgressResponse.class);
//            progress = progressResponse.getData().getProcess();
//            System.out.println(progressResult);
//
//            if (progress < 100) {
//                Thread.sleep(5000); // 暂停2秒
//            }
//        }
//
//        // 基于大纲生成ppt
//        String pptResp = client.createPptByOutline(appId, ts, signature, outlineQuery, outlineResponse.getData().getOutline());
//        System.out.println(pptResp);
//        CreateResponse pptResponse = JSON.parseObject(pptResp, CreateResponse.class);
//        // 利用sid查询PPT生成进度
//        progress = 0;
//        while (progress < 100) {
//            String progressResult = client.checkProgress(appId, ts, signature, pptResponse.getData().getSid());
//            progressResponse = JSON.parseObject(progressResult, ProgressResponse.class);
//            progress = progressResponse.getData().getProcess();
//            System.out.println(progressResult);
//
//            if (progress < 100) {
//                Thread.sleep(5000); // 暂停2秒
//            }
//        }
    }
}