package com.equipmentwork.controller;


import com.equipmentwork.entity.Posts;
import com.equipmentwork.entity.Reservation;
import com.equipmentwork.entity.Staff;
import com.equipmentwork.service.PostsService;
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
@Tag(name="帖子",description="帖子相关接口")
@RestController //SpringBoot框架的注解
@RequestMapping("/Posts")
public class PostsController {
    @Autowired
    private PostsService postsService;
    @Operation(summary = "增加帖子信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "增加成功"),
            @ApiResponse(responseCode = "500", description = "增加失败")
    })
    @PostMapping("/add")
    public CommonResult addPosts(@RequestParam("memberId") int memberId, @RequestParam("content") String content) {
        CommonResult commonResult = new CommonResult();
        try {
            postsService.addPosts(memberId, content);
            commonResult.setCode(200);
            commonResult.setMsg("success");
        } catch (Exception e) {
            e.printStackTrace();
            commonResult.setCode(500);
            commonResult.setMsg("failed");
        }
        return commonResult;
    }
    @Operation(summary="删除帖子信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "删除成功"),
            @ApiResponse(responseCode = "500",description = "删除失败")
    })

    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam("id") int id) {
        CommonResult commonResult = new CommonResult();
        try{
            postsService.deletePosts(id);
            commonResult.setCode(200);
            commonResult.setMsg("success");
        }catch(Exception e){
            e.printStackTrace();
            commonResult.setCode(500);
            commonResult.setMsg("failed");
        }
        return commonResult;
    }
    @Operation(summary="查询所有帖子信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "查询成功"),
            @ApiResponse(responseCode = "500",description = "查询失败")
    })
    @GetMapping("/list")
    //请求路径为http://localhost:8089/EquipmentWork/Reservation/list
    public CommonResult list() {
        CommonResult commonResult = new CommonResult();
        try {
            List<Posts> posts = postsService.getPosts();
            commonResult = new CommonResult();
            commonResult.setCode(200);
            commonResult.setMsg("success");
            commonResult.setData(posts);

        } catch (Exception e) {
            e.printStackTrace();
            commonResult.setCode(500);
            commonResult.setMsg("failed");

        }
        return commonResult;
    }
    @Operation(summary="分页查询帖子信息")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "查询成功"),
            @ApiResponse(responseCode = "500",description = "查询失败")
    })


    @GetMapping("/page")
    public CommonResult page(@RequestParam("currentPage") int currentPage, @RequestParam("size") int size) {
        CommonResult commonResult = new CommonResult();
        try{
            Map<String,Object> resultMap = postsService.getPostsPage(currentPage,size);
            commonResult.setCode(200);
            commonResult.setMsg("success");
            commonResult.setData(resultMap);
        }catch (Exception e){
            commonResult.setCode(500);
            commonResult.setMsg("failed");
        }
        return commonResult;

    }
    @Operation(summary = "修改帖子信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功"),
            @ApiResponse(responseCode = "500", description = "修改失败")
    })
    @PutMapping("/update")
    public CommonResult update(@RequestParam("id") int id, @RequestParam("content") String content) {
        CommonResult commonResult = new CommonResult();
        try {
            postsService.updatePosts(id, content);
            commonResult.setCode(200);
            commonResult.setMsg("success");
        } catch (Exception e) {
            e.printStackTrace();
            commonResult.setCode(500);
            commonResult.setMsg("failed");
        }
        return commonResult;
    }
    @Operation(summary = "模糊查询帖子信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功"),
            @ApiResponse(responseCode = "500", description = "查询失败")
    })
    @GetMapping("/search")
    public CommonResult search(@RequestParam("keyword") String keyword) {
        CommonResult commonResult = new CommonResult();
        try {
            List<Posts> posts = postsService.searchPosts(keyword);
            commonResult.setCode(200);
            commonResult.setMsg("success");
            commonResult.setData(posts);
        } catch (Exception e) {
            e.printStackTrace();
            commonResult.setCode(500);
            commonResult.setMsg("failed");
        }
        return commonResult;
    }



}
