package com.ai_education.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import lombok.NoArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
@NoArgsConstructor
@Component
public class BigModelNew extends WebSocketListener {
    public static final String hostUrl = "https://spark-api.xf-yun.com/v3.5/chat";
    public static final String appid = "c94df294";
    public static final String apiSecret = "MTUyODEzZDQ1YTExZjZlMjg2MmM2YTI2";
    public static final String apiKey = "e6a371543b9f9482eaf7fe8be606eb78";

    public static List<RoleContent> historyList = new ArrayList<>();
    public static final Gson gson = new Gson();
    private static Boolean totalFlag = true;
    private static StringBuffer totalAnswer;
    private String userId;
    private Boolean wsCloseFlag;
    private CompletableFuture<String> futureResult;

    public BigModelNew(String userId, Boolean wsCloseFlag, CompletableFuture<String> futureResult) {
        this.userId = userId;
        this.wsCloseFlag = wsCloseFlag;
        this.futureResult = futureResult;
    }

    public static String getAuthUrl(String hostUrl, String apiKey, String apiSecret) throws Exception {
        URL url = new URL(hostUrl);
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = format.format(new Date());
        String preStr = "host: " + url.getHost() + "\n" +
                "date: " + date + "\n" +
                "GET " + url.getPath() + " HTTP/1.1";

        Mac mac = Mac.getInstance("hmacsha256");
        SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "hmacsha256");
        mac.init(spec);
        byte[] hexDigits = mac.doFinal(preStr.getBytes(StandardCharsets.UTF_8));
        String sha = Base64.getEncoder().encodeToString(hexDigits);
        String authorization = String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"",
                apiKey, "hmac-sha256", "host date request-line", sha);

        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse("https://" + url.getHost() + url.getPath())).newBuilder()
                .addQueryParameter("authorization", Base64.getEncoder().encodeToString(authorization.getBytes(StandardCharsets.UTF_8)))
                .addQueryParameter("date", date)
                .addQueryParameter("host", url.getHost())
                .build();

        return httpUrl.toString();
    }

    public CompletableFuture<String> performTaskAsync(String question) {
        CompletableFuture<String> future = new CompletableFuture<>();
        try {
            String authUrl = getAuthUrl(hostUrl, apiKey, apiSecret);
            OkHttpClient client = new OkHttpClient.Builder().build();
            String url = authUrl.replace("http://", "ws://").replace("https://", "wss://");
            Request request = new Request.Builder().url(url).build();
            WebSocket webSocket = client.newWebSocket(request, new BigModelNew(userId, wsCloseFlag, future));
            totalAnswer= new StringBuffer();
            // 启动线程来发送请求
            new Thread(() -> {
                try {
                    JSONObject requestJson = new JSONObject();

                    JSONObject header = new JSONObject();
                    header.put("app_id", appid);
                    header.put("uid", UUID.randomUUID().toString().substring(0, 10));

                    JSONObject parameter = new JSONObject();
                    JSONObject chat = new JSONObject();
                    chat.put("domain", "generalv3");
                    chat.put("temperature", 0.5);
                    chat.put("max_tokens", 4096);
                    parameter.put("chat", chat);



                    JSONObject payload = new JSONObject();
                    JSONObject message = new JSONObject();
                    JSONArray text = new JSONArray();

                    // 添加历史问题
                    for (RoleContent tempRoleContent : historyList) {
                        text.add(JSON.toJSON(tempRoleContent));
                    }

                    // 添加新问题
                    RoleContent roleContent = new RoleContent();
                    roleContent.role = "user";
                    roleContent.content = question;
                    text.add(JSON.toJSON(roleContent));
                    historyList.add(roleContent);

                    message.put("text", text);
                    payload.put("message", message);

                    requestJson.put("header", header);
                    requestJson.put("parameter", parameter);
                    requestJson.put("payload", payload);

                    webSocket.send(requestJson.toString());
                } catch (Exception e) {
                    future.completeExceptionally(e);
                }
            }).start();

        } catch (Exception e) {
            future.completeExceptionally(e);
        }
        return future;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        System.out.print("大模型：");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        JsonParse myJsonParse = gson.fromJson(text, JsonParse.class);
        if (myJsonParse.header.code != 0) {
            futureResult.completeExceptionally(new IOException("Error code: " + myJsonParse.header.code));
            webSocket.close(1000, "");
            return;
        }


        for (Text temp : myJsonParse.payload.choices.text) {

         // System.out.print(temp.content);
            totalAnswer.append(temp.content);
         // System.out.println(totalAnswer);
        }

        if (myJsonParse.header.status == 2) {
            System.out.println();
            System.out.println("*************************************************************************************");
            if (canAddHistory()) {
                RoleContent roleContent = new RoleContent();
                roleContent.setRole("assistant");
                roleContent.setContent(totalAnswer.toString());
                historyList.add(roleContent);
            } else {
                historyList.remove(0);
                RoleContent roleContent = new RoleContent();
                roleContent.setRole("assistant");
                roleContent.setContent(totalAnswer.toString());
                historyList.add(roleContent);
            }
            wsCloseFlag = true;
            totalFlag = true;
            futureResult.complete(totalAnswer.toString());
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        try {
            if (response != null) {
                int code = response.code();
                System.out.println("onFailure code:" + code);
                System.out.println("onFailure body:" + response.body().string());
                if (code != 101) {
                    futureResult.completeExceptionally(new IOException("Connection failed"));
                }
            } else {
                futureResult.completeExceptionally(t);
            }
        } catch (IOException e) {
            futureResult.completeExceptionally(e);
        }
    }

    public static boolean canAddHistory() {
        int history_length = 0;
        for (RoleContent temp : historyList) {
            history_length += temp.content.length();
        }
        if (history_length > 12000) {
            for (int i = 0; i < 5; i++) {
                historyList.remove(0);
            }
            return false;
        } else {
            return true;
        }
    }

    class JsonParse {
        Header header;
        Payload payload;
    }

    class Header {
        int code;
        int status;
        String sid;
    }

    class Payload {
        Choices choices;
    }

    class Choices {
        List<Text> text;
    }

    class Text {
        String role;
        String content;
    }

    class RoleContent {
        String role;
        String content;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
