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
        chatModel = "openAiChatModel",
        streamingChatModel = "openAiStreamingChatModel",
        chatMemoryProvider = "chatMemoryProvider"
)
public interface ConsultantService {
    @SystemMessage(fromResource = "system.txt")
    public Flux<String> chat(@MemoryId String memberId, @UserMessage String message);
}
