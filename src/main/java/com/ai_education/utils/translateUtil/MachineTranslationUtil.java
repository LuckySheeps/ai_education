package com.ai_education.utils.translateUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MachineTranslationUtil {

    private static final String requestUrl = "https://itrans.xf-yun.com/v1/its";
    private  String appId;
    private  String apiSecret;
    private  String apiKey;
    private static final Gson gson = new Gson();
    private static final String RES_ID = "its_en_cn_word";

    public JSONObject translate(String from, String to, String text) throws Exception {
        String response = doRequest(from, to, text);
        JsonParse jsonParse = gson.fromJson(response, JsonParse.class);
        String textBase64Decode = new String(Base64.getDecoder().decode(jsonParse.payload.result.text), StandardCharsets.UTF_8);
        JSONObject jsonObject = JSON.parseObject(textBase64Decode);
        return jsonObject;
    }

    private String doRequest(String from, String to, String text) throws Exception {
        URL realUrl = new URL(buildRequestUrl());
        URLConnection connection = realUrl.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-type", "application/json");

        OutputStream out = httpURLConnection.getOutputStream();
        String params = buildParam(from, to, text);
        out.write(params.getBytes(StandardCharsets.UTF_8));
        out.flush();

        InputStream is;
        try {
            is = httpURLConnection.getInputStream();
        } catch (Exception e) {
            is = httpURLConnection.getErrorStream();
            throw new Exception("make request error: code is " + httpURLConnection.getResponseMessage() + readAllBytes(is));
        }
        return readAllBytes(is);
    }

    private String buildRequestUrl() {
        try {
            URL url = new URL(requestUrl);
            SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            format.setTimeZone(TimeZone.getTimeZone("GMT"));
            String date = format.format(new Date());
            String host = url.getHost();
            StringBuilder builder = new StringBuilder("host: ").append(host).append("\n")
                    .append("date: ").append(date).append("\n")
                    .append("POST ").append(url.getPath()).append(" HTTP/1.1");
            Charset charset = StandardCharsets.UTF_8;
            Mac mac = Mac.getInstance("hmacsha256");
            SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(charset), "hmacsha256");
            mac.init(spec);
            byte[] hexDigits = mac.doFinal(builder.toString().getBytes(charset));
            String sha = Base64.getEncoder().encodeToString(hexDigits);
            String authorization = String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"",
                    apiKey, "hmac-sha256", "host date request-line", sha);
            String authBase = Base64.getEncoder().encodeToString(authorization.getBytes(charset));
            return String.format("%s?authorization=%s&host=%s&date=%s",
                    requestUrl, URLEncoder.encode(authBase, charset), URLEncoder.encode(host, charset), URLEncoder.encode(date, charset));
        } catch (Exception e) {
            throw new RuntimeException("assemble requestUrl error: " + e.getMessage());
        }
    }

    private String buildParam(String from, String to, String text) {
        return "{" +
                "    \"header\": {" +
                "        \"app_id\": \"" + appId + "\"," +
                "        \"status\": 3," +
                "        \"res_id\": \"its_en_cn_word\"" +
                "    }," +
                "    \"parameter\": {" +
                "        \"its\": {" +
                "            \"from\": \"" + from + "\"," +
                "            \"to\": \"" + to + "\"," +
                "            \"result\": {}" +
                "        }" +
                "    }," +
                "    \"payload\": {" +
                "        \"input_data\": {" +
                "            \"encoding\": \"utf8\"," +
                "            \"status\": 3," +
                "            \"text\": \"" + Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8)) + "\"" +
                "        }" +
                "    }" +
                "}";
    }

    private String readAllBytes(InputStream is) throws IOException {
        byte[] buffer = new byte[1024];
        StringBuilder sb = new StringBuilder();
        int len;
        while ((len = is.read(buffer)) != -1) {
            sb.append(new String(buffer, 0, len, StandardCharsets.UTF_8));
        }
        return sb.toString();
    }

    private static class JsonParse {
        Payload payload;

        static class Payload {
            Result result;
        }

        static class Result {
            String text;
        }
    }
}
