package com.equipmentwork.controller;


import com.equipmentwork.entity.Equipment;
import com.equipmentwork.entity.EquipmentQueryParam;
import com.equipmentwork.entity.PageResult;
import com.equipmentwork.entity.Result;
import com.equipmentwork.service.EquipmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Tag(name = "器材管理", description = "器材管理相关接口")
@Slf4j
@RestController
@RequestMapping("/equipments")
public class EquipmentController {


    @Autowired
    private EquipmentService equipmentService;

    // 器材分页查询
    @GetMapping
    public Result page(EquipmentQueryParam equipmentQueryParam){
        log.info("分页查询：{}",equipmentQueryParam);
        PageResult<Equipment> result = equipmentService.page(equipmentQueryParam);
        return Result.success(result);
    }

    // 查询所有器材
    @GetMapping("/list")
    public  Result list(){
        log.info("查询所有器材");
        List<Equipment> equipmentList = equipmentService.queryEquipment();
        return Result.success(equipmentList);
    }

    // 添加器材
    @PostMapping
    public Result save(@RequestBody Equipment equipment){
        log.info("添加器材：{}",equipment);
        equipmentService.insert(equipment);
        return Result.success();
    }

    // 根据ID查询器材
    @GetMapping("/{id}")
    public Result getByID(@PathVariable Integer id){
        log.info("根据ID查询器材：{}",id);
        Equipment equipment = equipmentService.getById(id);
        return Result.success(equipment);
    }

    // 修改器材信息
    @PutMapping()
    public Result update(@RequestBody Equipment equipment){
        log.info("更新器材信息：{}",equipment);
        equipmentService.update(equipment);
        return Result.success();
    }
    // 删除器材
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除器材：{}",id);
        equipmentService.delete(id);
        return Result.success();
    }

}
