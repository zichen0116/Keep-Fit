package com.equipmentwork.tools;

import com.equipmentwork.entity.AiReservation;
import com.equipmentwork.entity.Reservation;
import com.equipmentwork.service.MyAiService;
import com.equipmentwork.service.MyAiService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReservationTool {
    
    @Autowired
    private MyAiService myAiService;

    @Tool("预约志愿服务")
    public void addReservation(
            @P("考生姓名") String name,
            @P("考生性别") String gender,
            @P("考生手机号") String phone,
            @P("预约沟通时间，格式为：yyyy-MM-dd'T'HH:mm") String communicateTime,
            @P("考生所在省份") String province,
            @P("考生预估分数") Integer estimatedScore
    )
    {
        AiReservation aiReservation = new AiReservation(null,name,gender,phone, LocalDateTime.parse(communicateTime),province,estimatedScore);
        myAiService.insert(aiReservation);
    }

    @Tool("根据手机号查询预约服务")
    public void findReservationByPhone(
            @P("手机号") String phone
    )
    {
        Reservation reservation = myAiService.findByPhone(phone);
        System.out.println(reservation);
    }
}