package com.equipmentwork;

import dev.langchain4j.service.spring.AiServiceScannerProcessor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
public class EquipmentWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(EquipmentWorkApplication.class, args);
    }

}