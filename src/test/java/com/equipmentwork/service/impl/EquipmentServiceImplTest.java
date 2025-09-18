package com.equipmentwork.service.impl;

import com.equipmentwork.dao.RepairMapper;
import com.equipmentwork.dao.ReservationMapper;
import com.equipmentwork.entity.Equipment;
import com.equipmentwork.entity.EquipmentQueryParam;
import com.equipmentwork.entity.PageResult;
import com.equipmentwork.exception.EquipRepairException;
import com.equipmentwork.exception.EquipReservationException;
import com.equipmentwork.mapper.EquipmentMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EquipmentServiceImplTest {

    @Mock
    private EquipmentMapper equipmentMapper;
    
    @Mock
    private ReservationMapper reservationMapper;
    
    @Mock
    private RepairMapper repairMapper;
    
    @InjectMocks
    private EquipmentServiceImpl equipmentService;
    
    private Equipment testEquipment;
    private EquipmentQueryParam queryParam;

    @BeforeEach
    void setUp() {
        testEquipment = new Equipment();
        testEquipment.setId(1);
        testEquipment.setName("测试设备");
        testEquipment.setBrand("测试品牌");
        testEquipment.setSize("中型");
        testEquipment.setArea("A区");
        testEquipment.setStatus("可用");

        queryParam = new EquipmentQueryParam();
        queryParam.setPage(1);
        queryParam.setPageSize(10);
    }

    /**
     * 测试用例1: 测试分页查询功能
     * 测试EquipmentServiceImpl.page()方法
     * 涉及复杂调用关系：PageHelper.startPage() + equipmentMapper.list() + Page类型转换
     */
    // @Test
    // void testPageQuery() {
    //     // 准备测试数据
    //     List<Equipment> equipmentList = Arrays.asList(testEquipment);
    //     Page<Equipment> page = new Page<>();
    //     page.addAll(equipmentList);
    //     page.setTotal(1);
    //
    //     // 模拟PageHelper和Mapper行为
    //     try (MockedStatic<PageHelper> pageHelperMock = mockStatic(PageHelper.class)) {
    //         pageHelperMock.when(() -> PageHelper.startPage(anyInt(), anyInt()))
    //                       .thenReturn(null);
    //
    //         when(equipmentMapper.list(any(EquipmentQueryParam.class)))
    //             .thenReturn(page);
    //
    //         // 执行测试方法
    //         PageResult<Equipment> result = equipmentService.page(queryParam);
    //
    //         // 验证结果
    //         assertNotNull(result);
    //         assertEquals(1, result.getTotal());
    //         assertEquals(1, result.getRecords().size());
    //         assertEquals("测试设备", result.getRecords().get(0).getName());
    //
    //         // 验证调用次数和参数
    //         pageHelperMock.verify(() -> PageHelper.startPage(1, 10), times(1));
    //         verify(equipmentMapper, times(1)).list(queryParam);
    //     }
    // }

    /**
     * 测试用例2: 测试删除设备成功的场景
     * 测试EquipmentServiceImpl.delete()方法
     * 涉及复杂调用关系：reservationMapper.countByEquipmentId() + repairMapper.countByEquipmentId() + equipmentMapper.deleteById()
     */
    @Test
    void testDeleteEquipmentSuccess() {
        // 模拟没有预约和维修记录的情况
        when(reservationMapper.countByEquipmentId(1)).thenReturn(0);
        when(repairMapper.countByEquipmentId(1)).thenReturn(0);

        // 执行删除操作
        assertDoesNotThrow(() -> equipmentService.delete(1));

        // 验证调用顺序和次数
        verify(reservationMapper, times(1)).countByEquipmentId(1);
        verify(repairMapper, times(1)).countByEquipmentId(1);
        verify(equipmentMapper, times(1)).deleteById(1);
    }

    /**
     * 测试用例3: 测试删除设备失败的场景（存在预约记录）
     * 测试EquipmentServiceImpl.delete()方法的异常处理
     * 涉及复杂调用关系：reservationMapper.countByEquipmentId() + 异常抛出机制
     */
    @Test
    void testDeleteEquipmentFailWithReservation() {
        // 模拟存在预约记录的情况
        when(reservationMapper.countByEquipmentId(1)).thenReturn(2);

        // 验证抛出正确的异常
        EquipReservationException exception = assertThrows(
            EquipReservationException.class, 
            () -> equipmentService.delete(1)
        );
        
        assertEquals("该设备存在借还记录，无法删除", exception.getMessage());

        // 验证调用情况：应该检查预约记录，但不应该检查维修记录或执行删除
        verify(reservationMapper, times(1)).countByEquipmentId(1);
        verify(repairMapper, never()).countByEquipmentId(anyInt());
        verify(equipmentMapper, never()).deleteById(anyInt());
    }

    /**
     * 测试用例4: 测试删除设备失败的场景（存在维修记录）
     * 测试EquipmentServiceImpl.delete()方法的另一种异常处理
     * 涉及复杂调用关系：reservationMapper.countByEquipmentId() + repairMapper.countByEquipmentId() + 异常抛出机制
     */
    @Test
    void testDeleteEquipmentFailWithRepair() {
        // 模拟没有预约记录但存在维修记录的情况
        when(reservationMapper.countByEquipmentId(1)).thenReturn(0);
        when(repairMapper.countByEquipmentId(1)).thenReturn(1);

        // 验证抛出正确的异常
        EquipRepairException exception = assertThrows(
            EquipRepairException.class, 
            () -> equipmentService.delete(1)
        );
        
        assertEquals("该设备存在维修记录，无法删除", exception.getMessage());

        // 验证调用情况：应该检查预约和维修记录，但不应该执行删除
        verify(reservationMapper, times(1)).countByEquipmentId(1);
        verify(repairMapper, times(1)).countByEquipmentId(1);
        verify(equipmentMapper, never()).deleteById(anyInt());
    }
}