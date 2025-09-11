package com.equipmentwork.entity;


public class Staff {

  private long id;
  private String name;
  private String gender;
  private long age;
  private String role;
  private String password;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }


  public long getAge() {
    return age;
  }

  public void setAge(long age) {
    this.age = age;
  }


  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
