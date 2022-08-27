**整体流程说明：**

![整体流程说明](http://172.18.233.210:31102/gateway-docs/img/auth/jwt-token-flow.png)

1. 用户在网关OP系统申请App。
2. 网关OP系统自动生成APP ID和APP SECRET。
3. 用户使用APP ID和APP SECRET向网关OP系统申请Token。
4. 网关OP系统携带APP ID和APP SECRET向网关Token服务器申请Token。
5. 网关Token服务器生成Token，返回到网关OP系统。
6. 网关OP系统将Token返回给用户。
7. 用户携带Token请求网关。
8. 网关验证成功后转发下游服务。
9. 下游服务返回响应成功。
10. 网关返回响应成功。