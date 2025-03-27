package com.ai_education;

import com.ai_education.service.GptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiEducationApplicationTests {

    @Test
    void contextLoads() {
    }
@Autowired
    GptService gptService;
    @Test
   void gpttest(){
        new Thread(()->{

            System.out.println("000"+gptService.getanswer("1+1=?"));
        });
new Thread(()->{

    System.out.println("111"+gptService.getanswer("你好"));
});
        System.out.println("222"+gptService.getanswer("你是谁"));

    }
}
