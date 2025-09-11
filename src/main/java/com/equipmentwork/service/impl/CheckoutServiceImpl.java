package com.equipmentwork.service.impl;

import com.equipmentwork.dao.CheckoutMapper;
import com.equipmentwork.entity.Checkout;
import com.equipmentwork.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckoutServiceImpl implements CheckoutService
{
    @Autowired
    private CheckoutMapper checkoutMapper;
    @Override
    public  boolean addCheckout(Checkout checkout){
        return checkoutMapper.insert(checkout)>0;

    }
    @Override
    public boolean deleteCheckout(int id){
        return checkoutMapper.delete(id) > 0;

    }
    @Override
    // 获取所有预约信息
    public List<Checkout> getCheckouts(){
        return checkoutMapper.findAll();

    }
    @Override
    public Map<String,Object> getCheckoutPage(int currentPage, int size){
        int start = (currentPage - 1) * size;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total",checkoutMapper.findAll().size());
        resultMap.put("records",checkoutMapper.findByPage(start, size));
        return resultMap;

    }

}
