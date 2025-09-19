package com.equipmentwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.equipmentwork.entity.AiReservation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AiReservationMapper extends BaseMapper<AiReservation> {

    @Select("select * from ai_reservation where phone=#{phone}")
    void findByPhone(String phone);

}
