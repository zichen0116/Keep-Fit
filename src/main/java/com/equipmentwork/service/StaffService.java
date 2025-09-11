package com.equipmentwork.service;

import com.equipmentwork.entity.Staff;

import java.util.List;
import java.util.Map;

public interface StaffService {
    public boolean addStaff(Staff staff);
    public boolean deleteStaff(int id);
    public int updateStaff(Staff staff);
    public List<Staff> getStaffs();
    public Map<String,Object> getStaffPage(int currentPage, int size);
    public List<Staff> getStaffsByKeyword(String keyword);

    public Staff login(String name,String password);
}
