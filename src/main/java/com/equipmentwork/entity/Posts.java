package com.equipmentwork.entity;


public class Posts {

  private long id;
  private long memberId;
  private String content;
  private java.sql.Timestamp postTime;
  private java.sql.Timestamp lastUpdated;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getMemberId() {
    return memberId;
  }

  public void setMemberId(long memberId) {
    this.memberId = memberId;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public java.sql.Timestamp getPostTime() {
    return postTime;
  }

  public void setPostTime(java.sql.Timestamp postTime) {
    this.postTime = postTime;
  }


  public java.sql.Timestamp getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(java.sql.Timestamp lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

}
