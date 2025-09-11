package com.equipmentwork.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private int id;
    private String name;
    private String gender;
    private int age;
    private String phone;
    private String password;
}
