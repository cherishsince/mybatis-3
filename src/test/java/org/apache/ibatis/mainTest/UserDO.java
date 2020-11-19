package org.apache.ibatis.mainTest;

/**
 */
public class UserDO {

  private Long id;

  private String username;

  private String password;

  public Long getId() {
    return id;
  }

  public UserDO setId(Long id) {
    this.id = id;
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
