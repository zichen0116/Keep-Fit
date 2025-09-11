package com.equipmentwork.service.impl;

import com.equipmentwork.dao.RepairMapper;
import com.equipmentwork.entity.Repair;
import com.equipmentwork.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RepairServiceImpl implements RepairService {
    @Autowired
    private RepairMapper repairMapper;

    @Override
    public boolean insert(Repair repair){
        return repairMapper.addRepair(repair)>0;
    }

    @Override
    public boolean delete(long id){
        return repairMapper.deleteRepair(id)>0;
    }

    @Override
    public boolean update(Repair repair){
        return repairMapper.updateRepair(repair)>0;
    }

    @Override
    public List<Repair> getAll(){
        return repairMapper.getAllRepair();
    }

    @Override
    public Map<String,Object> getByPage(int page, int pageSize){
        int start = (page-1)*pageSize;
        Map<String,Object> resultMap=new HashMap<>();//返回结果
        resultMap.put("total",repairMapper.getAllRepair().size());//总记录数
        resultMap.put("rows",repairMapper.getRepairByPage(start,pageSize));//当前页数据
        return resultMap;
    }

}
