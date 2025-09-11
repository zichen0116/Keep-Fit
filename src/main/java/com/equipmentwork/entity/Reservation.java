package com.equipmentwork.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name="Reservation",description="预约器材")
public class Reservation {

  // 预约ID
  @Schema(name="id",description="预约ID")
  private long id;
  // 设备ID
  @Schema(name="equipmentId",description="器材ID")
  private long equipmentId;
  // 会员ID
  @Schema(name="memberId",description="用户ID")
  private long memberId;
  // 开始时间
  @Schema(name="startTime",description="开始时间")
  private java.time.LocalDateTime startTime;
  // 结束时间
  @Schema(name="endTime",description="结束时间")
  private java.time.LocalDateTime endTime;


  // 获取预约ID
  public long getId() {
    return id;
  }

  // 设置预约ID
  public void setId(long id) {
    this.id = id;
  }


  // 获取设备ID
  public long getEquipmentId() {
    return equipmentId;
  }

  // 设置设备ID
  public void setEquipmentId(long equipmentId) {
    this.equipmentId = equipmentId;
  }


  // 获取会员ID
  public long getMemberId() {
    return memberId;
  }

  // 设置会员ID
  public void setMemberId(long memberId) {
    this.memberId = memberId;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

// 获取开始时间
  public LocalDateTime getStartTime() {
    return startTime;
  }

// 设置开始时间
  public void setStartTime(LocalDateTime startTime) {
    // 将传入的开始时间赋值给类的startTime属性
    this.startTime = startTime;
  }
}
