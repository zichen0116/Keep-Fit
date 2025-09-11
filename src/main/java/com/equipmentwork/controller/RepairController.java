package com.equipmentwork.controller;

import com.equipmentwork.entity.Repair;
import com.equipmentwork.service.RepairService;
import com.equipmentwork.utils.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
//增、删、改、分页
@CrossOrigin
@Tag(name="报修管理",description="报修器材管理相关接口")
@RestController
@RequestMapping("/repair")
public class RepairController {
    @Autowired
    private RepairService repairService;
    @Operation(summary="增加报修信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "增加成功"),
            @ApiResponse(responseCode = "500",description = "增加失败")
    })

//请求路径为http://localhost:8089/EquipmentWork/repair/add
    //维修记录添加
    @PostMapping("/add")
    public CommonResult add(@RequestBody Repair repair){
        CommonResult commonResult = new CommonResult();
        try{
            repairService.insert(repair);
            commonResult.setCode(200);
            commonResult.setMsg("添加成功");

        }catch (Exception e){
            commonResult.setCode(500);
            commonResult.setMsg("添加失败");
        }
        return commonResult;

    }
    @Operation(summary="列举报修信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "增加成功"),
            @ApiResponse(responseCode = "500",description = "增加失败")
    })

    //维修记录查询
    // 请求路径为http://localhost:8089/EquipmentWork/repair/list
    @GetMapping("/list")
    public CommonResult list(){
        CommonResult commonResult = new CommonResult();
        try{
            List<Repair> repairList = repairService.getAll();
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
    @Operation(summary="删除报修信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "增加成功"),
            @ApiResponse(responseCode = "500",description = "增加失败")
    })
    @DeleteMapping("delete")
    // 请求路径为http://localhost:8089/EquipmentWork/repair/delete
    public CommonResult delete(@RequestParam("id") long id){
        CommonResult commonResult = new CommonResult();
        try{
            repairService.delete(id);
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
    @Operation(summary="更新报修信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "增加成功"),
            @ApiResponse(responseCode = "500",description = "增加失败")
    })
    @PutMapping("update")
    // 请求路径为http://localhost:8089/EquipmentWork/repair/update
    public CommonResult update(@RequestBody Repair repair){
        CommonResult commonResult = new CommonResult();
        try{
            repairService.update(repair);
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
            @ApiResponse(responseCode = "200",description = "增加成功"),
            @ApiResponse(responseCode = "500",description = "增加失败")
    })
//请求路径为http://localhost:8089/EquipmentWork/repair/page
    @GetMapping("/page")
    public CommonResult page(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize){
        CommonResult commonResult = new CommonResult();
        try{
            Map<String,Object> repairs=repairService.getByPage(page,pageSize);
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

}
