package cn.coget.test.genericToken;

import org.apache.ibatis.parsing.GenericTokenParser;
import org.apache.ibatis.parsing.TokenHandler;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GenericTokenParserTest {

  @Test
  public void parseTest() {
    GenericTokenParser tokenParser = new GenericTokenParser("#{", "}", content -> {
      Map<String, String> values = new HashMap<>();
      values.put("name", "大黄");
      return values.get(content);
    });
    System.err.println(tokenParser.parse("SELECT * FROM name=#{name}"));
  }
}
