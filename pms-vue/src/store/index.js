import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import menu from './modules/menu'
import user from './modules/user'
import personal from './modules/personal'
import role from './modules/role'
import costSet from './modules/costSet'
import house from './modules/house'
import door from './modules/door'
import park from './modules/park'
import recharge from './modules/recharge'
import payment from './modules/payment'
import repairs from './modules/repairs'
import maintain from './modules/maintain'
import settle from './modules/settle'
import settleReport from './modules/settleReport'
import userhouse from './modules/userhouse'
import userdoor from './modules/userdoor'
import userpark from './modules/userpark'
import rechargeuser from './modules/rechargeuser'
import repairsuser from './modules/repairsuser'
import paymentuser from './modules/paymentuser'
import maintainuser from './modules/maintainuser'
import settleReportuser from './modules/settleReportuser'
import echarts from './modules/echarts'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    app: app,
    menu: menu,
    user: user,
    role: role,
    echarts: echarts,
    personal: personal,
    costSet: costSet,
    house: house,
    userhouse: userhouse,
    userdoor: userdoor,
    userpark: userpark,
    door: door,
    park: park,
    recharge: recharge,
    rechargeuser: rechargeuser,
    payment: payment,
    paymentuser: paymentuser,
    repairs: repairs,
    repairsuser: repairsuser,
    maintain: maintain,
    maintainuser: maintainuser,
    settle: settle,
    settleReport: settleReport,
    settleReportuser: settleReportuser
  }
})
