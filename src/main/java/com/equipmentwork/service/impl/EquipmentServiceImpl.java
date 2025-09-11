package com.equipmentwork.service.impl;

import com.equipmentwork.dao.RepairMapper;
import com.equipmentwork.dao.ReservationMapper;
import com.equipmentwork.entity.Equipment;
import com.equipmentwork.entity.EquipmentQueryParam;
import com.equipmentwork.entity.PageResult;
import com.equipmentwork.entity.Reservation;
import com.equipmentwork.exception.EquipRepairException;
import com.equipmentwork.exception.EquipReservationException;
import com.equipmentwork.mapper.EquipmentMapper;
// import com.equipmentwork.model.Equipment;
import com.equipmentwork.service.EquipmentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private RepairMapper repairMapper;
    // 分页查询
    @Override
    public PageResult<Equipment> page(EquipmentQueryParam equipmentQueryParam) {
        PageHelper.startPage(equipmentQueryParam.getPage(),equipmentQueryParam.getPageSize());

        List<Equipment> equipmentList=equipmentMapper.list(equipmentQueryParam);

        Page<Equipment> p = (Page<Equipment>) equipmentList;

        return new PageResult<Equipment>(p.getTotal(),p.getResult());

    }

    @Override
    public List<Equipment> queryEquipment() {
        List<Equipment> clazzes = equipmentMapper.queryAllEquipment();
        return clazzes;
    }

    @Override
    public void insert(Equipment equipment) {


        equipmentMapper.insert(equipment);
    }

    @Override
    public Equipment getById(Integer id) {
        return equipmentMapper.getById(id);
    }

    @Override
    public void update(Equipment equipment) {
        equipmentMapper.update(equipment);
    }

    @Override
    public void delete(Integer id) {

        if (reservationMapper.countByEquipmentId(id) > 0) {
            throw new EquipReservationException("该设备存在借还记录，无法删除");
        }
        if (repairMapper.countByEquipmentId(id) > 0) {
            throw new EquipRepairException("该设备存在维修记录，无法删除");
        }
        equipmentMapper.deleteById(id);
    }



}