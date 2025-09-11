package com.equipmentwork.dao;

import com.equipmentwork.entity.Maintenance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MaintenanceMapper {
    public int addMaintenance(Maintenance maintenance);//增加
    public int updateMaintenance(Maintenance maintenance);//更新
    public int deleteMaintenance(long id);//删

    public List<Maintenance> getAllMaintenance();//得到所有
    public List<Maintenance> getMaintenanceByPage(int page, int pageSize);//按页查询


}
