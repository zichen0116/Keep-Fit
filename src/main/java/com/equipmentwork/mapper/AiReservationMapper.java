package com.equipmentwork.mapper;

import com.equipmentwork.entity.AiReservation;
import com.equipmentwork.entity.Reservation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AiReservationMapper {

    @Insert("insert into ai_reservation(name,gender,phone,communication_time,province,ai_reservation.estimated_score) values(#{name},#{gender},#{phone},#{communicationTime},#{province},#{estimatedScore})")
    void insert(AiReservation aiReservation );
    @Select("select * from ai_reservation where phone=#{phone}")
    void findByPhone(String phone);

}
