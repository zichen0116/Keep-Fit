package com.equipmentwork.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import com.equipmentwork.entity.Equipment; // 系统现有器材实体类

/**
 * 器材管理远程服务接口
 * 必须继承Remote，方法抛出RemoteException
 */
public interface EquipmentRemoteService extends Remote {
    /**
     * 批量查询器材状态（支持按状态筛选）
     * @param status 器材状态（可用/预约中/租借中/维修中）
     * @return 符合条件的器材列表
     */
    // List<Equipment> queryEquipmentByStatus(String status) throws RemoteException;

    /**
     * 远程更新器材状态
     * @param equipment 器材
     * @return 更新结果（true成功，false失败）
     */
    void updateEquipment(Equipment equipment) throws RemoteException;
}