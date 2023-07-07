import {report} from '@/api/repairs'
import {getUser} from '@/api/user'
export default {
  namespaced: true,
  state: {
    table: {
      data: [],
      total: 0
    }
  },
  mutations: {
    FIND_ALL (state, data) {
      state.table.data = data
      state.table.total = data.length
    }
  },
  actions: {
    // eslint-disable-next-line no-empty-pattern
    async getUser ({}, id) {
      const user = await getUser(id)
      return user
    },
    async report ({commit}, repair) {
      const data = await report(repair)
      commit('FIND_ALL', data)
    }
  }
}
