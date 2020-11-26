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
package cn.coget.test.mapper;

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

    // 老师，这个还是帮我申请退学吧，我反馈的这个问题直接影响课程质量问题，给我的感觉（这也是我很不愿意说的）工作三年毕竟每个人的知识体系不一样。
    // 我们话一万多报一个班，肯定希望得到的是一个完整的知识体系的巩固和成长，课程标题都规划不好，我作为学员，我也会有怀疑（这也是我很不愿意说的）

    try (Reader reader = Resources.getResourceAsReader("cn/coget/test/mapper/mybatis-config.xml")) {
      this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//      sqlSessionFactory.getConfiguration().setLogImpl();
    }
  }

  @Test
  public void selectTest() {
    // 一级缓存.md
    {
      SqlSession sqlSession = sqlSessionFactory.openSession();
      UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
      UserDO userDO = userMapper.selectById(1L);
      UserDO userDO2 = userMapper.selectById(1L);
      System.err.println(userDO);
      sqlSession.commit();
      sqlSession.close();
    }

    // 二级缓存.md
    {
      SqlSession sqlSession = sqlSessionFactory.openSession();
      UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
      UserDO userDO = userMapper.selectById(1L);
      UserDO userDO2 = userMapper.selectById(1L);
      System.err.println(userDO);
      sqlSession.commit();
      sqlSession.close();
    }
  }
}
