package com.wuhang.springbootproducerconsumer.inter;

public class User {

  private String username;

  private String password;

  private String lock;

  public String getLock() {
    return lock;
  }

  public void setLock(String lock) {
    this.lock = lock;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User(String username, String password,String lock) {
    this.username = username;
    this.password = password;
    this.lock = lock;
  }

  public User() {
  }

  @Override
  public String toString() {
    return "User{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", lock='" + lock + '\'' +
        '}';
  }
}
