package com.equipmentwork.service.impl;

import com.equipmentwork.dao.ReservationMapper;
import com.equipmentwork.entity.Reservation;
import com.equipmentwork.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ReservationServiceImpl implements ReservationService
{
    @Autowired
    private ReservationMapper reservationMapper;
    @Override
    public boolean addReservation(Reservation reservation){
        return reservationMapper.insert(reservation)>0;

    }
    @Override
    public boolean deleteReservation(int id){
        return reservationMapper.delete(id) > 0;

    }
    @Override
    public List<Reservation> getReservations(){
        return reservationMapper.findAll();

    }
    @Override
    public Map<String,Object> getReservationPage(int currentPage, int size){
        int start = (currentPage - 1) * size;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total",reservationMapper.findAll().size());
        resultMap.put("records",reservationMapper.findByPage(start, size));
        return resultMap;


    }


}
