/**
 * Copyright 2009-2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ibatis.mainTest;

import org.apache.ibatis.BaseDataTest;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

/**
 * SqlSessionFactory
 */
public class SqlSessionFactoryTest extends BaseDataTest {

  private SqlSessionFactory sqlSessionFactory = null;

  @Before
  public void setup() throws IOException {
    try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/mainTest/mybatis-config.xml")) {
      this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }
  }

  @Test
  public void selectTest() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    String statement = "org.apache.ibatis.mainTest.UserMapper.selectById";
    UserDO userDO = sqlSession.selectOne(statement, 1L);
    sqlSession.commit();
  }
}
