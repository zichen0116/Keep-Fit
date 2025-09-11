package com.equipmentwork.mapper;

import com.equipmentwork.entity.Member;
import com.equipmentwork.entity.MemberQueryParam;
import com.equipmentwork.entity.Member;
import com.equipmentwork.entity.MemberQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {
    // Member相关方法
    
    // 分页查询
    List<Member> list(MemberQueryParam memberQueryParam);

    //全部查询
    @Select("select m.* from member m ;")
    List<Member> queryAllMember();
    // 插入会员
    @Options(useGeneratedKeys = true,keyProperty="id")
    @Insert("insert into member (name,gender,age,phone,password) " +
            "values (#{name},#{gender},#{age},#{phone},#{password})")
    void insert(Member member);
    // 查询会员
    @Select("select * from member where member.id=#{id}")
    Member getById(Integer id);
    // 更新会员
    @Update("update member set name=#{name},gender=#{gender},age=#{age},phone=#{phone},password=#{password} where id=#{id}")
    void update(Member member);
    // 删除会员
    @Delete("delete from member where id=#{id}")
    void deleteById(Integer id);

    // 查询会员数
    @Select("select count(*) from member ")
    Integer countStudentByMemberId();

    @Select("select * from member where name=#{name} and password=#{password}")
    Member login(String name, String password);

}