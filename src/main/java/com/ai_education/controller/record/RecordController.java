package com.ai_education.controller.record;

import com.ai_education.context.BaseContext;
import com.ai_education.result.Result;
import com.ai_education.service.Impl.RecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aiEducation/record")
@CrossOrigin
public class RecordController {
    @Autowired
    RecordServiceImpl recordService;
    @GetMapping("get")
      public Result get(@RequestParam("courseid")String courseId){
        System.out.println(courseId);
        System.out.println(Integer.parseInt(BaseContext.getCurrentId()));
        Result result = recordService.get(Integer.parseInt(BaseContext.getCurrentId()),courseId);

        return result;
    }
}
