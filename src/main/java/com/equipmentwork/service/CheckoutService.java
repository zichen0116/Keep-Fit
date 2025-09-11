package com.equipmentwork.service;

import com.equipmentwork.entity.Checkout;
import com.equipmentwork.entity.Reservation;

import java.util.List;
import java.util.Map;

public interface CheckoutService {
    public  boolean addCheckout(Checkout checkout);
    public boolean deleteCheckout(int id);

    public List<Checkout> getCheckouts();
    public Map<String,Object> getCheckoutPage(int currentPage, int size);
}
