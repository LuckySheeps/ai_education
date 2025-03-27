package com.ai_education.utils.xfyun.example.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import com.ai_education.utils.xfyun.example.dto.FileStatusData;
import com.ai_education.utils.xfyun.example.dto.FileStatusResp;
import com.ai_education.utils.xfyun.example.dto.UploadResp;
import com.ai_education.utils.xfyun.example.dto.chat.ChatMessage;
import com.ai_education.utils.xfyun.example.dto.chat.ChatRequest;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ChatDocUtil {
    private final OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build();

    public UploadResp upload(MultipartFile multipartFile, String url, String appId, String secret) throws IOException {
        // 将 MultipartFile 转换为 byte[]
        byte[] fileBytes = multipartFile.getBytes();
        RequestBody fileBody = RequestBody.create(fileBytes, MediaType.parse("multipart/form-data"));

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("file", multipartFile.getOriginalFilename(), fileBody);
        builder.addFormDataPart("fileType", "wiki");
        builder.addFormDataPart("parseType", "AUTO");
        RequestBody body = builder.build();

        long ts = System.currentTimeMillis() / 1000;
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("appId", appId)
                .addHeader("timestamp", String.valueOf(ts))
                .addHeader("signature", ApiAuthUtil.getSignature(appId, secret, ts))
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String respBody = response.body().string();
                UploadResp uploadResp = JSONUtil.toBean(respBody, UploadResp.class);

                /*// 进行向量化处理
                vectorizeFile(uploadResp.getData().getFileId(), appId, secret);
                // 轮询检查文件状态
                checkFileStatus(uploadResp.getData().getFileId(), appId, secret);*/

                // 异步处理向量化和状态检查
                CompletableFuture.runAsync(() -> {
                    try {
                        vectorizeFile(uploadResp.getData().getFileId(), appId, secret);
                        checkFileStatus(uploadResp.getData().getFileId(), appId, secret);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });


                return uploadResp;
            } else {
                throw new IOException("Unexpected code " + response);
            }
        }
    }

    public void vectorizeFile(String fileId, String appId, String secret) throws IOException {
        long ts = System.currentTimeMillis() / 1000;
        RequestBody body = new FormBody.Builder()
                .add("fileIds", fileId) // 注意这里是 fileIds，支持多个文件 ID
                .build();

        Request request = new Request.Builder()
                .url("https://chatdoc.xfyun.cn/openapi/v1/file/embedding")  // 向量化接口的URL
                .post(body)
                .addHeader("appId", appId)
                .addHeader("timestamp", String.valueOf(ts))
                .addHeader("signature", ApiAuthUtil.getSignature(appId, secret, ts))
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        }
    }

    public void checkFileStatus(String fileId, String appId, String secret) throws IOException {
        long ts = System.currentTimeMillis() / 1000;
        Request request = new Request.Builder()
                .url("https://chatdoc.xfyun.cn/openapi/v1/file/status?fileIds=" + fileId) // 检查文件状态的URL
                .get()
                .addHeader("appId", appId)
                .addHeader("timestamp", String.valueOf(ts))
                .addHeader("signature", ApiAuthUtil.getSignature(appId, secret, ts))
                .build();

        boolean isVectored = false;
        while (!isVectored) {
            try (Response response = okHttpClient.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    String respBody = response.body().string();
                    FileStatusResp fileStatusResp = JSONUtil.toBean(respBody, FileStatusResp.class);
                    for (FileStatusData data : fileStatusResp.getData()) {
                        if ("vectored".equals(data.getFileStatus())) {
                            isVectored = true;
                            break;
                        }
                    }
                    if (!isVectored) {
                        // 如果文件状态不是 vectored，等待一段时间再检查
                        Thread.sleep(2000); // 等待 5 秒
                    }
                } else {
                    throw new IOException("Unexpected code " + response);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException("Thread was interrupted", e);
            }
        }
    }

    public String chat(String chatUrl, String fileId, String question, String appId, String secret) {
        ChatMessage message = new ChatMessage();
        message.setRole("user");
        message.setContent(question);
        // 请求内容
        ChatRequest request = ChatRequest.builder()
                .fileIds(Collections.singletonList(fileId))
                .topN(3)
                .messages(Collections.singletonList(message))
                .build();

        // 构造url鉴权
        long ts = System.currentTimeMillis() / 1000;
        String signature = ApiAuthUtil.getSignature(appId, secret, ts);
        String requestUrl = chatUrl + "?" + "appId=" + appId + "&timestamp=" + ts + "&signature=" + signature;

        Request wsRequest = new Request.Builder().url(requestUrl).build();
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
        StringBuffer buffer = new StringBuffer();
        WebSocket webSocket = okHttpClient.newWebSocket(wsRequest, new WebSocketListener() {
            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosed(webSocket, code, reason);
                webSocket.close(1002, "websocket finish");
                okHttpClient.connectionPool().evictAll();
            }

            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
                super.onFailure(webSocket, t, response);
                webSocket.close(1001, "websocket finish");
                okHttpClient.connectionPool().evictAll();
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                System.out.println(text);
                JSONObject jsonObject = JSONUtil.parseObj(text);
                String content = jsonObject.getStr("content");
                if (StrUtil.isNotEmpty(content)) {
                    buffer.append(content);
                }
                if (Objects.equals(jsonObject.getInt("status"), 2)) {
                    System.out.println("回答内容：" + buffer);
                    webSocket.close(1000, "websocket finish");
                    okHttpClient.connectionPool().evictAll();
                }
            }
        });
        webSocket.send(JSONUtil.toJsonStr(request));

        // 等待 WebSocket 处理完毕并返回结果
        while (webSocket.send("ping")) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        return buffer.toString();
    }
}
