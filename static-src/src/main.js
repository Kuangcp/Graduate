// 引入 Vue
import Vue from 'vue'
// 引入Solo及css
import Solo from 'solojs'
import 'solojs/solo.css'

import kk from 'kkjs'

// 引入 vux 的 重置样式表
import 'vux/src/styles/reset.less'

// 引入 vue-resource 负责 ajax 数据请求
// 参考文档 https://github.com/pagekit/vue-resource
import VueResource from 'vue-resource'

import Pages from './pages/pages'

// 引入全局变量
import globalParams from './components/params/global-params'

Vue.prototype.GLOBAL_PARAMS = globalParams

// 引入 fastclick， 解决移动设备上点击延迟的问题
const FastClick = require('fastclick')

FastClick.attach(document.body)

Vue.use(VueResource)
Vue.use(Solo)

// // 调用KK的日志打印能力，控制台输出的日志信息也输出到K日志文件中
// // http://kk5.landray.com.cn:6789/jssdk/index.html#kkconfig
// kk.config('detailLog', true)

// // 如果接口服务器只能处理 application/x-www-form-urlencoded 格式的请求数据
// // 则需要将 emulateJSON 设置为 true
// Vue.http.options.emulateJSON = true

// // 若需要使用KK的代理请求能力(在混合应用应用中/接口服务器未暴露到外网/需要跨域访问其他域名的服务等)
// // 以下这段配置应当放置在所有网络配置的最前面
// // 则需要使用下面👇的代码
// Vue.http.interceptors.push(kk.proxy.vueInterceptor)

// // 在前后端分离且不使用KK的代理请求能力的前提下，应用开发前期需要自行在本地模拟接口数据，
// // 在项目根目录的 static 文件夹中建立以接口命名的 json 文件，json 文件内新建
// // 然后使用下面的代码
Vue.http.interceptors.push((request, next) => {
  // request.url = '/static' + request.url + '.json'
  // request.method = 'GET'
  // next()
})

// 启动应用
Solo.start({
  kk,
  appName: '学生',
  pages: Pages,
  // 应用首页
  homePage: 'index',
  // 应用进入页
  enterPage: 'index'
})
