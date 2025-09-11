package com.equipmentwork.dao;

import com.equipmentwork.entity.Staff;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface StaffMapper {
    public int insert(Staff staff); //增
    public int delete(int id); //删
    public  int update(Staff staff); //改
    public List<Staff> findAll(); //查询所有

    public List<Staff> findByPage(int start, int size);//分页查询
    public List<Staff> findByKeyword(String keyword);//模糊查询
    public Staff chachMember(String name,String password);
}
