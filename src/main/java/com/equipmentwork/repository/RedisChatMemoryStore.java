package com.equipmentwork.repository;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.List;

@Repository
public class RedisChatMemoryStore implements ChatMemoryStore {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        String json = stringRedisTemplate.opsForValue().get(memoryId.toString());
        // 反序列化
        return ChatMessageDeserializer.messagesFromJson(json);
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> list) {
        // list转成json
        String json = ChatMessageSerializer.messagesToJson(list);
        // 存入redis
        stringRedisTemplate.opsForValue().set(memoryId.toString(), json, Duration.ofDays(1));
    }

    @Override
    public void deleteMessages(Object memoryId) {
        stringRedisTemplate.delete(memoryId.toString());
    }
}
