package org.apache.ibatis.mainTest;

import org.apache.ibatis.annotations.Param;

/**
 */
public interface UserMapper {

  UserDO selectById(@Param("id") Long id);
}
