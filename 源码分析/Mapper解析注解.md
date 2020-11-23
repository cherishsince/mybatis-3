# Mapper解析注解

Mapper注解解析步骤，在注册 Mapper 的时候，`MapperRegistry#addMapper`，代码如下：

```java
// MapperRegistry
public <T> void addMapper(Class<T> type) {
  // <1> 必须是 interface
  if (type.isInterface()) {
    if (hasMapper(type)) {
      throw new BindingException("Type " + type + " is already known to the MapperRegistry.");
    }
    // <2> 加载完成标记
    boolean loadCompleted = false;
    try {
      // <3> 根据mapper，添加对应的 MapperProxyFactory 代理工厂
      knownMappers.put(type, new MapperProxyFactory<>(type));
//        在解析器运行之前添加类型是很重要的
//        否则，将自动尝试绑定
//        映射器解析器。如果已经知道类型，则不会尝试。
      // It's important that the type is added before the parser is run
      // otherwise the binding may automatically be attempted by the
      // mapper parser. If the type is already known, it won't try.
      // <3> 解析 Mapper 的注解配置
      MapperAnnotationBuilder parser = new MapperAnnotationBuilder(config, type);
      parser.parse();
      // 加载完成标记
      loadCompleted = true;
    } finally {
      // <4> 加载失败，需要从 knownMappers 移除
      if (!loadCompleted) {
        knownMappers.remove(type);
      }
    }
  }
}
```

说明：在<3>这里，构建了 `MapperAnnotationBuilder` 也是进行注解相关的解析过程（所以Mybatis不需要配置，也能注册一个Mapper）。



解析代码如下：

```java
// MapperAnnotationBuilder
public void parse() {
  // <1> 判断当前 Mapper 接口是否应加载过。
  String resource = type.toString();
  if (!configuration.isResourceLoaded(resource)) {
    // <2> 加载对应的 XML Mapper
    loadXmlResource();
    // <3> 标记该 Mapper 接口已经加载过
    configuration.addLoadedResource(resource);
    // <4> 设置 namespace 属性
    assistant.setCurrentNamespace(type.getName());
    // <5> 解析 @CacheNamespace 注解
    parseCache();
    // <6> 解析 @CacheNamespaceRef 注解
    parseCacheRef();
    // <7> 遍历每个方法，解析其上的注解
    for (Method method : type.getMethods()) {
      if (!canHaveStatement(method)) {
        continue;
      }
      // <7.1> 解析 @Select @SelectProvider 上是否有 @ResultMap，需要处理 ResultMap
      if (getAnnotationWrapper(method, false, Select.class, SelectProvider.class).isPresent()
        && method.getAnnotation(ResultMap.class) == null) {
        parseResultMap(method);
      }
      try {
        // <7.2> 执行解析
        parseStatement(method);
      } catch (IncompleteElementException e) {
        // <7.3> 解析失败，添加到 configuration 中
        configuration.addIncompleteMethod(new MethodResolver(this, method));
      }
    }
  }
  // <8> 解析待定的方法(由于信息还不完全，主要是 resultMap解析，xml 和 注解加载顺序不一样问题)
  parsePendingMethods();
}

```

解析注解有几个需要注意的地方：

1. <1> 这个if判断，判断 resource，xml加