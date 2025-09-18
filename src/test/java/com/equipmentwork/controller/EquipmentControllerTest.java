package com.equipmentwork.controller;

import com.equipmentwork.entity.Equipment;
import com.equipmentwork.entity.EquipmentQueryParam;
import com.equipmentwork.entity.PageResult;
import com.equipmentwork.service.EquipmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EquipmentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EquipmentService equipmentService;

    @InjectMocks
    private EquipmentController equipmentController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(equipmentController).build();
        objectMapper = new ObjectMapper();
    }

    // 测试用例1: 测试分页查询器材功能
    @Test
    void testPageQuery() throws Exception {
        // 准备测试数据
        EquipmentQueryParam param = new EquipmentQueryParam();
        param.setPage(1);
        param.setPageSize(10);
        param.setName("跑步机");
        
        List<Equipment> equipmentList = Arrays.asList(
            new Equipment(1, "跑步机", "舒华", "SH-T3109", "A区", "正常"),
            new Equipment(2, "动感单车", "舒华", "SH-B5180", "A区", "正常")
        );
        
        PageResult<Equipment> pageResult = new PageResult<>(2L, equipmentList);
        
        // 模拟服务层返回
        when(equipmentService.page(any(EquipmentQueryParam.class))).thenReturn(pageResult);
        
        // 执行测试
        mockMvc.perform(get("/equipments")
                .param("page", "1")
                .param("pageSize", "10")
                .param("name", "跑步机"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.data.total").value(2));
        
        // 验证服务层方法被调用
        verify(equipmentService, times(1)).page(any(EquipmentQueryParam.class));
    }

    // 测试用例2: 测试查询所有器材功能
    @Test
    void testListAllEquipment() throws Exception {
        // 准备测试数据
        List<Equipment> equipmentList = Arrays.asList(
            new Equipment(1, "跑步机", "舒华", "SH-T3109", "A区", "正常"),
            new Equipment(2, "动感单车", "舒华", "SH-B5180", "A区", "正常"),
            new Equipment(3, "椭圆机", "舒华", "SH-E500", "B区", "维修中")
        );
        
        // 模拟服务层返回
        when(equipmentService.queryEquipment()).thenReturn(equipmentList);
        
        // 执行测试
        mockMvc.perform(get("/equipments/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.data.length()").value(3));
        
        // 验证服务层方法被调用
        verify(equipmentService, times(1)).queryEquipment();
    }

    // 测试用例3: 测试添加器材功能
    @Test
    void testSaveEquipment() throws Exception {
        // 准备测试数据
        Equipment equipment = new Equipment();
        equipment.setName("划船机");
        equipment.setBrand("舒华");
        equipment.setSize("SH-R500");
        equipment.setArea("C区");
        equipment.setStatus("正常");
        
        String equipmentJson = objectMapper.writeValueAsString(equipment);
        
        // 模拟服务层行为
        doNothing().when(equipmentService).insert(any(Equipment.class));
        
        // 执行测试
        mockMvc.perform(post("/equipments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(equipmentJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"));
        
        // 验证服务层方法被调用
        verify(equipmentService, times(1)).insert(any(Equipment.class));
    }

    // 测试用例4: 测试根据ID获取器材功能
    @Test
    void testGetEquipmentById() throws Exception {
        // 准备测试数据
        Equipment equipment = new Equipment(1, "跑步机", "舒华", "SH-T3109", "A区", "正常");
        
        // 模拟服务层返回
        when(equipmentService.getById(eq(1))).thenReturn(equipment);
        
        // 执行测试
        mockMvc.perform(get("/equipments/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("跑步机"));
        
        // 验证服务层方法被调用
        verify(equipmentService, times(1)).getById(eq(1));
    }

    // 测试用例5: 测试删除器材功能
    @Test
    void testDeleteEquipment() throws Exception {
        // 模拟服务层行为
        doNothing().when(equipmentService).delete(eq(1));
        
        // 执行测试
        mockMvc.perform(delete("/equipments/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"));
        
        // 验证服务层方法被调用
        verify(equipmentService, times(1)).delete(eq(1));
    }
}