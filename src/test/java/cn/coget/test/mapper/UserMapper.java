package cn.coget.test.mapper;

import org.apache.ibatis.annotations.Param;

/**
 */
public interface UserMapper {

  UserDO selectById(@Param("id") Long id);

  UserDO selectById2(@Param("id") Long id);
}
