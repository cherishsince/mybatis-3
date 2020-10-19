/**
 *    Copyright 2009-2015 the original author or authors.
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
package org.apache.ibatis.reflection;

/**
 * 反射 factory
 */
public interface ReflectorFactory {

  /**
   * 是否开启缓存
   */
  boolean isClassCacheEnabled();

  /**
   * 设置缓存开关
   */
  void setClassCacheEnabled(boolean classCacheEnabled);

  /**
   * 根据 class 查询 Reflector 对象
   *
   * 注意：开启了 classCacheEnabled 会从 map 中获取
   * classCacheEnabled = false，直接 new Reflector
   * @param type
   * @return
   */
  Reflector findForClass(Class<?> type);
}
