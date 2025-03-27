package com.ai_education.controller.ppt;

import com.ai_education.result.Result;
import com.ai_education.utils.pptUtils;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/aiEducation/ppt")
@CrossOrigin
public class PptController {

    @Value("${xunfei.appid}")
    String appid;
    @Value("${xunfei.pptapiSecret}")
    String pptapiSecret;
    @GetMapping("/getTempList")
    Result gettempList() throws IOException {

        return Result.success("成功", JSON.parseObject(pptUtils.getTempLateList(appid,pptapiSecret)));
    }


    @PostMapping("/getPpt")
    Result getPPt(@RequestParam("theme")String theme,@RequestParam("desc")String desc) throws IOException, InterruptedException {

        return Result.success("成功",pptUtils.getPPt(appid,pptapiSecret,theme,desc));

    }

}
