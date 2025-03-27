package com.ai_education.controller.image;

import com.ai_education.result.Result;
import com.ai_education.utils.imageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aiEducation/image/")
@CrossOrigin
public class imageController {
    @Value("${xunfei.appid}")
   String appid;
    @Value("${xunfei.imageapiSecret}")
  String apiSecret;
    @Value("${xunfei.imageapiKey}")
   String apiKey;

    @PostMapping("/wordtoimg")
    public Result wordToiamge(@RequestParam("word")String word){
        System.out.println("image__生成");
        try {
            return Result.success("成功",imageUtil.getimage(word,appid,apiSecret,apiKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
