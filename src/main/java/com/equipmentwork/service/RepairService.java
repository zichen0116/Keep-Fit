package com.equipmentwork.service;

import com.equipmentwork.entity.Repair;

import java.util.List;
import java.util.Map;

public interface RepairService {
    public boolean update(Repair repair);
    public boolean insert(Repair repair);
    public boolean delete(long id);

    public List<Repair> getAll();
    public Map<String, Object> getByPage(int page, int pageSize);
}
