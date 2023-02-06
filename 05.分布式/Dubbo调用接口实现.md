### 调用接口实现

```
    public String call(String interfaceName, String methodName, String arg0) {

        // 封装了与注册中心的连接以及与提供者的连接
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setApplication(application);
        reference.setRegistries(registryConfigs);

        // 声明为泛化接口
        reference.setGeneric(Boolean.TRUE);
        // reference.setTimeout(600000);

        // 弱类型接口名
        reference.setInterface(interfaceName);

        // 设置服务版本; 当一个接口实现，出现不兼容升级时，可以用版本号过渡，版本号不同的服务相互间不引用。
        if (StringUtils.isNotEmpty(defaultVersion)) {
            reference.setVersion(defaultVersion);
        }

        // 设置 服务分组 ; 当一个接口有多种实现时，可以用group区分。
        if (StringUtils.isNotEmpty(defaultGroup)) {
            reference.setGroup(defaultGroup);
        }

        // 入参类型
        String[] paraType = {"java.lang.String"};
        // 入参值
        Object[] paraValues = { arg0 };

        // 调用dubbo服务
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(reference);

        String uuid = UUID.randomUUID().toString();
        logger.info(">>> Dubbo 开始调用服务 [{}], interface: [{}], method: [{}], parameter: [{}]"
                , uuid, interfaceName, methodName, arg0);
        Object resultObj = genericService.$invoke(methodName, paraType, paraValues);
        logger.info("<<< Dubbo 调用服务结束 [{}], 返参为 : {}", uuid, resultObj);
        if (resultObj == null) {
            return null;
        }
        return resultObj.toString();
    }
```

