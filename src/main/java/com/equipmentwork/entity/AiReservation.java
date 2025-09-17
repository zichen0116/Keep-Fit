package com.equipmentwork.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AiReservation {

  private Integer id;
  private String name;
  private String gender;
  private String phone;
  private LocalDateTime communicationTime;
  private String province;
  private long estimatedScore;

}
