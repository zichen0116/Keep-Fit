package com.equipmentwork.dto.deepseekresponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * DeepSeek AI模型的响应数据传输对象
 * 用于封装DeepSeek API返回的结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeepSeekResponse {
    private List<Choice> choices;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice {
        private Message message;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Message {
            private String content;
        }

    }

}
