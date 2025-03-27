package com.ai_education.controller.audio;


import com.ai_education.result.Result;
import com.ai_education.utils.audioUtil;
import com.ai_education.utils.imageUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/aiEducation/audio/")
@CrossOrigin
public class audioController {
    @Value("${xunfei.appid}")
    String appid;
    @Value("${xunfei.audiokeySecret}")
    String keySecret;
    @SneakyThrows
    @PostMapping("/get")
    public Result get(@RequestParam("audio") MultipartFile audio){
        System.out.println("yes");
        System.out.println(appid+keySecret);
      return    Result.success("成功", audioUtil.getsting(audio,appid,keySecret)) ;
    }

}
