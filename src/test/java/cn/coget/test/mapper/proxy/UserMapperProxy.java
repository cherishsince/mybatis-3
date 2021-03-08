package cn.coget.test.mapper.proxy;

import cn.coget.test.mapper.UserDO;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *
 */
public class UserMapperProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return new UserDO().setId(1L).setMobile("133").setPassword("11").setUsername("proxy");
    }
}
