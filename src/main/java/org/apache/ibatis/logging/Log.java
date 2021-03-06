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
package org.apache.ibatis.logging;

/**
 * @author Clinton Begin
 */
public interface Log {

  /**
   * 是否开启 debug
   */
  boolean isDebugEnabled();

  /**
   * 是否开启 trace
   */
  boolean isTraceEnabled();

  /**
   * error 信息
   *
   * @param s
   * @param e
   */
  void error(String s, Throwable e);

  /**
   * error 信息
   *
   * @param s
   */
  void error(String s);

  /**
   * debug 信息
   *
   * @param s
   */
  void debug(String s);

  /**
   * trace 信息
   *
   * @param s
   */
  void trace(String s);

  /**
   * warn 信息
   *
   * @param s
   */
  void warn(String s);

}
