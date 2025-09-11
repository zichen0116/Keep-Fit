package com.equipmentwork.service;

public interface DeepSeekService {
    String getChatResponse(String userMessage);
    String getChatResponse(String userMessage, String model);
}
