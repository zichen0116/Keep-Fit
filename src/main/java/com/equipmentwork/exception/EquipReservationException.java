package com.equipmentwork.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

// 自定义器材异常类
@Data
@NoArgsConstructor
public class EquipReservationException extends RuntimeException {
    public EquipReservationException(String message) {
        super(message);
    }
}