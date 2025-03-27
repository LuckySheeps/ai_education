package com.ai_education.controller.gpt;

import com.ai_education.result.Result;
import com.ai_education.service.GptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aiEducation/gpt/")
@CrossOrigin
public class gptController {



@Autowired
    GptService gptService;
    @GetMapping("/ask")
    public Result useWebSocketTool(@RequestParam ("question") String question) {
      return Result.success("成功",gptService.getanswer(question));
    }
}
