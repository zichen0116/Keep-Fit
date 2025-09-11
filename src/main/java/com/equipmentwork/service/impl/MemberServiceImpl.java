package com.equipmentwork.service.impl;

import com.equipmentwork.entity.Equipment;
import com.equipmentwork.entity.EquipmentQueryParam;
import com.equipmentwork.entity.Member;
import com.equipmentwork.entity.MemberQueryParam;
import com.equipmentwork.entity.PageResult;
import com.equipmentwork.mapper.EquipmentMapper;
import com.equipmentwork.mapper.MemberMapper;
import com.equipmentwork.service.EquipmentService;
import com.equipmentwork.service.MemberService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    
    @Autowired
    private MemberMapper memberMapper;


    @Override
    public PageResult<Member> page(MemberQueryParam memberQueryParam) {
        log.info("启动分页：page={},size={}",memberQueryParam.getPage(),memberQueryParam.getPageSize());
        PageHelper.startPage(memberQueryParam.getPage(),memberQueryParam.getPageSize());

        List<Member> memberList=memberMapper.list(memberQueryParam);

        Page<Member> p = (Page<Member>) memberList;
        log.info("实际分页结果: 请求数量={}, 实际数量={}", memberQueryParam.getPageSize(), p.getResult().size());
        return new PageResult<Member>(p.getTotal(),p.getResult());
    }

    @Override
    public List<Member> queryMember() {
        List<Member> clazzes = memberMapper.queryAllMember();
        return clazzes;
    }

    @Override
    public void insert(Member member) {
        memberMapper.insert(member);
    }

    @Override
    public Member getById(Integer id) {
        return memberMapper.getById(id);
    }

    @Override
    public void update(Member member) {
        memberMapper.update(member);
    }

    @Override
    public void delete(Integer id) {
        memberMapper.deleteById(id);
    }
    @Override
    public Member login(String name, String password) {
        return memberMapper.login(name,password);
    }
}