package com.equipmentwork.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentQueryParam {
    private Integer page =1;
    private Integer pageSize =10;
    private String name;
    private String brand;
    private String status;
}
