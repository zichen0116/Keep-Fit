package com.equipmentwork.dao;

import com.equipmentwork.entity.Checkout;
import com.equipmentwork.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CheckoutMapper {
    public int insert(Checkout checkout); //增
    public int delete(int id);//删
    public List<Checkout> findAll();//查找所有
    public List<Checkout> findByPage(int start, int size);//按页查询
}
