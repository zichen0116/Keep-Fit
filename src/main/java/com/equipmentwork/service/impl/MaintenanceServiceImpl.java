package com.equipmentwork.service.impl;

import com.equipmentwork.dao.MaintenanceMapper;
import com.equipmentwork.entity.Maintenance;
import com.equipmentwork.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    @Autowired
    private MaintenanceMapper maintenanceMapper;

    @Override
    public boolean insert(Maintenance maintenance){
        return maintenanceMapper.addMaintenance(maintenance)>0;
    }

    @Override
    public boolean delete(long id){
        return maintenanceMapper.deleteMaintenance(id)>0;
    }

    @Override
    public boolean update(Maintenance maintenance){
        return maintenanceMapper.updateMaintenance(maintenance)>0;
    }

    @Override
    public List<Maintenance> getAll(){
        return maintenanceMapper.getAllMaintenance();
    }

    @Override
    public Map<String,Object> getByPage(int page, int pageSize){
        int start = (page-1)*pageSize;
        Map<String,Object> resultMap=new HashMap<>();//返回结果
        resultMap.put("total",maintenanceMapper.getAllMaintenance().size());//总记录数
        resultMap.put("rows",maintenanceMapper.getMaintenanceByPage(start,pageSize));//当前页数据
        return resultMap;
    }
}
