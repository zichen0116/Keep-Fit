package com.equipmentwork.utils;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                // 接口文档基本信息（标题、版本、联系人等，属于Info对象）
                .info(new Info()
                        .title("健身器材管理系统文档")
                        .description("接口说明文档")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("xgl"))) // 联系人信息嵌套在Info中

                // 外部文档配置（属于OpenAPI对象）
                .externalDocs(new ExternalDocumentation()
                        .url("http://140.143.206.51:8096/EquipmentWork/doc.html"));
    }
}
