package com.equipmentwork.entity;


import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
@Schema(name="Repair",description="报修信息表")
public class Repair {
  @Schema(name="id",description="报修ID")
  private long id;
  @Schema(name="equipment_id",description="器材ID")
  private long equipment_id;
  @Schema(name="reporter_id",description="报修人ID")
  private long reporter_id;
  @Schema(name="problem",description="报修问题")
  private String problem;
  @Schema(name="report_time",description="报修时间")
  private java.time.LocalDateTime report_time;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Schema(name="status",description="报修状态")
  private String status;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public LocalDateTime getReport_time() {
    return report_time;
  }

  public void setReport_time(LocalDateTime report_time) {
    this.report_time = report_time;
  }

  public long getEquipment_id() {
    return equipment_id;
  }

  public void setEquipment_id(long equipment_id) {
    this.equipment_id = equipment_id;
  }

  public long getReporter_id() {
    return reporter_id;
  }

  public void setReporter_id(long reporter_id) {
    this.reporter_id = reporter_id;
  }

  public String getProblem() {
    return problem;
  }

  public void setProblem(String problem) {
    this.problem = problem;
  }

}
