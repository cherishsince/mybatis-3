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
package org.apache.ibatis.scripting.xmltags;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ognl.Ognl;
import ognl.OgnlException;

import org.apache.ibatis.builder.BuilderException;

/**
 * 缓存 OGNL 解析的表达式结果的缓存
 *
 * Caches OGNL parsed expressions.
 *
 * @author Eduardo Macarron
 *
 * @see <a href='https://github.com/mybatis/old-google-code-issues/issues/342'>Issue 342</a>
 */
public final class OgnlCache {

  /**
   *
   */
  private static final OgnlMemberAccess MEMBER_ACCESS = new OgnlMemberAccess();
  /**
   *
   */
  private static final OgnlClassResolver CLASS_RESOLVER = new OgnlClassResolver();
  /**
   * 解析后缓存，便于第二次直接获取
   */
  private static final Map<String, Object> expressionCache = new ConcurrentHashMap<>();

  private OgnlCache() {
    // Prevent Instantiation of Static Class
  }

  public static Object getValue(String expression, Object root) {
    try {
      // tip: root 是 OgnlContext 转换返回，不是就创建一个 OgnlContext
      Map context = Ognl.createDefaultContext(root, MEMBER_ACCESS, CLASS_RESOLVER, null);
      // 解析表达式 并 获取value
      return Ognl.getValue(parseExpression(expression), context, root);
    } catch (OgnlException e) {
      throw new BuilderException("Error evaluating expression '" + expression + "'. Cause: " + e, e);
    }
  }

  private static Object parseExpression(String expression) throws OgnlException {
    // tip: 先从cache中获取，没有再去解析表达式
    // tip: 如 data.id，有可能是对象里面的id，也有可能是 keyMap 的key
    Object node = expressionCache.get(expression);
    if (node == null) {
      // 解析 expression 表达式
      node = Ognl.parseExpression(expression);
      // 解析的 cache，避免下一次再次解析
      expressionCache.put(expression, node);
    }
    return node;
  }

}
