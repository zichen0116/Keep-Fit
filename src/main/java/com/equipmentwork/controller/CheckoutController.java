package com.equipmentwork.controller;

import com.equipmentwork.entity.Checkout;
import com.equipmentwork.entity.Reservation;
import com.equipmentwork.service.CheckoutService;
import com.equipmentwork.utils.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
//增、删、查、分页
@CrossOrigin
@Tag(name="租借管理",description="租借器材管理相关接口")
@RestController //SpringBoot框架的注解
@RequestMapping("/Checkout")
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;

    @Operation(summary="增加租借信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "租借成功"),
            @ApiResponse(responseCode = "500",description = "租借失败")
    })
    @PostMapping("/add")//http://localhost:8089/EquipmentWork/Checkout/add
    public CommonResult addCheckout(@RequestBody Checkout checkout) {
        CommonResult commonResult = new CommonResult();
        try{
            checkoutService.addCheckout(checkout);
            commonResult.setCode(200);
            commonResult.setMsg("success");
        }catch(Exception e){
            e.printStackTrace();
            commonResult.setCode(500);
            commonResult.setMsg("failed");
        }
        return commonResult;
    }

    @Operation(summary="删除租借信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "增加成功"),
            @ApiResponse(responseCode = "500",description = "增加失败")
    })
    //http://localhost:8089/EquipmentWork/Checkout/delete
    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam("id") int id) {
        CommonResult commonResult = new CommonResult();
        try{
            checkoutService.deleteCheckout(id);
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
    //http://localhost:8089/EquipmentWork/Checkout/list

    public CommonResult list() {
        CommonResult commonResult = new CommonResult();
        try {
            List<Checkout> users = checkoutService.getCheckouts();
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
    //http://localhost:8089/EquipmentWork/Checkout/page
    @GetMapping("/page")
    public CommonResult page(@RequestParam("currentPage") int currentPage, @RequestParam("size") int size) {
        CommonResult commonResult = new CommonResult();
        try{
            Map<String,Object> resultMap = checkoutService.getCheckoutPage(currentPage,size);
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
