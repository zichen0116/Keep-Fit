package com.equipmentwork.service;

import com.equipmentwork.entity.Equipment;
import com.equipmentwork.entity.Member;
import com.equipmentwork.entity.MemberQueryParam;
import com.equipmentwork.entity.PageResult;

import java.util.List;

public interface MemberService {

    PageResult<Member> page(MemberQueryParam memberQueryParam);

    // 查询所有会员
    List<Member> queryMember();

    // 添加会员
    void insert(Member member);

    Member getById(Integer id);

    void update(Member member);

    void delete(Integer id);

    Member login(String name, String password);
}
