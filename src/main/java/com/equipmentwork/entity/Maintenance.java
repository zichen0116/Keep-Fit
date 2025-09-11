package com.equipmentwork.entity;


import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
@Schema(name="Maintenance",description="维护记录表")
public class Maintenance {
  @Schema(name="id",description="维护记录ID")

  private long id;
  @Schema(name="equipment_id",description="器材ID")

  private long equipment_id;
  @Schema(name="maintenance_date",description="维护日期")
  private java.time.LocalDate maintenance_date;
  @Schema(name="cost",description="维护费用")
  private double cost;
  @Schema(name="description",description="维护描述")
  private String description;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public LocalDate getMaintenance_date() {
    return maintenance_date;
  }

  public void setMaintenance_date(LocalDate maintenance_date) {
    this.maintenance_date = maintenance_date;
  }

  public long getEquipment_id() {
    return equipment_id;
  }

  public void setEquipment_id(long equipment_id) {
    this.equipment_id = equipment_id;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
