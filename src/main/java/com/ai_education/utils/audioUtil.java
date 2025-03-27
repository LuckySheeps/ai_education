package com.ai_education.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ai_education.utils.wordtoaudioUtils.sign.LfasrSignature;
import com.ai_education.utils.wordtoaudioUtils.utils.HttpUtil;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URISyntaxException;
import java.security.SignatureException;
import java.util.HashMap;


public class audioUtil {
    private static final String HOST = "https://raasr.xfyun.cn";
    private static String AUDIO_FILE_PATH;
//    private static final String appid = "c94df294";
//    private static final String keySecret = "4e642bbdb30aafefa1ee43d248387f70";

    private static final Gson gson = new Gson();

    static {
        try {
            AUDIO_FILE_PATH = audioUtil.class.getResource("/").toURI().getPath() + "/audio/合成音频.wav";
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public static String getsting(MultipartFile audiofile, String appid, String keySecret) throws Exception {
        String result = upload(audiofile,appid,keySecret);
        String jsonStr = StringEscapeUtils.unescapeJavaScript(result);
        String orderId = String.valueOf(JSONUtil.getByPath(JSONUtil.parse(jsonStr), "content.orderId"));
        String result1 = getResult(orderId,appid,keySecret);
        return result1;
    }

    @SneakyThrows
    private static String upload(MultipartFile audioFile, String appid, String keySecret) throws SignatureException, FileNotFoundException {
        HashMap<String, Object> map = new HashMap<>(16);
        File audio = new File(AUDIO_FILE_PATH);
        String fileName = audio.getName();
//        long fileSize = audio.length();
        long fileSize = audioFile.getSize();
        map.put("appId", appid);
        map.put("fileSize", fileSize);
        map.put("fileName", audioFile.getOriginalFilename());
        map.put("duration", "200");
        LfasrSignature lfasrSignature = new LfasrSignature(appid, keySecret);
        map.put("signa", lfasrSignature.getSigna());
        map.put("ts", lfasrSignature.getTs());

        String paramString = HttpUtil.parseMapToPathParam(map);
        System.out.println("upload paramString:" + paramString);

        String url = HOST + "/v2/api/upload" + "?" + paramString;
        System.out.println("upload_url:" + url);
        String response = HttpUtil.iflyrecUpload(url, audioFile.getInputStream());

        System.out.println("upload response:" + response);
        return response;
    }

    private static String getResult(String orderId, String appid,String keySecret) throws SignatureException, InterruptedException, IOException {
        HashMap<String, Object> map = new HashMap<>(16);
        map.put("orderId", orderId);
        LfasrSignature lfasrSignature = new LfasrSignature(appid, keySecret);
        map.put("signa", lfasrSignature.getSigna());
        map.put("ts", lfasrSignature.getTs());
        map.put("appId", appid);
        map.put("resultType", "transfer,predict");
        String paramString = HttpUtil.parseMapToPathParam(map);
        String url = HOST + "/v2/api/getResult" + "?" + paramString;
        System.out.println("\nget_result_url:" + url);
        while (true) {
            String response = HttpUtil.iflyrecGet(url);
            JsonParse jsonParse = gson.fromJson(response, JsonParse.class);
            if (jsonParse.content.orderInfo.status == 4 || jsonParse.content.orderInfo.status == -1) {
                //System.out.println("订单完成:" + response);
                write(response);
                String c="";
                JSONObject jsonObject = new JSONObject(response);
                String s = jsonObject.getJSONObject("content").getJSONObject("orderResult").toString();
                JSONObject jsonObject1 = new JSONObject(s);
                JSONArray lattice2 = jsonObject1.getJSONArray("lattice2");
                for (Object o : lattice2) {
                    JSONArray jsonArray = ((JSONObject) o).getJSONObject("json_1best").getJSONObject("st").getJSONArray("rt");
                    for (Object o1 : jsonArray) {
                        JSONArray ws = ((JSONObject) o1).getJSONArray("ws");
                        for (Object w : ws) {
                            JSONArray cws = ((JSONObject) w).getJSONArray("cw");
                            for (Object cw : cws) {
                               c+= ( (JSONObject)cw).getStr("w");
                            }
                        }
                    }
                }
                System.out.println(c);

                return c;
            } else {
                System.out.println("进行中...，状态为:" + jsonParse.content.orderInfo.status);
                //建议使用回调的方式查询结果，查询接口有请求频率限制

                Thread.sleep(7000);
            }
        }
    }

    public static void write(String resp) throws IOException {
        //将写入转化为流的形式
        BufferedWriter bw = new BufferedWriter(new FileWriter("src\\main\\resources\\output\\test.txt"));
        String ss = resp;
        bw.write(ss);
        //关闭流
        bw.close();
        System.out.println("写入txt成功");
    }

    class JsonParse {
        Content content;
    }

    class Content {
        OrderInfo orderInfo;
    }

    class OrderInfo {
        Integer status;
    }
}
