package com.equipmentwork.service;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;


@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,// 手动装配
        chatModel = "openAiChatModel", // 配置模型
        streamingChatModel = "openAiStreamingChatModel",// 配置流式模型
        chatMemoryProvider = "chatMemoryProvider",// 配置会话记忆对象
        contentRetriever = "contentRetriever", //配置向量数据库检索对象
        tools = "reservationTool"
)
public interface ConsultantService {
    @SystemMessage(fromResource = "system.txt")
    public Flux<String> chat(@MemoryId String memberId, @UserMessage String message);
}
