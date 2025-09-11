package com.equipmentwork.controller;

import com.equipmentwork.entity.Maintenance;
import com.equipmentwork.service.MaintenanceService;
import com.equipmentwork.utils.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
//增、删、分页、更新
@CrossOrigin
@Tag(name="维护记录",description="维护记录相关接口")
@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {
    @Autowired
    private MaintenanceService maintenanceservice;

    @Operation(summary="增加维护记录信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "租借成功"),
            @ApiResponse(responseCode = "500",description = "租借失败")
    })

//请求路径为http://localhost:8089/EquipmentWork/maintenance/add
    //维修记录添加
    @PostMapping("/add")
    public CommonResult add(@RequestBody Maintenance maintenance){
        CommonResult commonResult = new CommonResult();
        try{
            maintenanceservice.insert(maintenance);
            commonResult.setCode(200);
            commonResult.setMsg("添加成功");

        }catch (Exception e){
            commonResult.setCode(500);
            commonResult.setMsg("添加失败");
        }
        return commonResult;

    }
    @Operation(summary="列举维护信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "租借成功"),
            @ApiResponse(responseCode = "500",description = "租借失败")
    })
    //维修记录查询
    // 请求路径为http://localhost:8089/EquipmentWork/maintenance/list
    @GetMapping("/list")
    public CommonResult list(){
        CommonResult commonResult = new CommonResult();
        try{
            List<Maintenance> repairList = maintenanceservice.getAll();
            commonResult.setCode(200);
            commonResult.setMsg("查询成功");
            commonResult.setData(repairList);

        }catch (Exception e){
            e.printStackTrace();
            commonResult.setCode(500);
            commonResult.setMsg("查询失败");
        }
        return commonResult;
    }
    @Operation(summary="删除维护信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "租借成功"),
            @ApiResponse(responseCode = "500",description = "租借失败")
    })
    @DeleteMapping("delete")
    //请求路径为http://localhost:8089/EquipmentWork/maintenance/delete
    public CommonResult delete(@RequestParam("id") long id){
        CommonResult commonResult = new CommonResult();
        try{
            maintenanceservice.delete(id);
            commonResult.setCode(200);
            commonResult.setMsg("删除成功");

        }
        catch (Exception e){
            e.printStackTrace();
            commonResult.setCode(500);
            commonResult.setMsg("删除失败");
        }
        return commonResult;
    }
    @Operation(summary="更新维护信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "租借成功"),
            @ApiResponse(responseCode = "500",description = "租借失败")
    })
    @PutMapping("update")
    //请求路径为http://localhost:8089/EquipmentWork/maintenance/update
    public CommonResult update(@RequestBody Maintenance maintenance){

        CommonResult commonResult = new CommonResult();
        try{
            maintenanceservice.update(maintenance);
            commonResult.setCode(200);
            commonResult.setMsg("修改成功");

        }
        catch (Exception e){
            e.printStackTrace();
            commonResult.setCode(500);
            commonResult.setMsg("修改失败");
        }
        return commonResult;
    }
    @Operation(summary="分页查询")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "租借成功"),
            @ApiResponse(responseCode = "500",description = "租借失败")
    })

//请求路径为http://localhost:8089/EquipmentWork/maintenance/page
    @GetMapping("/page")
    public CommonResult page(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize){
        CommonResult commonResult = new CommonResult();
        try{
            Map<String,Object> repairs=maintenanceservice.getByPage(page,pageSize);
            commonResult.setCode(200);
            commonResult.setMsg("查询成功");
            commonResult.setData(repairs);
        }catch (Exception e){
            e.printStackTrace();
            commonResult.setCode(500);
            commonResult.setMsg("查询失败");
        }
        return commonResult;
    }





    //维修记录修改
    //维修记录删除
}
