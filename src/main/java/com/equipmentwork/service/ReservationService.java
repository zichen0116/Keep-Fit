package com.equipmentwork.service;

import com.equipmentwork.entity.Reservation;

import java.util.List;
import java.util.Map;

public interface ReservationService {
    public  boolean addReservation(Reservation reservation);
    public boolean deleteReservation(int id);
// 获取所有预约信息
    public List<Reservation> getReservations();
    public Map<String,Object> getReservationPage(int currentPage, int size);

}
