package com.equipmentwork.controller;

import com.equipmentwork.entity.*;
import com.equipmentwork.entity.MemberQueryParam;
import com.equipmentwork.entity.Result;
import com.equipmentwork.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Tag(name = "会员管理", description = "会员管理相关接口")
@Slf4j
@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 会员分页查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int pageSize,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) String gender,
                       @RequestParam(required = false) Integer age){
        MemberQueryParam memberQueryParam = new MemberQueryParam();
        memberQueryParam.setPage(page);
        memberQueryParam.setPageSize(pageSize);
        memberQueryParam.setName(name);
        memberQueryParam.setGender(gender);
        memberQueryParam.setAge(age==null?0:age);

        log.info("分页查询：{}",memberQueryParam);
        PageResult<Member> result = memberService.page(memberQueryParam);
        return Result.success(result);
    }

    // 添加会员
    @PostMapping
    public Result save(@RequestBody Member member) {
        log.info("添加会员：{}", member);
        memberService.insert(member);
        return Result.success();
    }

    // 根据ID查询会员
    @GetMapping("/{id}")
    public Result getById(@PathVariable int id) {
        log.info("根据ID查询会员：{}", id);
        Member member = memberService.getById(id);
        return Result.success(member);
    }

    // 修改会员信息
    @PutMapping("/update")

    public Result update(@RequestBody Member member) {
        log.info("更新会员信息：{}", member);
        memberService.update(member);
        return Result.success();
    }

    // 删除会员
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) {
        log.info("删除会员：{}", id);
           memberService.delete(id);
        return Result.success();
    }

    // 查询所有会员
    @GetMapping("/list")
    public  Result list(){
        log.info("查询所有会员");
        List<Member> memberList = memberService.queryMember();
        return Result.success(memberList);
    }

    // 登录操作
    @PostMapping("/login")
    public Result login(@RequestParam("name") String
                                name,@RequestParam("password") String password) {
        log.info("登录操作：{}-{}", name, password);
        Member member = memberService.login(name, password);  // Corrected to include both name and password arguments
        try {
            if(member !=null) return Result.success(member);
            else return Result.error("登录失败");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
