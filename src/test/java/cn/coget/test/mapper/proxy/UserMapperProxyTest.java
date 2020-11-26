package cn.coget.test.mapper.proxy;

import cn.coget.test.mapper.UserMapper;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 */
public class UserMapperProxyTest {

  @Test
  public void proxyTest() {
    UserMapperProxy proxy = new UserMapperProxy();
    UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{UserMapper.class}, proxy);
    System.err.println(userMapper.selectById(1L));
  }
}
