package com.equipmentwork.mapper;

import com.equipmentwork.entity.Equipment;
import com.equipmentwork.entity.EquipmentQueryParam;
// import com.equipmentwork.model.Equipment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EquipmentMapper {
    // List<Equipment> getAllEquipments();
    // Equipment getEquipmentById(Long id);
    // void insertEquipment(Equipment equipment);
    // void updateEquipment(Equipment equipment);
    // void deleteEquipment(Long id);

    // 分页查询
    List<Equipment> list(EquipmentQueryParam equipmentQueryParam);

    //全部查询
    @Select("select e.* from equipment e ;")
    List<Equipment> queryAllEquipment();

    @Options(useGeneratedKeys = true,keyProperty="id")
    @Insert("insert into equipment (name,brand,size,area,status) " +
            "values (#{name},#{brand},#{size},#{area}, #{status})")
    void insert(Equipment equipment);

    @Select("select * from equipment where equipment.id=#{id}")
    Equipment getById(Integer id);

    @Update("update equipment set name=#{name},brand=#{brand},size=#{size},area=#{area},status=#{status} where id=#{id}")
    void update(Equipment equipment);

    @Delete("delete from equipment where id=#{id}")
    void deleteById(Integer id);


}