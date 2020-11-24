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
import org.apache.ibatis.cache.TransactionalCacheManager;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * config 注册 mapper
 */
public class ConfigurationRegisterMapperTest extends BaseDataTest {

  @Before
  public void setup() {

  }

  @Test
  public void configRegisterMapperTest() {
    Configuration configuration = new Configuration();
    // 构建 Environment
    Environment.Builder builder = new Environment.Builder("11");
    builder.dataSource(new UnpooledDataSource(
      "com.mysql.jdbc.Driver",
      "jdbc:mysql://120.78.218.163:3306/storm_sports",
      "root",
      "@D23d7a3df91cc42"
    ));
    builder.transactionFactory(new JdbcTransactionFactory());
    configuration.setEnvironment(builder.build());
    // 添加 mapper
    configuration.addMapper(UserMapper.class);
    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(configuration);
    // 开启 sqlSession
    SqlSession sqlSession = factory.openSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    // 查询
    UserDO userDO = userMapper.selectById(1L);
    sqlSession.close();
    System.err.println(userDO);
  }
}
