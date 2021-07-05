package com.itStuManager.domain;


public class Student {

  private Integer sid;
  private String name;
  private String sex;
  private String hobby;
  private String birthday;
  private String sdesc;
  private String photo;

  public Student() {
  }

  public Student(Integer sid, String name, String sex, String hobby, String birthday, String sdesc, String photo) {
    this.sid = sid;
    this.name = name;
    this.sex = sex;
    this.hobby = hobby;
    this.birthday = birthday;
    this.sdesc = sdesc;
    this.photo = photo;
  }

  public Integer getSid() {
    return sid;
  }

  public void setSid(Integer sid) {
    this.sid = sid;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public String getHobby() {
    return hobby;
  }

  public void setHobby(String hobby) {
    this.hobby = hobby;
  }


  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }


  public String getSdesc() {
    return sdesc;
  }

  public void setSdesc(String sdesc) {
    this.sdesc = sdesc;
  }


  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

}
