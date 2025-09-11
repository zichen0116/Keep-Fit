package com.equipmentwork.exception;

import lombok.Data;
import lombok.NoArgsConstructor;


// 自定义器材异常类
@Data
@NoArgsConstructor
public class EquipRepairException extends RuntimeException {
    public EquipRepairException(String message) {
        super(message);
    }
}