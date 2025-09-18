package com.equipmentwork.controller;

import com.equipmentwork.service.ConsultantService;
import dev.langchain4j.model.openai.OpenAiChatModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

//
// @CrossOrigin
// @Tag(name="Deepseek",description="Ai相关接口")
// @RestController
// @RequestMapping("/api/ai")
// @Slf4j
// public class AiController {
//
//     @Autowired
//     private ConsultantService consultantService;
//
//
//     @RequestMapping(value = "/chat",produces = "text/html;charset=utf-8")
//     public Flux<String> chat(String memoryId ,String message) {
//         Flux<String> chatResult = consultantService.chat(memoryId,message);
//         return chatResult;
//     }
// }
