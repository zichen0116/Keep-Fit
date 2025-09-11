package com.equipmentwork.controller;

import com.equipmentwork.entity.Reservation;
import com.equipmentwork.service.ReservationService;
import com.equipmentwork.utils.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin
@Tag(name="预约管理",description="健身器材管理相关接口")
@RestController //SpringBoot框架的注解
@RequestMapping("/Reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    //请求路径为http://localhost:8089/EquipmentWork/Reservation/add
    @Operation(summary="增加预约信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "增加成功"),
            @ApiResponse(responseCode = "500",description = "增加失败")
    })
    @PostMapping("/add")
    public CommonResult addReservation(@RequestBody Reservation reservation) {
        CommonResult commonResult = new CommonResult();
        try{
            reservationService.addReservation(reservation);
            commonResult.setCode(200);
            commonResult.setMsg("success");
        }catch(Exception e){
            e.printStackTrace();
            commonResult.setCode(500);
            commonResult.setMsg("failed");
        }
        return commonResult;
    }
    @Operation(summary="删除预约信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "增加成功"),
            @ApiResponse(responseCode = "500",description = "增加失败")
    })
    //请求路径为http://localhost:8089/EquipmentWork/Reservation/delelte
    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam("id") int id) {
        CommonResult commonResult = new CommonResult();
        try{
            reservationService.deleteReservation(id);
            commonResult.setCode(200);
            commonResult.setMsg("success");
        }catch(Exception e){
            e.printStackTrace();
            commonResult.setCode(500);
            commonResult.setMsg("failed");
        }
        return commonResult;
    }

    @Operation(summary="查找全部用户")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "增加成功"),
            @ApiResponse(responseCode = "500",description = "增加失败")
    })

    @GetMapping("/list")
    //请求路径为http://localhost:8089/EquipmentWork/Reservation/list
    public CommonResult list() {
        CommonResult commonResult = new CommonResult();
        try {
            List<Reservation> users = reservationService.getReservations();
            commonResult = new CommonResult();
            commonResult.setCode(200);
            commonResult.setMsg("success");
            commonResult.setData(users);

        } catch (Exception e) {
            e.printStackTrace();
            commonResult.setCode(500);
            commonResult.setMsg("failed");

        }
        return commonResult;
    }

    @Operation(summary="分页查询用户")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "增加成功"),
            @ApiResponse(responseCode = "500",description = "增加失败")
    })
    //请求路径为http://localhost:8089/EquipmentWork/Reservation/page
    @GetMapping("/page")
    public CommonResult page(@RequestParam("currentPage") int currentPage, @RequestParam("size") int size) {
        CommonResult commonResult = new CommonResult();
        try{
            Map<String,Object> resultMap = reservationService.getReservationPage(currentPage,size);
            commonResult.setCode(200);
            commonResult.setMsg("success");
            commonResult.setData(resultMap);
        }catch (Exception e){
            commonResult.setCode(500);
            commonResult.setMsg("failed");
        }
        return commonResult;

    }

}
