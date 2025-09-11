package com.equipmentwork.service;

import com.equipmentwork.entity.Posts;
import com.equipmentwork.entity.Reservation;

import java.util.List;
import java.util.Map;

public interface PostsService {
    // PostsService.java
    boolean addPosts(int memberId, String content);
    public boolean deletePosts(int id);
    // 获取所有预约信息
    public List<Posts> getPosts();
    public Map<String,Object> getPostsPage(int currentPage, int size);
    int updatePosts(int id, String content);
    public List<Posts> searchPosts(String keyword);

}
