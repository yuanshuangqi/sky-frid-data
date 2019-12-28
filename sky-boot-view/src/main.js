import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
/*导入全局样式*/
import './assets/css/global.css'
/*导入icon font css*/
import './assets/font/iconfont.css'
import echarts from 'echarts'
import ObjUtil from './util/ObjUtil.js'
import skyPermission from './permission'
import ElTreeSelect from 'el-tree-select';

Vue.use(ElTreeSelect);
Vue.config.productionTip = false
Vue.prototype.ObjUtil = ObjUtil
Vue.prototype.$skyPermission = skyPermission
Vue.prototype.$echarts = echarts
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
