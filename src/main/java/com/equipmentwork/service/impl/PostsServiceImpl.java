package com.equipmentwork.service.impl;


import com.equipmentwork.dao.PostsMapper;
import com.equipmentwork.entity.Posts;
import com.equipmentwork.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PostsServiceImpl implements PostsService {
    @Autowired
    private PostsMapper postsMapper;
    @Override
    public boolean addPosts(int memberId, String content) {
        Posts posts = new Posts();
        posts.setMemberId(memberId);
        posts.setContent(content);
        return postsMapper.insert(posts) > 0;
    }
    @Override
    public boolean deletePosts(int id){
        return postsMapper.delete(id) > 0;

    }
    @Override
    public List<Posts> getPosts() {
        return postsMapper.findAll();
    }
    @Override
    public Map<String,Object> getPostsPage(int currentPage, int size){
        int start = (currentPage - 1) * size;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total",postsMapper.findAll().size());
        resultMap.put("records",postsMapper.findByPage(start, size));
        return resultMap;

    }
    @Override
    public int updatePosts(int id, String content) {
        Posts posts = new Posts();
        posts.setId(id);
        posts.setContent(content);
        return postsMapper.update(posts);
    }
    @Override
    public List<Posts> searchPosts(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return postsMapper.findAll();
        }
        return postsMapper.searchPosts(keyword);
    }
}
