package com.equipmentwork.controller;

import com.equipmentwork.service.DeepSeekService;
import com.equipmentwork.utils.DeepSeekCommonResult;
import dev.langchain4j.model.openai.OpenAiChatModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * DeepSeek AI 服务的控制器，提供与DeepSeek模型交互的API接口
 * 该控制器处理HTTP请求，调用DeepSeekService执行具体业务逻辑，并返回处理结果
 */
@CrossOrigin
@Tag(name="Deepseek",description="Deepseek相关接口")
@RestController
@RequestMapping("/api/ai")
@Slf4j
public class DeepSeekController {

     @Autowired
    private final DeepSeekService deepSeekService;
    @Autowired
    public DeepSeekController(DeepSeekService deepSeekService) {
        this.deepSeekService = deepSeekService;
    }
    @Operation(summary="Deepseek调用")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "接入成功"),
            @ApiResponse(responseCode = "500",description = "接入失败")
    })
    @PostMapping("/chat")
    // http://localhost:8089/EquipmentWork/api/ai/chat
    public ResponseEntity<DeepSeekCommonResult<String>> chat(@RequestBody Map<String, String> request) {
        String userMessage = request.get("message");
        String model = request.get("model");

        try {
            String response = (model != null)
                    ? deepSeekService.getChatResponse(userMessage, model)
                    : deepSeekService.getChatResponse(userMessage);

            return ResponseEntity.ok(DeepSeekCommonResult.success(response));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(DeepSeekCommonResult.error(500, "AI服务调用失败: " + e.getMessage()));
        }
    }




}