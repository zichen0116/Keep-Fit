package com.equipmentwork.service;

import com.equipmentwork.entity.Maintenance;

import java.util.List;
import java.util.Map;

public interface MaintenanceService {
    public boolean update(Maintenance maintenance);
    public boolean insert(Maintenance maintenance);
    public boolean delete(long id);

    public List<Maintenance> getAll();
    public Map<String, Object> getByPage(int page, int pageSize);
}
