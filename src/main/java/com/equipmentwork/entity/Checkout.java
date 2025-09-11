package com.equipmentwork.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name="Checkout",description="租借器材表")
public class Checkout {
  @Schema(name="id",description="预约ID")

  private long id;
  @Schema(name="equipmentId",description="器材ID")

  private long equipmentId;
  @Schema(name="memberId",description="用户ID")
  private long memberId;
  @Schema(name="checkoutTime",description="租借时间")
  private java.time.LocalDateTime checkoutTime;
  @Schema(name="returnTime",description="归还时间")
  private java.time.LocalDateTime returnTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getEquipmentId() {
    return equipmentId;
  }

  public void setEquipmentId(long equipmentId) {
    this.equipmentId = equipmentId;
  }


  public long getMemberId() {
    return memberId;
  }

  public void setMemberId(long memberId) {
    this.memberId = memberId;
  }

  public LocalDateTime getCheckoutTime() {
    return checkoutTime;
  }

  public void setCheckoutTime(LocalDateTime checkoutTime) {
    this.checkoutTime = checkoutTime;
  }

  public LocalDateTime getReturnTime() {
    return returnTime;
  }

  public void setReturnTime(LocalDateTime returnTime) {
    this.returnTime = returnTime;
  }
}
