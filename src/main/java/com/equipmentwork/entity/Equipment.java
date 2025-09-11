package com.equipmentwork.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

  private int id;
  private String name;
  private String brand;
  private String size;
  private String area;
  private String status;



}
