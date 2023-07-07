// ../store/modules/echarts
import {findAll} from '@/api/role'
export default {
  namespaced: true,
  state: {
    chartData: [] // 添加一个新的属性用于存储图表数据
  },
  mutations: {
    // 添加一个新的 mutation 用于更新 chartData
    LOAD_CHART_DATA (state, data) {
      state.chartData = data
    }
  },
  actions: {
    async loadChartData ({commit}) {
      const response = await findAll()
      // console.log(response)
      // const result = Object.fromEntries(response.map(item => [item.name, item.citeNum]))
      const result = response.map(item => ({
        name: item.name, // 根据后端数据结构调整
        value: item.citeNum // 根据后端数据结构调整
      }))
      // console.log(2, result)
      commit('LOAD_CHART_DATA', result)
    }
  }
}
