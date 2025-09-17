package com.equipmentwork.service.impl;

import com.equipmentwork.entity.AiReservation;
import com.equipmentwork.entity.Reservation;
import com.equipmentwork.mapper.AiReservationMapper;
import com.equipmentwork.service.MyAiService;
import com.equipmentwork.service.ReservationService;
import dev.langchain4j.service.spring.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyAiServiceImpl implements MyAiService {

    @Autowired
    private AiReservationMapper aiReservationMapper;
    @Override
    public void insert(AiReservation  aiReservation) {
        aiReservationMapper.insert(aiReservation);
    }

    @Override
    public Reservation findByPhone(String phone) {

        aiReservationMapper.findByPhone(phone);
        return null;
    }
}