package com.equipmentwork.dao;

import com.equipmentwork.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ReservationMapper {
    public int insert(Reservation reservation); //增
    public int delete(int id);//删除
    public List<Reservation> findAll();//查询所有
    public List<Reservation> findByPage(int start, int size);//按页查询
    public int countByEquipmentId(Integer equipmentId);//按ID查
}
