package com.equipmentwork.config;

import com.equipmentwork.repository.RedisChatMemoryStore;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CommonConfig {

    @Autowired
    private RedisChatMemoryStore redisChatMemoryStore;
    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        ChatMemoryProvider chatMemoryProvider = new ChatMemoryProvider() {
            @Override
            public ChatMemory get(Object memoryId){
                return MessageWindowChatMemory.builder()
                        .id(memoryId)
                        .chatMemoryStore(redisChatMemoryStore)
                        .maxMessages(20)
                        .build();
            }

         };
        return chatMemoryProvider;

    }
}
