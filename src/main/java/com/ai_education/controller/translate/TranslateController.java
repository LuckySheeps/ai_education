package com.ai_education.controller.translate;

import com.ai_education.result.Result;
import com.ai_education.result.VO.TranslateVO;
import com.ai_education.utils.translateUtil.MachineTranslationUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aiEducation/common/translate")
@CrossOrigin
public class TranslateController {
    @Autowired
    private MachineTranslationUtil machineTranslationUtil;
    @PostMapping()
    public Result translate(@RequestBody TranslateVO translateVO){
        try {
            JSONObject translateContent  = machineTranslationUtil.translate(translateVO.getFromLanguage(),
                    translateVO.getToLanguage(),
                    translateVO.getContent());
            System.out.println(translateContent);
            return Result.success("翻译成功：",translateContent);
        } catch (Exception e) {
            System.out.println(e);
            return Result.error("翻译失败："+e);
        }
    }
}
