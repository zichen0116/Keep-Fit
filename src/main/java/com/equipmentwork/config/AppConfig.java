package com.equipmentwork.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
/**
 * 应用基础配置类，负责创建和配置应用运行所需的通用Bean
 */
@Configuration
public class AppConfig {

    /** 从配置文件读取的连接超时时间，单位为毫秒 */
    @Value("${http.client.connect-timeout}")
    private int connectTimeout;
    /** 从配置文件读取的读取超时时间，单位为毫秒 */
    @Value("${http.client.read-timeout}")
    private int readTimeout;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

// 使用RestTemplateBuilder创建RestTemplate实例，并配置连接超时和读取超时
        // 连接超时：客户端尝试与服务器建立连接的最大等待时间
        // 读取超时：客户端从服务器读取响应的最大等待时间
        return builder
                .setConnectTimeout(Duration.ofMillis(connectTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }
}