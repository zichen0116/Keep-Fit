package com.equipmentwork.dao;

import com.equipmentwork.entity.Repair;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RepairMapper {
    public int addRepair(Repair repair);//增加
    public int updateRepair(Repair repair);//更新
    public int deleteRepair(long id);//删除

    public List<Repair> getAllRepair();//得到所有
    public List<Repair> getRepairByPage(int page, int pageSize);//按页查询

    public int countByEquipmentId(Integer id);//ID查

}
