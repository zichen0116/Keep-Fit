package com.equipmentwork.service.impl;

import com.equipmentwork.dao.StaffMapper;
import com.equipmentwork.entity.Staff;
import com.equipmentwork.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffMapper staffMapper;
    @Override
    public boolean addStaff(Staff staff){
        return staffMapper.insert(staff) > 0;

    }
    @Override
    public boolean deleteStaff(int id){
        return staffMapper.delete(id) > 0;

    }
    @Override
    public int updateStaff(Staff staff){
        return staffMapper.update(staff);

    }
    @Override
    public List<Staff> getStaffs(){
        return staffMapper.findAll();

    }
    @Override
    public Map<String,Object> getStaffPage(int currentPage, int size){
        int start = (currentPage - 1) * size;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total",staffMapper.findAll().size());
        resultMap.put("records",staffMapper.findByPage(start, size));
        return resultMap;

    }
    @Override
    public List<Staff> getStaffsByKeyword(String keyword){
        return staffMapper.findByKeyword(keyword);

    }

    @Override
    public Staff login(String name,String password){
        return staffMapper.chachMember(name,password);
    }

}
