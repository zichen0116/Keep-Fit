package com.equipmentwork.dto.deepseekrequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * DeepSeek AI模型的请求数据传输对象
 * 用于封装调用DeepSeek API所需的参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeepSeekRequest {
     private String model = "deepseek-chat";
    // private String model = "Qwen/QwQ-32B";
    private List<Message> messages;
    private boolean stream = false;
    private double temperature = 0.7;
    private int max_tokens = 2000;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {
        private String role;
        private String content;
    }
}
