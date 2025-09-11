package com.equipmentwork.service.impl;

import com.equipmentwork.dto.deepseekrequest.DeepSeekRequest;
import com.equipmentwork.dto.deepseekresponse.DeepSeekResponse;
import com.equipmentwork.service.DeepSeekService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class DeepSeekServiceimpl implements DeepSeekService {
    private final String apiUrl;
    private final String apiKey;
    private final String defaultModel;
    private final RestTemplate restTemplate;

    public DeepSeekServiceimpl(
            @Value("${deepseek.api.url}") String apiUrl,
            @Value("${deepseek.api.key}") String apiKey,
            @Value("${deepseek.api.model}") String defaultModel,
            RestTemplate restTemplate) {

        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
        this.defaultModel = defaultModel;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getChatResponse(String userMessage) {
        return getChatResponse(userMessage, defaultModel);
    }

    @Override
    public String getChatResponse(String userMessage, String model) {
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        // 创建消息体
        DeepSeekRequest.Message message = new DeepSeekRequest.Message("user", userMessage);
        DeepSeekRequest request = new DeepSeekRequest();
        request.setModel(model);
        request.setMessages(Collections.singletonList(message));

        // 发送请求
        HttpEntity<DeepSeekRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<DeepSeekResponse> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                entity,
                DeepSeekResponse.class
        );

        // 处理响应
        if (response.getStatusCode().is2xxSuccessful() &&
                response.getBody() != null &&
                !response.getBody().getChoices().isEmpty()) {

            return response.getBody().getChoices().get(0).getMessage().getContent();
        } else {
            throw new RuntimeException("DeepSeek API调用失败: " + response.getStatusCode());
        }
    }


}
