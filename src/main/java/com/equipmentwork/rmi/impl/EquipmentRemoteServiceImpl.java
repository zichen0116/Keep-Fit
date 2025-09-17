package com.equipmentwork.rmi.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.equipmentwork.rmi.EquipmentRemoteService;
import com.equipmentwork.entity.Equipment;
import com.equipmentwork.service.EquipmentService; // 系统原有器材业务服务

/**
 * 器材管理远程服务实现类
 * 继承UnicastRemoteObject，自动处理网络通信与对象序列化
 */
@Component
public class EquipmentRemoteServiceImpl extends UnicastRemoteObject implements EquipmentRemoteService {

    // 注入系统原有业务服务，复用数据库操作逻辑
    @Autowired
    private EquipmentService equipmentService;

    // 必须显式定义构造方法（因UnicastRemoteObject构造方法抛出RemoteException）
    protected EquipmentRemoteServiceImpl() throws RemoteException {
        super();
    }

    // @Override
    // public List<Equipment> queryEquipmentByStatus(String status) throws RemoteException {
    //     // 调用原有业务逻辑，无需重复开发
    //     return equipmentService.listByStatus(status);
    // }

    @Override
    public void updateEquipment(Equipment equipment) throws RemoteException {
        // 调用原有业务逻辑，包含状态合法性校验（如“维修中”器材不可直接改为“可用”）
         equipmentService.update(equipment);
    }
}