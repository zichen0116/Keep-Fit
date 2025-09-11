package com.equipmentwork.service;

import com.equipmentwork.entity.Equipment;
import com.equipmentwork.entity.EquipmentQueryParam;
import com.equipmentwork.entity.PageResult;
// import com.equipmentwork.model.Equipment;

import java.util.List;

public interface EquipmentService {
    // 分页查询
    PageResult<Equipment> page(EquipmentQueryParam equipmentQueryParam);
    // 查询所有器材
    List<Equipment> queryEquipment();

    // 添加器材
    void insert(Equipment equipment);

    Equipment getById(Integer id);

    void update(Equipment equipment);

    void delete(Integer id);
}