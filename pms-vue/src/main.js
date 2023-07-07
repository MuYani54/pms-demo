// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
// 引入ECharts
import ECharts from 'vue-echarts'

// eslint-disable-next-line import/no-duplicates
import 'echarts/lib/chart/pie' // 引入饼图
// eslint-disable-next-line import/no-duplicates
import 'echarts/lib/component/tooltip' // 引入提示框组件
import 'echarts/lib/component/title' // 引入标题组件
import 'echarts/lib/component/legend' // 引入图例组件
import 'echarts/lib/component/toolbox'

import './assets/style/reset.less'
import './assets/icon/iconfont.css'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en'

import App from './App'
import router from './router'
import store from './store'

Vue.component('v-chart', ECharts)

// 注册 Vue-ECharts 组件
Vue.component('v-chart', ECharts)
Vue.use(ElementUI, { locale })
Vue.config.productionTip = false

if (process.env.NODE_ENV === 'production') {
  const { mockXHR } = require('../mock')
  mockXHR()
}

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})

const bus = new Vue()
Vue.prototype.$bus = bus
