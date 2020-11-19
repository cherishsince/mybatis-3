package org.apache.ibatis.mainTest;

import java.io.Serializable;

/**
 */
public class UserDO implements Serializable {

  private Long id;

  private String mobile;

  private String username;

  private String password;

  @Override
  public String toString() {
    return "UserDO{" +
      "id=" + id +
      ", mobile='" + mobile + '\'' +
      ", username='" + username + '\'' +
      ", password='" + password + '\'' +
      '}';
  }

  public Long getId() {
    return id;
  }

  public UserDO setId(Long id) {
    this.id = id;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public UserDO setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public UserDO setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserDO setPassword(String password) {
    this.password = password;
    return this;
  }
}
