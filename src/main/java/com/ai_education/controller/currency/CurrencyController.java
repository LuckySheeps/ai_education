package com.ai_education.controller.currency;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.ai_education.pojo.Currency;
import com.ai_education.result.Result;
import com.ai_education.service.Impl.CurrencyServiceImpl;
import com.ai_education.utils.currencyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/aiEducation/currency")
@CrossOrigin
public class CurrencyController {
    @Value("${xunfei.appid}")
    String APPID;
    // 接口密钥
    @Value(("${xunfei.currencyApikey}"))
    String API_KEY;
    @Autowired
    CurrencyServiceImpl currencyService;

    @PostMapping("/getmessage")
    Result get(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        String getmessage = currencyUtil.getmessage(multipartFile, APPID, API_KEY);
        JSONObject jsonObject = new JSONObject(getmessage);
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("fileList");
        String label = jsonArray.getJSONObject(0).getStr("label");
        System.out.println("label:" + label);
        JSONArray labels = jsonArray.getJSONObject(0).getJSONArray("labels");
//        for (Object o : labels) {
//            System.out.println(o.toString());
//        }
        ArrayList<Currency> getbyids = currencyService.getbyids(labels);
        System.out.println(getbyids);
        if (getbyids == null) {
            return Result.error("识别失败");
        }
        return Result.success("识别成功", getbyids);
    }
}
