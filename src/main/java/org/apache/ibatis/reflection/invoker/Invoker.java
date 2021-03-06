/**
 *    Copyright 2009-2020 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.reflection.invoker;

import java.lang.reflect.InvocationTargetException;

/**
 * invoker 方法调用的抽象
 *
 * @author Clinton Begin
 */
public interface Invoker {

  /**
   * invoke 调用（这里不是 反射的invoke，不过会使用到相关技术）
   * 注意：是现有，FieldInvoke、MethodInvoke、GetMethodInvoke、SetMethodInvoke
   *
   * @param target 目标对象
   * @param args 参数
   * @return
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  Object invoke(Object target, Object[] args) throws IllegalAccessException, InvocationTargetException;

  /**
   * 获取类型（一般是返回类型）
   */
  Class<?> getType();
}
