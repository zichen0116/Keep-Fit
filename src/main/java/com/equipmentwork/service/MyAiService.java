package com.equipmentwork.service;

import com.equipmentwork.entity.AiReservation;
import com.equipmentwork.entity.Reservation;
import org.springframework.stereotype.Service;


public interface MyAiService {
    /**
     * 插入预约信息
     * @param aiReservation
     */
    void insert(AiReservation aiReservation);

    /**
     * 根据手机号查询预约信息
     * @param phone
     * @return
     */
    Reservation findByPhone(String phone);
}
