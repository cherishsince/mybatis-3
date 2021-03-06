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
package org.apache.ibatis.executor.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 参数处理器
 *
 * tip: 用于处理，mapper 参数
 *
 * A parameter handler sets the parameters of the {@code PreparedStatement}.
 *
 * @author Clinton Begin
 */
public interface ParameterHandler {

  /**
   * 获取参数value
   *
   * @return
   */
  Object getParameterObject();

  /**
   * 设置到 sql的 PreparedStatement 里面
   *
   * @param ps
   * @throws SQLException
   */
  void setParameters(PreparedStatement ps) throws SQLException;

}
