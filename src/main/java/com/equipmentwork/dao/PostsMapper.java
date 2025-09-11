package com.equipmentwork.dao;



import com.equipmentwork.entity.Posts;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PostsMapper {
    public int insert(Posts posts); //增
    public int delete(int id);//删除
    public List<Posts> findAll();//查询所有
    public List<Posts> findByPage(int start, int size);//按页查询
    public int update(Posts posts);//修改
    List<Posts> searchPosts(String keyword);
}
