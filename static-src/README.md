# Solo 应用模板

Solo 应用模板已经构建好一整完整的 Solo 应用开发环境，旨在于让开发者快速上手并且投入到 Solo 应用项目的开发当中来



## 快速开始 Get Started

> 前提：已经安装 [Node.js](https://nodejs.org/en/) 4.x 版本及以上, npm 3.x 版本及以上

``` bash
# 在文件夹根目录进入控制台，安装应用模板所需要的依赖
npm install --registry=https://registry.npm.taobao.org

# 在 localhost:8080 启动运行应用模板，开始开发和调试
npm run dev
```



## 预配置命令 Command

* `npm run dev`

  在浏览器中预览应用

* `npm run build`

  打包应用代码，并发布到应用模板根目录下的 **dist** 文件夹中

* `npm run buildx`

  应用代码进行分片打包，并发布到应用模板根目录下的 **dist** 文件夹中





##  文档 Documention

有关 Solo 框架的详细使用文档（内容命令，内置组件，页面导航...），请访问 [Solo 文档](http://kk5.landray.com.cn:6789/solo) 进行进一步的了解



## 网络调试 Network Debugging

网络调试，是应用实际开发过程中最易遇到最让人困扰的一部分内容。

在实际应用开发和调试过程中，前端和后端必然会进行数据接口的对接，对接过程中也会涉及到跨域名访问等各式网络问题。Solo 应用模板提供多种方式快速实现对接口的调试和尽力避免会出现的网络问题。


1. 本地模拟数据
  适用：项目初期，后端尚在开发接口过程中，未能直接向前端提供可用的数据接口。此时，作为前端的我们需要在本地模拟一些数据以适应应用的前期开发和调试

  > 注意：
  >
  > 1. 该方式只适用于应用的开发调试阶段，应用上线发布时应移除代理配置

  具体配置：

  ```javascript
  /* 以下是项目 api 文档定下的其中一个接口信息
  ** 描述：获取用户数据
  ** 方法: ‘POST'
  ** URL: ‘/api/get-user-data'
  ** request: {userID: 10}
  ** response: {code:0, message: '请求成功', data: {hello: 'world'}}
  */

  // 第一步：新建模拟数据文件

  /*  1. 在项目根目录的 static 文件夹中根据文档接口路径的目录结构新建模拟数据 json 文件，
  **  例如示例 URL 为‘/api/get-user-data’，则在 static 文件夹中新建子文件夹 api，然后在子文件夹中新建 get-user-data.json 文件夹，以此类推...
  **  2. 在新建的模拟数据 json 文件中根据 api 文档 response 信息自行写入需要模拟请求的返回结果，
  **  例如依据示例 api 在 get-user-data.json 中写入 {code:0, message: '请求成功', data: {hello: 'world'}}
  *／

  // 第二步：应用中调用请求

  /* 示例页面 example.vue */
  export default {
    methods: {
      // 页面中发起获取用户数据的请求
      getUserData() {
        this.$http.post('/api/get-user-data', {
          userID: 10
        }).then((res) => {
          // 请求成功回调，打印出请求回来的数据
          console.log(res.data)
        }, (response) => {
          // 请求失败回调，打印出请求失败的信息
          console.log(res.status)
        })
      }
    }
  }

  /* 入口文件 main.js */
  import VueResource from 'vue-resource'

  Vue.use(VueResource)
  Vue.http.interceptors.push((request, next) => {
     // 重定向请求路径到本地模拟数据路径中，请求方法也需要更改为‘GET’
     request.url = '/static' + request.url + '.json'
     request.method = 'GET'
     next()
  })
  ```

2. 代理后端数据
  适用：项目中后期，前后端可以开始进行接口的对接和对现有的这些接口进行测试和校验，但期间会涉及到跨域名访问其他域名的问题，此时需要借助代理的方式去获得数据。

  > 注意：
  >
  > 1. 当该方式和第一种本地模拟数据的方式共存的话，总会请求到代理后端的数据而不是本地模拟数据
  > 2. 该方式只适用于应用的开发调试阶段，应用上线发布时应移除代理配置

  具体步骤：

  ```javascript
  /* 以下是项目 api 文档定下的其中一个接口信息
  ** 描述：获取用户数据
  ** 方法: ‘POST'
  ** URL: ‘/api/get-user-data'
  ** request: {userID: 10}
  ** response: {code:0, message: '请求成功', data: {hello: 'world'}}
  */

  // 第一步：配置到后端的代理

  /* 在根目录的 config/proxy.js 中写入代理配置
  ** 例如将请求代理到后端服务器所在的这个地址上 http://172.16.10.28:3439，则详细配置内容如下所示：
  */
  module.exports = {
    '/api': { // 请求根路由
      target: 'http://172.16.10.28:3439', // 后端服务器地址
      changeOrigin: true
    }  
  }
  /* 关于代理的更详细的可配置项可参考 https://github.com/chimurai/http-proxy-middleware */

  // 第二步：应用中调用请求

  /* 示例页面 example.vue */
  export default {
    methods: {
      // 页面中发起获取用户数据的请求
      getUserData() {
        this.$http.post('/api/get-user-data', {
          userID: 10
        }).then((res) => {
          // 请求成功回调，打印出请求回来的数据
          console.log(res.data)
        }, (response) => {
          // 请求失败回调，打印出请求失败的信息
          console.log(res.status)
        })
      }
    }
  }

  /* 入口文件 main.js */
  import VueResource from 'vue-resource'
  Vue.use(VueResource)
  ```

3. KK的代理请求能力

  适用：在混合应用中/接口服务器未暴露到外网/需要跨域访问其他域名的服务

  > 注意：
  >
  > 1. 该方式和第二种代理后端的方式不能共存，但是能和第一种本地模拟数据的方式共存，请求返回的数据也是第一种方式的数据。
  > 2. `Vue.http.interceptors.push(kk.proxy.vueInterceptor) ` 这段代码应该放置在所有网络配置的最前头。
  > 3. 应用是在线应用的话，上线时应当移除这段代理配置信息。倘若应用是混合应用的话，上线时则应当保留这段代理配置信息

  具体配置：
  ```javascript
  // 应用中调用请求

  /* 示例页面 example.vue */
  export default {
    methods: {
      // 页面中发起获取用户数据的请求
      getUserData() {
        this.$http.post('/api/get-user-data', {
          userID: 10
        }).then((res) => {
          // 请求成功回调，打印出请求回来的数据
          console.log(res.data)
        }, (response) => {
          // 请求失败回调，打印出请求失败的信息
          console.log(res.status)
        })
      }
    }
  }

  /* main.js */
  import kk from 'kkjs'
  import VueResource from 'vue-resource'

  Vue.use(VueResource)
  // 使用KK的代理请求能力
  Vue.http.interceptors.push(kk.proxy.vueInterceptor)
  ```
