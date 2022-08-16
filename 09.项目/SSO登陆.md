## 单点登陆(Single Sign On)

**单点登陆** : 在一个企业中, 系统由多个子系统构成, 访问每个子系统都需要单独登陆, 造成重复登陆多次, 操作复杂, 单点登陆即只需要登陆一次, 即可访问所有子系统.

![Cookie&Session](https://pic.imgdb.cn/item/612dc53144eaada73909f493.jpg)

在主流的登陆方案中, 都是使用 `cookie/session` 的解决方案, 即 浏览器端存储 `cookie` 信息, 服务端存储 `session` 信息, 浏览器的每次操作, 都会携带 `cookie` 到服务端, 服务端校验该 `cookie `是否已存在对应的 `session` 信息, 如果存在, 就允许浏览器进行操作, 不存在, 说明用户未登陆, 提示用户登陆. 登陆后, 创建cookie/session关联关系, 后续即可访问即可正常进行.

在多系统集成的场景下, 不同的系统部署在不同的domain域中(即 ip/port 不同), 由于浏览器的 `**同源策略**` 和 `**cookie作用域**` 的限制, 登陆系统A后, 会生成系统A的cookie, 这个cookie 的domain域限定为A (即只有在操作系统A时, 浏览器才会发送该cookie); 而如果操作系统B, 并不会发送系统A的cookie, 此时就需要再次登陆系统B, 才能生成系统B的 cookie/session 关系.



```
浏览器同源策略限制内容包括:

1. Cookie, LocalStorage 和 IndexDB 无法跨域读取.
2. DOM 无法跨域获取.
3. Ajax 请求无法跨域发送
```

```
Cookie作用域：

1. domain: 表示cookie所在的域，默认为请求的地址. 如网址为 www.google.com/test/test.html，那么domain默认为www.google.com. 如果要跨域访问，例如 域A为t1.test.com，域B为t2.test.com，那么在域A生产一个令域A和域B都能访问的cookie就要将该cookie的domain设置为.test.com；如果要在域A生产一个令域A不能访问而域B能访问的cookie就要将该cookie的domain设置为t2.test.com。
2. path: 表示cookie所在的目录, 默认为/, 即根目录. 例如 在同一个服务器上有目录如下：/test/, /test/cd/, /test/dd/，假设一个cookie1 的path为/test/，cookie2的path为/test/cd/，那么test下的所有页面都可以访问到cookie1，而/test/ 和 /test/dd/的子页面不能访问cookie2. 因为cookie仅能让其path路径下的页面访问.
```

### 

### 整体流程说明

![OAuth2 authorization code](https://pic.imgdb.cn/item/612dc66f44eaada7390ce690.jpg)

| NOTE | 单点登陆流程基于OAuth2协议的 授权码模式(authorization code) |
| ---- | ----------------------------------------------------------- |
|      |                                                             |

#### 第一次登陆

1. 浏览器请求 [http://customer.com](http://customer.com/) 域的 Nginx .
2. Nginx 将请求转发到 Gateway. (此处Nginx反向代理解决了Ajax跨域问题).
3. Gateway 判断如果用户未登陆, 则返回 `认证服务器` 的URL.
4. 前端对返回的 `认证服务器` 的URL进一步处理, 添加自定义的 `重定向URL` 地址.
5. 前端使用上一步处理后的 `认证服务器` URL跳转到 认证服务器.
6. 在 `认证服务器` , 如果用户未登陆, 返回登陆页面.
7. 用户输入用户名/密码, 提交登陆.
8. 登陆成功后, `认证服务器` 会回跳到前端自定义的 `重定向URL` 地址, 并且会在 URL中携带 `code` 与 `state` 参数(`state` 是可选的).
9. 前端收到 `认证服务器` 的重定向请求, 可以得到 `code` 值, 使用该值作为参数再次对网关发起认证请求. (需要将 redirect_uri 与 registration_id 也加入请求参数)
10. Nginx 将该请求转发至网关.
11. 网关收到请求后, 会在后台调用 `认证服务器` 验证该 `code` 是否有效.
12. 验证成功, 会返回用户的登陆信息, 网关使用这些登陆信息创建 `session`.
13. 网关为前端返回用户登陆信息.
14. 前端渲染展现用户登陆信息.

#### 第二次登陆

1. 浏览器请求 [http://order.com](http://order.com/) 域的 Nginx .
2. Nginx 将请求转发到 Gateway.
3. Gateway 判断如果用户未登陆, 则返回 `认证服务器` 的URL.
4. 前端对返回的 `认证服务器` 的URL进一步处理, 添加自定义的 `重定向URL` 地址.
5. 前端使用上一步处理后的 `认证服务器` URL跳转到 认证服务器.
6. 在 `认证服务器` , 如果用户已登陆, 会回跳到前端自定义的 `重定向URL` 地址, 并且会在 URL中携带 `code` 与 `state` 参数(`state` 是可选的).
7. 前端收到 `认证服务器` 的重定向请求, 可以得到 `code` 值, 使用该值作为参数再次对网关发起认证请求. (需要将 redirect_uri 与 registration_id 也加入请求参数)
8. Nginx 将该请求转发至网关.
9. 网关收到请求后, 会在后台调用 `认证服务器` 验证该 `code` 是否有效.
10. 验证成功, 会返回用户的登陆信息, 网关使用这些登陆信息创建 `session`.
11. 网关为前端返回用户登陆信息.
12. 前端继续处理业务操作.

#### 请求响应示例

未登陆时, 网关返回响应

```
Status Code: 403
Authentication: gateway-sso
Content-Type: application/json;charset=UTF-8

{
    "msg": "Full authentication is required to access this resource",
    "sso_flows": {
        "github": {
            "registrationId": "github",
            "redirectUri": "https://github.com/login/oauth/authorize?response_type=code&client_id=8c2c3e2f52ade799ed5f&scope=read%3Auser&redirect_uri=",
            "authenticationUri": "/login/oauth2/code/github"
        },
        "bss-login": {
            "registrationId": "bss-login",
            "redirectUri": "http://172.21.2.41:9010/oauth/authorize?response_type=code&client_id=bss-login&scope=user&redirect_uri=",
            "authenticationUri": "/login/oauth2/code/bss-login"
        }
    }
}
```

- 其中 redirectUri节点 表示 `认证服务器` 的地址, 需要将该URL中的 `redirect_uri=` 参数补全为前端自定义的重定向地址(地址要用URLEncode进行编码).
- authenticationUri 表示前端拿到OAuth2授权码流程中的 `code` 值后, 要将该值提交给网关完成认证的地址.

前端请求响应

- 前端拿到Gateway的未登陆响应后, 可以根据 `redirectUri` 值构建 如 `http://172.21.2.41:9010/oauth/authorize?response_type=code&client_id=bss-login&scope=user&redirect_uri=https://baidu.com&state=123` 的跳转地址, **其中 `redirect_uri` 表示认证服务器登陆成功后重定向的地址, 一般是本应用的某个预定地址** (例子中redirect_uri参数为 [https://baidu.com](https://baidu.com/) 仅仅是举例使用), state可以随意自定义或为空.
- 在 `认证服务器` 登陆成功后, 会重定向回到前端自定义的 `redirect_uri` 地址, 并携带 `code` 和 `state` 参数. 例如上面的认证请求会重定向为: https://www.baidu.com/?code=Gij0h3&state=123 (state与请求值相同)
- 前端从URL参数获得 `code` 的值, 在加上 `redirect_uri` (重定向地址) 与 `registration_id` (OAuth2授权码流程标识) 作为参数, 再次提交 `Get` 请求到网关. 例如: [http://localhost:8080/login/oauth2/code/bss-login?redirect_uri=](http://localhost:8080/login/oauth2/code/bss-login?redirect_uri=https://baidu.com®istration_id=bss-login&code=Gij0h3)[https://baidu.com&registration_id=bss-login&code=Gij0h3](https://baidu.com%26registration_id%3Dbss-login%26code%3Dgij0h3/) ; 如果验证成功, 网关会返回 登陆的用户信息.

```
{
    "password": null,
    "username": "sitech01",
    "authorities": [],
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true,
    "enabled": true,
    "sysUserCode": null,
    "orgId": "10031",
    "lanId": "8140100",
    "regionId": "8140100",
    "systemPostId": "10000000000003"
}
```

网关的认证处理

- 网关在拦截到 `/login/oauth2/code/{registrationId}` 的请求后, 会获取 `code` 值, 在网关内部 通过 `POST` 和 `Basic Auth` 的请求调用认证服务器, 例如: [http://172.21.2.41:9010/oauth/token?client_id=bss-login&grant_type=authorization_code&redirect_uri=](http://172.21.2.41:9010/oauth/token?client_id=bss-login&grant_type=authorization_code&redirect_uri=https://baidu.com&code=i6NV3c)[https://baidu.com&code=i6NV3c](https://baidu.com%26code%3Di6nv3c/) .网关服务器校验 `code` 和 `Basic Auth` 成功后, 返回 `access_token` .

![网关内部获取access_token](https://pic.imgdb.cn/item/612dc6d444eaada7390dc72c.jpg)

- 网关取到 `access_token` 后, 再使用 `access_token` 的值构建 `http://172.21.2.41:9010/user?access_token=dc8ef253-192e-4273-8016-48ce5cf3432a` 请求访问 `资源服务器` (此处资源服务器与认证服务器是同一个应用), 获得用户信息.

![网关内部获取登陆用户信息](http://172.18.193.45/trtd/oauth2/images/oauth2-code-user.png)

- 根据返回的用户信息创建 `session`

#### Vue 前端示例

##### 1.第一次登陆

- 在router/index.js配置请求项目根路径，跳转到一个自定义的空白页BlankContent

```
{
    path: "/",
    redirect: "blank",
},
{
    path: "/blank",
    component: () =>
        import("@/components/layout/BlankContent"),
}
```

以本地项目举例

- 在浏览器上输入地址 http://localhost:9093/#/ ，跳转到空白页BlankContent，为了用户体验可以增加loading的效果
- 空白页创建的同时发送请求 http://172.21.12.114:9080/oauth2/principal 到网关

config/index.js

```
// 网关登录
"/oauth2": {
    target: "http://172.21.12.114:9080",
      // 接口的域名
    //secure: false,                    // 如果是https接口，需要配置这个参数
    changeOrigin: true,
                     // 如果接口跨域，需要进行这个参数配置
    pathRewrite: {
        "": ""
    }
},
```

service/url/base-center-url.js

```
remoteLogin:'/oauth2/principal',
```

service/api/base-center.js

```
remoteLogin: function remoteLogin(param) {
    return request.get(baseServiceUrl.remoteLogin, param, true);
},
```

- 由于没有登录，网关返回403响应，在public/request.js拦截403响应进行处理，携带自定义redirectUri重定向到认证服务器上，拼成 http://172.21.2.41:9010/oauth/authorize?response_type=code&client_id=bss-login&scope=user&redirect_uri=http:%2F%2Flocalhost:9093%2F%23%2Fblank

```
function handlerLoginInterceptor(response){
        let location = window.location;
        let redirectHost = location.protocol + encodeURIComponent("//") + location.host;
        let bssLogin = response.data.sso_flows["bss-login"];
        let redirectUri = bssLogin.redirectUri + redirectHost + encodeURIComponent("/#/blank");
        window.location.href = redirectUri;
}
```

- 再次重定向到 http://172.21.2.41:9010/login ，展示登录页
- 在登录页上输入用户名密码，点击登录按钮，如果校验成功，会重定向到自定义redirectUri 地址，并携带code值， http://localhost:9093/?code=l4ct5Z
- 空白页BlankContent从url参数中获取code值，再加上redirect_uri和registration_id作为参数，再次提交到网关 http://172.21.12.114:9080/login/oauth2/code/bss-login?redirect_uri=http:%2F%2Flocalhost:9093%2F%23%2Fblank&registration_id=bss-login&code=l4ct5Z

config/index.js

```
// 认证登录
"/login": {
    target: "http://172.21.12.114:9080",
      // 接口的域名
    //secure: false,                    // 如果是https接口，需要配置这个参数
    changeOrigin: true,
                     // 如果接口跨域，需要进行这个参数配置
    pathRewrite: {
        "": ""
    }
},
```

service/url/base-center-url.js

```
oauth:'/login/oauth2/code/bss-login',
```

service/api/base-center.js

```
oauth: function oauth(param) {
    return request.get(baseServiceUrl.oauth, param);
},
```

- 认证成功，前端把返回的session信息保存起来，并且跳转到index首页

```
<script>
        import { baseApi } from "@/service/api";
        import { Base64 } from 'js-base64';

        export default {
                name: "BlankContent",
                created() {
                        this.redirectToUrl();
                },
                data(){
                        return{
                                redirectLocation: ""
                        }
                },
                methods: {
                        redirectToUrl: function() {
                                this.getLocation();
                                let pathname = window.location.href;
                                if(pathname.indexOf("code") !== -1) {
                                        let temp = pathname.substring(pathname.indexOf("code") + 5);
                                        let code = temp.substring(0, temp.indexOf("#"));
                                        let param = {
                                                redirect_uri: this.redirectLocation + "#/blank",
                                                registration_id: "bss-login",
                                                code: code
                                        }
                                        baseApi.oauth(param).then(res => {
                                                sessionStorage.setItem('userName', res.data.username);
                                                sessionStorage.setItem("sessionData", Base64.encode(JSON.stringify(res.data)));
                                                sessionStorage.setItem("sessionDataOriginal", JSON.stringify(res.data));
                                                this.goToIndex();
                                        });
                                } else {
                                        baseApi.remoteLogin().then(res =>{
                                                // 如果登录成功，再次访问起始地址，直接跳转到首页
                                                if(res !== undefined && res.status === 200 && res.data.username !== null){
                                                        this.goToIndex();
                                                }
                                        });
                                }
                        },
                        // 跳转到首页
                        goToIndex: function(){
                                window.location.href = this.redirectLocation + "#/main/index";
                        },
                        // 获取地址协议和端口号
                        getLocation: function(){
                                let location = window.location;
                                this.redirectLocation = location.protocol + "//" + location.host + "/";
                        }
                }
        }
</script>
```

##### 2.第二次登录

- 访问 http://localhost:9093/#/ ，跳转到空白页BlankContent，发送 http://172.21.12.114:9080/oauth2/principal 请求到网关
- 网关发现登录成功，返回200状态，前端直接重定向到index首页

##### 3.退出

- 点击退出按钮，首先向网关发送请求

config/index.js

```
// 网关退出
"/oauth2-logout": {
    target: "http://172.21.12.114:9080",
      // 接口的域名
    //secure: false,                    // 如果是https接口，需要配置这个参数
    changeOrigin: true,
                     // 如果接口跨域，需要进行这个参数配置
    pathRewrite: {
        "": ""
    }
}
```

service/url/base-center-url.js

```
oauth2Logout:'/oauth2-logout',
```

service/api/base-center.js

```
oauth2Logout: function oauth2Logout(param) {
    return request.post(baseServiceUrl.oauth2Logout, param, true);
}
```

- 网关退出成功后，携带要重定向的地址，发送请求到认证服务器上 [http://172.21.2.41:9010/logout?redirect_uri=](http://172.21.2.41:9010/logout?redirect_uri=http://localhost:9093/#/)http://localhost:9093/#/

```
logout () {
    let _this = this;
    this.$dialog({
        type: "info",
        title: "提示",
        text: "确定要退出登录吗？",
        confirmDialog: function () {
            // 网关退出
            baseApi.oauth2Logout().then(res=>{
                if(res !== undefined && res.status == 200){
                    // 认证退出
                    let location = window.location;
                    let url = location.protocol + "//" + location.host + "/";
                    window.location.href = res.data.authserverUrl + "?redirect_uri=" + url;
                    sessionStorage.removeItem("userName");
                    sessionStorage.removeItem("sessionData");
                    sessionStorage.removeItem("sessionDataOriginal");
                    sessionStorage.removeItem("tagsView");
                    _this.$store.dispatch("clearLocation");
                    _this.$store.dispatch("clearstate");
                }
            });
        },
        closeDialog: function () {
            console.log("取消！")
        }
    })
}
```

- 认证服务器退出，重定向到 http://localhost:9093/#/ ，由于没有了session，网关返回403响应，被拦截重定向到 http://172.21.2.41:9010/login

### 基于KONG的前后端分离流程

### Session 信息获取

应用在网关登陆认证成功后, 所有通过网关请求 **后端服务** 的 Http Request, 网关会自动为其增加一组 `x-session-` 前缀的Http Headers, 这些 Http Headers携带了登陆的 Session信息, 后端服务可以通过这些Http Headers获取到 session信息.

![oauth2: access-token](https://pic.imgdb.cn/item/612dc77744eaada7390f57a8.jpg)

#### 后端获取Session

在使用SpringMVC的后端中, 可以通过 @RequestHeader(value="x-session-username") 来获取Http Headers中携带的 `x-session-username` 的值。 Http Header中 `x-session-username` 的值 即对应于 **session** 中的 `username` 值。

代码示例

```
@GetMapping("/hello/{latency}")
public String hello(@PathVariable("latency") int latency,
                    @RequestHeader HttpHeaders httpHeaders,
                    @RequestHeader(value="x-session-username", required=false) String sessionUserName) {

    System.out.println("x-session-username -> " + sessionUserName);
    Map<String, String> headersMap = httpHeaders.toSingleValueMap();
    headersMap.forEach((k, v) -> {
        System.out.println(k + " : " + v);
    });
    return "Hello World!";
}
```

## 访问受保护资源(access_token)

网关作为 **Resource Server(资源服务器)**, 保护后端服务的API, 三方如果需要访问网关上的API, 需要到 **Authorization Server(授权认证服务器)** 上获取access_token, 通过access_token 才能访问网关上的API.

![oauth2: access-token](https://pic.imgdb.cn/item/612dc79544eaada7390f9c60.jpg)

### OAuth2密码模式

密码模式（Resource Owner Password Credentials Grant）中，用户向客户端提供自己的用户名和密码。客户端使用这些信息，向"服务商提供商"索要授权。

在这种模式中，用户必须把自己的密码给客户端，但是客户端不得储存密码。这通常用在用户对客户端高度信任的情况下，比如客户端是操作系统的一部分，或者由一个著名公司出品。而认证服务器只有在其他授权模式无法执行的情况下，才能考虑使用这种模式.

![Resource Owner Password Credentials Grant](http://172.18.193.45/trtd/oauth2/images/oauth2-password-grant.png)

网关的认证处理

（A）用户向客户端提供用户名和密码。

（B）客户端将用户名和密码发给认证服务器，向后者请求令牌。

（C）认证服务器确认无误后，向客户端提供访问令牌。

1. 为客户端分配用户名和密码, 例如分配的 用户名/密码 为 **sitech01/sitech01**
2. 客户端使用分配的 **用户名/密码** 请求 **Authorization Server**, 以获取 access_token.
3. 使用 access_token 获取用户认证信息.
4. access_token存在失效时间, 在 **Authorization Server** 的返回参数中, `expires_in` 表示失效的剩余时间(秒), 可以在失效前使用 `refresh_token` 刷新 access_token.

密码模式获取access_token

- POST http://172.21.2.41:9010/oauth/token
- Request Headers:

```
POST /oauth/token
Content-Type: application/x-www-form-urlencoded
cache-control: no-cache
Authorization: Basic YnNzLWxvZ2luOmJzcy1sb2dpbi0yMDE4
Accept: */*
Host: 172.21.2.41:9010
accept-encoding: gzip, deflate
content-length: 55
```

- Request Body:

```
grant_type=password&username=sitech01&password=sitech01
```

- Response Headers:

```
HTTP/1.1 200
status: 200
Pragma: no-cache
Cache-Control: no-store
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Wed, 09 Jan 2019 07:48:14 GMT
```

- Response Body:

```
{
    "access_token": "66ba0223-4335-4085-a2dd-91cb4646fdd9",
    "token_type": "bearer",
    "refresh_token": "5a4d7d4e-de41-4f97-9785-52e42a10c6c9",
    "expires_in": 17389,
    "scope": "user"
}
```

使用access_token获取用户信息

- GET http://172.21.2.41:9010/user?access_token=66ba0223-4335-4085-a2dd-91cb4646fdd9
- Response Body:

```
{
    "password": null,
    "username": "sitech01",
    "authorities": [],
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true,
    "enabled": true,
    "sysUserCode": "sitech01",
    "sysUserId": 1793,
    "sysPostId": 10000000000003,
    "orgId": 10031,
    "orgName": "太原市分公司",
    "regionId": 8140100,
    "regionName": "太原市",
    "staffId": 10000000000005,
    "staffName": "测试修改改",
    "systemInfoId": 1000,
    "lanId": 8140100,
    "lanName": "太原市"
}
```

刷新 acess_token

- POST http://172.21.2.41:9010/oauth/token
- Request Headers:

```
POST /oauth/token
Content-Type: application/x-www-form-urlencoded
cache-control: no-cache
Authorization: Basic YnNzLWxvZ2luOmJzcy1sb2dpbi0yMDE4
Accept: */*
Host: 172.21.2.41:9010
accept-encoding: gzip, deflate
content-length: 75
```

- Request Body:

```
grant_type=refresh_token&refresh_token=5a4d7d4e-de41-4f97-9785-52e42a10c6c9
```

- Response Headers:

```
HTTP/1.1 200
status: 200
Pragma: no-cache
Cache-Control: no-store
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Wed, 09 Jan 2019 08:31:39 GMT
```

- Response Body:
```
{
    "access_token": "4114f01b-a43a-44cc-903b-1b31550b25bf",
    "token_type": "bearer",
    "refresh_token": "5a4d7d4e-de41-4f97-9785-52e42a10c6c9",
    "expires_in": 43199,
    "scope": "user"
}
```