package com.equipmentwork.controller;

import com.equipmentwork.entity.Reservation;
import com.equipmentwork.entity.Staff;
import com.equipmentwork.service.StaffService;
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
@Tag(name="员工信息管理",description="员工信息管理相关接口")
@RestController //SpringBoot框架的注解
@RequestMapping("/Staff")
public class StaffController {
    @Autowired
    private StaffService staffService;
    @Operation(summary="增加用户信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "增加成功"),
            @ApiResponse(responseCode = "500",description = "增加失败")
    })
    //请求路径为http://localhost:8089/EquipmentWork/Staff/add
    @PostMapping("/add")
    public CommonResult addStaff(@RequestBody Staff staff) {
        CommonResult commonResult = new CommonResult();
        try{
            staffService.addStaff(staff);
            commonResult.setCode(200);
            commonResult.setMsg("success");
        }catch(Exception e){
            e.printStackTrace();
            commonResult.setCode(500);
            commonResult.setMsg("failed");
        }
        return commonResult;
    }
    @Operation(summary="删除用户信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "增加成功"),
            @ApiResponse(responseCode = "500",description = "增加失败")
    })
    //请求路径为http://localhost:8089/EquipmentWork/Staff/delete
    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam("id") int id) {
        CommonResult commonResult = new CommonResult();
        try{
            staffService.deleteStaff(id);
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
    //请求路径为http://localhost:8089/EquipmentWork/Staff/list
    public CommonResult list() {
        CommonResult commonResult = new CommonResult();
        try {
            List<Staff> users =staffService.getStaffs();
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
    //请求路径为http://localhost:8089/EquipmentWork/Staff/page
    @GetMapping("/page")
    public CommonResult page(@RequestParam("currentPage") int currentPage, @RequestParam("size") int size) {
        CommonResult commonResult = new CommonResult();
        try{
            Map<String,Object> resultMap = staffService.getStaffPage(currentPage,size);
            commonResult.setCode(200);
            commonResult.setMsg("success");
            commonResult.setData(resultMap);
        }catch (Exception e){
            commonResult.setCode(500);
            commonResult.setMsg("failed");
        }
        return commonResult;

    }
    @Operation(summary="更新用户信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "增加成功"),
            @ApiResponse(responseCode = "500",description = "增加失败")
    })
    //请求路径为http://localhost:8089/EquipmentWork/Staff/update1

    @PutMapping("/update1")
    public CommonResult update(@RequestBody Staff user) {
        CommonResult commonResult = new CommonResult();
        try{
            staffService.updateStaff(user);
            commonResult.setCode(200);
            commonResult.setMsg("success");
        }catch(Exception e){
            commonResult.setCode(500);
            commonResult.setMsg("failed");
        }
        return commonResult;
    }
    @Operation(summary="模糊查询用户信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "增加成功"),
            @ApiResponse(responseCode = "500",description = "增加失败")
    })
    //请求路径为http://localhost:8089/EquipmentWork/Staff/select
    @GetMapping("/select")
    public CommonResult selectByKeyword(@RequestParam("keyword") String keyword) {
        CommonResult commonResult = new CommonResult();
        try{
            List<Staff> staffList = staffService.getStaffsByKeyword(keyword);
            commonResult.setCode(200);
            commonResult.setMsg("success");
            commonResult.setData(staffList);
        }catch (Exception e){
            commonResult.setCode(500);
            commonResult.setMsg("failed");
        }
        return commonResult;
    }

    @Operation(summary="用户登录")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "登录成功"),
            @ApiResponse(responseCode = "500",description = "登录失败")
    })



    @PostMapping(value = "/login",produces = "application/json;charset=UTF-8")//请求方式为post，请求路径为http://localhost:8089/mvc/user/login
    public CommonResult login(@RequestParam("name") String
                                      name,@RequestParam("password") String password) {
        CommonResult commonResult = new CommonResult();
        try {
            Staff staff = staffService.login(name,password);
            if (staff != null){
                commonResult.setCode(1);
                commonResult.setMsg("登录成功");
                commonResult.setData(staff);
            }else{
                commonResult.setCode(500);
                commonResult.setMsg("登录失败");
            }
        }catch (Exception e) {
            e.printStackTrace(); //输出异常信息
            commonResult.setCode(500);
            commonResult.setMsg("登录失败");
        }
        return commonResult;
    }
}
